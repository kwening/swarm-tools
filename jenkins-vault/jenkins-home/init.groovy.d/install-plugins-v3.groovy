/* 
 * Jenkins init script 
 * 
 * Sets up a set of base plugins needed on the jenkins instance,
 * based on a list provided in text file 'plugins.txt'
 *
 * Precondition
 * Update center (especially custom update center) needs to be set up first
 */
import jenkins.model.*
import java.util.logging.Logger

def logger = Logger.getLogger("")
def installed = false
def initialized = false
def env = System.getenv()
def plugins = []

def pluginFilePath = env.JENKINS_PLUGINS_TXT ?: "${env.JENKINS_HOME}/plugins/plugins.txt"

// TODO: read plugins file from env-var
def pluginFile = new File(pluginFilePath)

if(!pluginFile.exists()) {
  logger.info("No plugin.txt found at ${pluginFilePath}")
  return
}

//pluginFile.eachLine { line ->
pluginFile.splitEachLine(":") { line ->
  plugins << line
}

def instance = Jenkins.getInstance()
def pm = instance.getPluginManager()
def uc = instance.getUpdateCenter()

plugins.each {
  pl = it[0]
  logger.info("Checking " + pl)

  if (!pm.getPlugin(it)) {
    logger.info("Looking in UpdateCenter for " + pl)

    if (!initialized) {
      logger.info "Initializing UpdateCenter"
      uc.updateAllSites()
      initialized = true
    }

    def plugin = uc.getPlugin(pl)

    if (plugin) {
      logger.info("Installing " + pl)
      def installFuture = plugin.deploy()

      while(!installFuture.isDone()) {
        logger.info("Waiting for plugin install: " + pl)
        sleep(3000)
      }

      installed = true
    }
  }
}

if (installed) {
  logger.info("Plugins installed, initializing a restart!")
  instance.save()
  instance.restart()
}

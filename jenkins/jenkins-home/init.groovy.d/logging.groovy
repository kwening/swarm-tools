import java.util.logging.Level
import java.util.logging.Logger

println "Setup logger"
 
Logger.getLogger("io.jenkins.plugins.casc.impl.secrets.VaultSecretSource").setLevel(Level.FINEST)

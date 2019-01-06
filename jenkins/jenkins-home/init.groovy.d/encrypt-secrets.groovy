#!groovy
import groovy.io.FileType
import java.util.logging.Logger

def logger = Logger.getLogger("")
def env = System.getenv()
def secretsPath = new File("${env.SECRETS}")

logger.info "Start encrypting secrets"

// loop over directory contents
secretsPath.eachFileRecurse (FileType.FILES) { file ->
  if(file.name.startsWith("user.")) {
    logger.info "User secret found : ${file.name} --> deleting"
    file.delete()
  } else if(file.name.startsWith("cred.")) {
    if(!file.text.startsWith("{")) {
      passwd_enc = hudson.util.Secret.fromString(file.text).getEncryptedValue()
      logger.info "Credential secret found : ${file.name} --> encrypting"
      file.write(passwd_enc)
    }
  }
}


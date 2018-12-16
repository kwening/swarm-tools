FROM jenkins/jenkins:lts
#ENV CASC_JENKINS_CONFIG="/var/jenkins_home/casc_config/jenkins.yml"
#ENV CASC_VAULT_TOKEN=5bcab13b-6cf5-2f58-8b37-34dca31bebde
#ENV CASC_VAULT_PATH=/secret/jenkins
#ENV CASC_VAULT_URL=http://127.0.0.1:8200
#COPY jenkins.yml ${CASC_JENKINS_CONFIG}
USER jenkins
RUN /usr/local/bin/install-plugins.sh configuration-as-code configuration-as-code-support git workflow-cps-global-lib

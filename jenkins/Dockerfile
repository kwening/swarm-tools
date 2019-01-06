FROM jenkins/jenkins:lts

USER root

RUN mkdir -p /var/jenkins_data/build \
    && mkdir /var/jenkins_data/workspace \
    && chown -R jenkins.jenkins /var/jenkins_data

USER jenkins
VOLUME /var/jenkins_home

COPY custom-entrypoint.sh /usr/local/bin/custom-entrypoint.sh

ENV JAVA_OPTS "-Djenkins.install.runSetupWizard=false -Djenkins.model.Jenkins.workspacesDir=/var/jenkins_data/workspace -Djenkins.model.Jenkins.buildsDir=/var/jenkins_data/build/\$\{ITEM_FULL_NAME\}"
RUN /usr/local/bin/install-plugins.sh configuration-as-code configuration-as-code-support git workflow-cps-global-lib greenballs simple-theme-plugin matrix-auth workflow-aggregator blueocean credentials-binding

ENTRYPOINT ["/sbin/tini", "--", "/usr/local/bin/custom-entrypoint.sh"]

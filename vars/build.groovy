def installNodeJs(){
    sh "sudo apt install nodejs -y"
    // sh "node_version=$(node -v)"
    // echo "Success installed Node.js ${node_version}"
}
def installNpm(){
    sh "sudo apt install npm -y"
}
def gitCheckout(url){
    checkout scmGit(branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[url: "${url}"]])
    echo "Code Cloned Successfully !"
}

def nodeJsBuild(){
    sh "npm install"
    echo "Build Successfully !"
}

def nodeJsTest(){
    sh "npm run test"
    echo "Test Successfully !"
}

def dockerBuildAndPush(dockerRegistry,credentialsId,imageName){
    withDockerRegistry(credentialsId: "${credentialsId}", url: "${dockerRegistry}") {
        sh "docker build -t ${imageName}:${BUILD_NUMBER} ."
        sh "docker push ${imageName}:${BUILD_NUMBER}"
    }
}
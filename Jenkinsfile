node{
stage('SCM Checkout'){
git 'https://github.com/techcybernetics/webapi-bdd'
}

stage('Compile-Package'){
sh 'mvn package'
}
}

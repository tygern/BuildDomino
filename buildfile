repositories.remote << "http://artifacts.devboy.org" << "http://repo2.maven.org/maven2"

define 'build-domino' do
  project.version = '0.1.0'
  package :jar
end
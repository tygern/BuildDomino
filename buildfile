THIS_VERSION = "0.2.0"

define 'build-domino' do
  project.version = THIS_VERSION
  package(:jar).with :manifest=>{
    'Copyright'=>'Tyson Gern, 2012',
    'Main-Class'=>'BuildDomino'
  }
end

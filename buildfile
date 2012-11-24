define 'build-domino' do
  project.version = '0.2.0'
  package(:jar).with :manifest=>{
    'Copyright'=>'Tyson Gern, 2012',
    'Main-Class'=>'BuildDomino'
  }
end

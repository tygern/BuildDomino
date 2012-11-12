define 'build-domino' do
  project.version = '0.1.0'
  package(:jar).with :manifest=>{ 'Main-Class'=>'BuildDomino' }
end

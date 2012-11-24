define 'build-domino' do
  project.version = '0.2.0'
  package(:jar).with :manifest=>{ 'Main-Class'=>'BuildDomino' }
end

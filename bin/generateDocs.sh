#!/bin/bash -e

git checkout docs
git merge origin/master

mvn clean javadoc:javadoc

if [ -d "docs" ]; then
  rm -rf docs
fi

mv target/site/apidocs docs

git reset .
git add docs/

git commit -m "Updated docs"

git push
git checkout master

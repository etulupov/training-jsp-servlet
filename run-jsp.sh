#!/bin/sh

mvn install

cd view-jsp

mvn install tomcat7:run-war
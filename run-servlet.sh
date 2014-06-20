#!/bin/sh

mvn install

cd view-servlet

mvn install tomcat7:run-war
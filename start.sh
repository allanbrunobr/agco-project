#!/bin/bash

java -jar domain-0.0.1-SNAPSHOT.jar &
java -jar services-impl-0.0.1-SNAPSHOT.jar &
java -jar user-0.0.1-SNAPSHOT.jar &
java -jar role-0.0.1-SNAPSHOT.jar &
java -jar team-0.0.1-SNAPSHOT.jar

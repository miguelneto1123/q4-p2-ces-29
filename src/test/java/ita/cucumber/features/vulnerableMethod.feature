# Author: Miguel
# Date: 07/01/2017

@test1
Feature: vulnerableMethod
Scenario: Opening an existing file that has content
	Given I have a file in "src/notEmpty.txt"
	When it has content
	Then its content should be printed

Scenario: Opening an existing file that has no content
	Given I have a file in "src/empty.txt"
	When it has no content
	Then its content should not be printed

Scenario: Trying to open a non-existing file
	Given I have a file in "src/nonExistant.txt"
	When it does not exist and I am trying to read it
	Then it should not work

Scenario: Trying to append to a file
	Given I have a file in "src/notEmpty.txt"
	When I attempt to write on it
	Then it should work

Scenario: Trying to write to a new file
	Given I have a file in "src/new.txt"
	When I attempt to write on it
	Then it should work
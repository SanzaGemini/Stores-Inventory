run_unittest: 
	- mvn clean -Dtest=com.stores.Inventory.Unittest.*Tests test

run_acceptance_test:
	- 	- mvn clean -Dtest=com.stores.Inventory.AcceptanceTests.*Tests test
	


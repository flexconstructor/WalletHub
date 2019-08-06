comma := ,

# Checks two given strings for equality.
eq = $(if $(or $(1),$(2)),$(and $(findstring $(1),$(2)),\
                                $(findstring $(2),$(1))),1)

run.db:
	docker run -it --network host --name mysql --rm -d \
		-p 3306:3306 \
		-e MYSQL_ALLOW_EMPTY_PASSWORD=true \
		-e MYSQL_USER=test \
		-e MYSQL_PASSWORD=test \
		-e MYSQL_DATABASE=accesslog \
		mysql:latest

run: run.db
	sleep 30
	gradle build -x test -x check
	docker run -it --rm --network host -v $(PWD)/build/libs/WalletHub-0.0.1-SNAPSHOT.jar:/app.jar \
		-v $(PWD)/access.log:/access.log openjdk:8 java -jar /app.jar
	docker stop mysql
	docker rm mysql

.PHONY: run.db run

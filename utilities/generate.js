/*
	Generate the Liquibase CSV for linking Questions to QuestionItems
	
	To run:
	node generate.js
*/

const fs = require('fs');

var groups = [
	{"questions": [1], 									"items": [1,2,3,4,5,6,7]},
	{"questions": [2], 									"items": [8,9,10,11,12,13,14,15,7]},
	{"questions": [3], 									"items": [16,17,18,19,7]},
	{"questions": [4], 									"items": [20,21,22,23]},
	{"questions": [5], 									"items": [24,25,26,27]},
	{"questions": [6,9,10,13,14,15], 					"items": [28,29,30,31]},
	{"questions": [7], 									"items": [32,25,33,34]},
	{"questions": [8], 									"items": [32,25,35,36]},
	{"questions": [11], 								"items": [32,25,35,27]},
	{"questions": [12], 								"items": [32,25,35,37]},
	{"questions": [16], 								"items": [32,38,35,27]},
	{"questions": [17,18,19,20,21,22,23,24], 			"items": [28,39,40,41]},
	{"questions": [25,26,27,28,29,30,31], 				"items": [28,39,40,41]},
	{"questions": [32], 								"items": [46,47,48]},
	{"questions": [50], 								"items": [49,50,51,52,53,54,55]},
	{"questions": [51], 								"items": [56,57,58,59,60]},
	{"questions": [52], 								"items": [61]},

];

var csv = ["question_id;question_item_id"];

for (var group of groups) {
	for (var question of group.questions) {
		for (var item of group.items) {
			csv.push(question + ";" + item)
		}
	}
}

var output = csv.join('\n');

fs.writeFileSync('question_question_item.csv', output, "utf8");

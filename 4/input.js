regExps = {
"exercise_1": /[A-Z][a-z]+/,
"exercise_2": /088[^0][0-9]{6}/,
"exercise_3": /[^0-1]+/,
"exercise_4": /^[A-Za-z][._0-9A-z]{2,16}$/,
"exercise_5": /^[0-9]{1,3}$|^1[0-4][0-9]{2}$|1500/,
"exercise_6": /class=['"][A-z\s]+["']/
};
cssSelectors = {
"exercise_1": "item:nth-child(3) > java",
"exercise_2": "different > java",
"exercise_3": "item:nth-child(3) > java > tag",
"exercise_4": "css > item:nth-child(3)",
"exercise_5": "tag > java:nth-of-type(2)",
"exercise_6": "item > item > item > item > item",
"exercise_7": "different#diffId2 > java:nth-child(2)",
"exercise_8": "#someId"
};

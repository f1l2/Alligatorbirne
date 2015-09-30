grammar Query;

@header {
	package event.processing.query.language;
}

query: conditions domains? window?;

window: WS windowType'(' WS? intValue WS? ')' | WS windowType'(' WS? intValue WS? ')';

domains: (WS 'FROM' WS domain);

domain: domainName | domain WS? COMMA WS? domain;

domainName: VARIABLE;

conditions: 'CONDITION' WS condition;

condition: singleCondition | compositeCondition;

singleCondition: evaluation | aggregateCondition;

compositeCondition: compositeOperationSingleDigit | compositeOperationDoubleDigit | singleCondition;

compositeOperationSingleDigit: compositeFunctionSingleDigit WS compositeCondition;

compositeFunctionSingleDigit: 'NOT'; 

compositeOperationDoubleDigit: singleCondition WS compositeFunctionDoubleDigit WS compositeCondition;

compositeFunctionDoubleDigit: ('AND' | 'and' | 'OR' | 'or' ); 

aggregateCondition: aggregateFunction WS? '(' WS? variable WS? ')' WS? operator WS? intValue;

aggregateFunction: ('SUM' | 'AVG' | 'COUNT' | 'MAX' | 'MIN');

evaluation: property WS? operator WS? property;

property: (variable | STRING | intValue);

variable: VARIABLE;

intValue: INT;

operator: ('=' | '<' | '>' | '<=' | '>=');

windowType: ('WIN:TIME' | 'win:time' | 'WIN:LENGTH' | 'win:length');

VARIABLE: ('A'..'Z' | 'a'..'z') ('A'..'Z' | 'a'..'z' | '0'..'9' | '-' | '_' )+;

STRING: '\'' ('A'..'Z' | 'a'..'z' | '0'..'9')+ '\'';
INT: ('0'..'9')+;

COMMA: (',')+;
WS: (' ' | '\t')+;
NL:  '\r'? '\n';
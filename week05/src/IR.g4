grammar IR;


//@header {
//package generated;
//}
program	: function+                     ;
function :  FUNCTION_START IDENT '(' type_spec* ')' type_spec NEWLINE
            instructions
            FUNCTION_END NEWLINE                    ;
instructions : (instruction NEWLINE)*               ;
instruction : assign_instruction
        | branch_instruction
        | call_instruction
        | return_instruction
        | label                                     ;
label : IDENT ':'                                   ;
assign_instruction : IDENT '=' operand BINARY_OP operand
        | IDENT '=' UNARY_OP operand
        | IDENT '=' operand                         ;
branch_instruction : BRANCH_OP IDENT
        | CBRANCH_OP operand IDENT                  ;
call_instruction : IDENT '=' CALL_OP IDENT '(' (operand (',' operand)* )? ')'   ;
return_instruction : RETURN_OP operand?             ;

type_spec	: VOID | INT                            ;
operand : IDENT | LITERAL                           ;

BINARY_OP :
        'mul' | 'div' | 'mod' | 'add' | 'sub' | 'eq' | 'neq' | 'leq' | 'lt' | 'geq' | 'gt' | 'and' | 'or' ;
UNARY_OP :
        'neg' | 'pos' | 'dec' | 'inc' | 'not' ;
BRANCH_OP :
        'jump' ;
CBRANCH_OP :
        'cjump' ;
CALL_OP :
        'call' ;
RETURN_OP :
        'return' ;

VOID: 'V';
INT: 'I';
FUNCTION_START : 'function';
FUNCTION_END : 'end function';


IDENT  : [a-zA-Z_]
        (   [a-zA-Z_]
        |  [0-9]
        )*;


LITERAL:   DecimalConstant     |   OctalConstant     |   HexadecimalConstant     ;


DecimalConstant
    :   '0'
	|   [1-9] [0-9]*
    ;

OctalConstant
    :   '0'[0-7]*
    ;

HexadecimalConstant
    :   '0' [xX] [0-9a-fA-F] +
    ;

WS  :   (   ' '
        |   '\t'
        )+
	-> channel(HIDDEN)	 
    ;



NEWLINE : '\n' (WS* '\n')*;

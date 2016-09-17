// $ANTLR 3.5 /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g 2016-09-17 19:42:31

package kohl.hadrien.antlr3;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("all")
public class ValidationMlParser extends Parser {
    public static final String[] tokenNames = new String[]{
            "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABS", "AGGREGATE", "AGGREGATERULEID_TAG",
            "AGGREGATE_AVG_TAG", "AGGREGATE_COUNT_TAG", "AGGREGATE_MAX_TAG", "AGGREGATE_MEDIAN_TAG",
            "AGGREGATE_MIN_TAG", "AGGREGATE_PERCENTILE_TAG", "AGGREGATE_STD_TAG",
            "AGGREGATE_SUM_TAG", "ALL", "AND", "AS", "ASC", "ASSIGN", "ASSIGN_TAG",
            "ATTRCALC", "ATTRIBUTE", "AVG", "AVGPERIOD", "BETWEEN", "BOOLEAN_CONSTANT",
            "BOOLEAN_CONSTANT_TAG", "BREAK", "BREAKDATE", "BREAK_DATE_TAG", "BREAK_EXPR_TAG",
            "BY", "CALC", "CALC_CLAUSE_TAG", "CARTESIAN_PER", "CASE", "CHARLENGTH",
            "CHARSET_MATCH", "CHECK", "CODELIST_MATCH", "COMPATIBILITYRULEID_TAG",
            "COMPLCHECK", "COMPONENTID_TAG", "CONCAT", "COND", "COUNT", "COUNT_DISTINCT",
            "CURRCHANGE", "CURRENCY_TAG", "CURRENT_DATE", "DATARIF_TIMEDELAY_CLAUSE_TAG",
            "DATASETCONTEXTID_TAG", "DATASETID_TAG", "DATASET_ABS_TAG", "DATASET_AGGREGATION_CLAUSE_TAG",
            "DATASET_AGGREGATION_MEASURE_TAG", "DATASET_AGGREGATION_SUBCLAUSE_TAG",
            "DATASET_AND_TAG", "DATASET_A_TAG", "DATASET_BETWEEN_TAG", "DATASET_BREAK_TAG",
            "DATASET_B_TAG", "DATASET_CALC_CLAUSE_TAG", "DATASET_CASE_ELSE_TAG", "DATASET_CASE_IF_TAG",
            "DATASET_CASE_TAG", "DATASET_CHARLENGTH_TAG", "DATASET_CHARSET_TAG", "DATASET_CHECK_ALL_TAG",
            "DATASET_CHECK_CONDITION_TAG", "DATASET_CHECK_DISCREPANCY_TAG", "DATASET_CHECK_ERLEVEL_TAG",
            "DATASET_CHECK_ERRORCODE_TAG", "DATASET_CHECK_IMBALANCE_TAG", "DATASET_CHECK_LEVEL_TAG",
            "DATASET_CHECK_SEVERITY_TAG", "DATASET_CHECK_TAG", "DATASET_CHECK_THRESHOLD_TAG",
            "DATASET_CLAUSE_TAG", "DATASET_COMPARE_TAG", "DATASET_COMPLCHECK_TAG",
            "DATASET_CONCAT_TAG", "DATASET_CURRCHANGE_TAG", "DATASET_DIFFC_TAG", "DATASET_DIFFPERC_TAG",
            "DATASET_DIFF_TAG", "DATASET_DIVDIV_TAG", "DATASET_DIVIDEFUN_TAG", "DATASET_DIVIDE_TAG",
            "DATASET_DOT_MEASURE_TAG", "DATASET_DROP_CLAUSE_TAG", "DATASET_EQ_TAG",
            "DATASET_EXCHECK_COMPL_ERRORLEVEL_TAG", "DATASET_EXCHECK_COMPL_KEYSET_TAG",
            "DATASET_EXCHECK_TAG", "DATASET_EXCLUDE_CLAUSE_TAG", "DATASET_EXISTS_IN_ALL_TAG",
            "DATASET_EXISTS_IN_TAG", "DATASET_EXKEY_TAG", "DATASET_EXP_TAG", "DATASET_EX_TAG",
            "DATASET_FILTER_CLAUSE_TAG", "DATASET_FIRST_CLAUSE_TAG", "DATASET_GET_DATASETAGGR_TAG",
            "DATASET_GET_DATASETID_TAG", "DATASET_GET_FILTERS_CLAUSE_TAG", "DATASET_GET_FILTER_CLAUSE_TAG",
            "DATASET_GET_KEEP_CLAUSE_TAG", "DATASET_GET_MERGE_CLAUSE_TAG", "DATASET_GET_PCSFILTER_CLAUSE_TAG",
            "DATASET_GET_TAG", "DATASET_GET_TIMEDELAY_CLAUSE_TAG", "DATASET_GET_TIMEFILTER_CLAUSE_TAG",
            "DATASET_GE_TAG", "DATASET_GT_TAG", "DATASET_HMEETS_TAG", "DATASET_INBETWEEN_TAG",
            "DATASET_INCLUDE_CLAUSE_TAG", "DATASET_INDEXOF_TAG", "DATASET_INSET_TAG",
            "DATASET_INTERSECTC_TAG", "DATASET_INTERSECT_TAG", "DATASET_IN_TAG", "DATASET_KEEP_CLAUSE_TAG",
            "DATASET_KEYSET_TAG", "DATASET_LAG_CLAUSE_TAG", "DATASET_LAST_CLAUSE_TAG",
            "DATASET_LCASE_TAG", "DATASET_LEFTC_TAG", "DATASET_LEN_TAG", "DATASET_LEVEL",
            "DATASET_LE_TAG", "DATASET_LIST_TAG", "DATASET_LN_TAG", "DATASET_LOG_TAG",
            "DATASET_LTRIM_TAG", "DATASET_LT_TAG", "DATASET_MATCH_TAG", "DATASET_MAX_TAG",
            "DATASET_MERGE_TAG", "DATASET_MINUS2FUN_TAG", "DATASET_MINUSFUN_TAG",
            "DATASET_MINUSMINUS_TAG", "DATASET_MINUS_TAG", "DATASET_MINUS_UNARY_TAG",
            "DATASET_MIN_TAG", "DATASET_MOD_TAG", "DATASET_MULTIPLYFUN_TAG", "DATASET_MULTIPLY_TAG",
            "DATASET_MULTMULT_TAG", "DATASET_NEX_TAG", "DATASET_NE_TAG", "DATASET_NODUPLICATES_TAG",
            "DATASET_NOT_BETWEEN_TAG", "DATASET_NOT_EXISTS_IN_ALL_TAG", "DATASET_NOT_EXISTS_IN_TAG",
            "DATASET_NOT_IN_TAG", "DATASET_NOT_TAG", "DATASET_NROOT_TAG", "DATASET_NVL_TAG",
            "DATASET_OR_TAG", "DATASET_OVERLAP_TAG", "DATASET_PERCENT_TAG", "DATASET_PLUS2FUN_TAG",
            "DATASET_PLUSFUN_TAG", "DATASET_PLUSPLUS_TAG", "DATASET_PLUS_TAG", "DATASET_PLUS_UNARY_TAG",
            "DATASET_POWER_TAG", "DATASET_RANK_CLAUSE_TAG", "DATASET_RANK_ORDERBY_CLAUSE_TAG",
            "DATASET_RELPERC_TAG", "DATASET_RENAME_CLAUSE_TAG", "DATASET_ROUND_TAG",
            "DATASET_RTRIM_TAG", "DATASET_SUBSTR_TAG", "DATASET_TOP_CLAUSE_TAG", "DATASET_TOP_ORDERBY_CLAUSE_TAG",
            "DATASET_TRIM_TAG", "DATASET_TRUNC_TAG", "DATASET_TYPE_TAG", "DATASET_UCASE_TAG",
            "DATASET_UMEETS_TAG", "DATASET_UNIONC_TAG", "DATASET_UNION_TAG", "DATASET_USING_CLAUSE_TAG",
            "DATASET_VALIDATE_CONDITIONTYPE_TAG", "DATASET_XOR_TAG", "DESC", "DIFF",
            "DIFFPERC", "DIMENSION", "DIMENSIONID_TAG", "DISCREPANCY", "DIVDIV", "DIVFUN",
            "DIVIDE", "DROP", "ELSE", "ELSEIF", "ENDPERIOD", "EOL", "EQ", "ERLEVEL",
            "ERRORCODE", "EVAL", "EX", "EXCHECK", "EXCLUDE", "EXISTS_IN", "EXISTS_IN_ALL",
            "EXKEY", "EXP", "FILTER", "FIRST", "FLOATEXP", "FLOAT_CONSTANT", "FLOAT_CONSTANT_TAG",
            "FROM_CURR", "GE", "GET", "GT", "HIERARCHY", "HIGHPERC_TAG", "HMEETS",
            "IDENTIFIER", "IF", "IMBALANCE", "IN", "INBALANCE", "INCLUDE", "INDEXOF",
            "INTDAY", "INTEGER_CONSTANT", "INTERSECT", "INTMONTH", "INTYEAR", "INT_CONSTANT_TAG",
            "ISNULL", "IS_COMPL_LIST_TAG", "JOIN_CONDITION_TAG", "JOIN_LEFTARGUMENT_TAG",
            "JOIN_LEFTVAR_TAG", "JOIN_RENAME_TAG", "JOIN_RIGHTARGUMENT_TAG", "JOIN_RIGHTVAR_TAG",
            "KEEP", "KEY", "KEYS", "KEYSET_COMP_TAG", "KEYSET_KEYS_TAG", "KEYSET_LIST_TAG",
            "KEYSET_VARPARAM_TAG", "KEYSET_VARS_TAG", "KEYSET_VAR_LIST_TAG", "KEYSET_VAR_SETID_TAG",
            "KEYSET_VAR_TAG", "LAGLIST_TAG", "LAST", "LCASE", "LE", "LEFTC", "LEN",
            "LETTER", "LN", "LOG", "LOWPERC_TAG", "LT", "MATCHES_INVALID", "MATCHES_VALID",
            "MATCHKEY", "MAX", "MEASURE", "MEDIAN", "MERGE", "MERGE_ON", "MIN", "MINUS",
            "MINUS2FUN", "MINUSFUN", "MINUSMINUS", "MISSING", "ML_COMMENT", "MOD",
            "MULTFUN", "MULTIPLY", "MULTMULT", "NE", "NEX", "NODUPLICATES", "NOT",
            "NOT_EXISTS_IN", "NOT_EXISTS_IN_ALL", "NOT_IN", "NROOT", "NULL_CONSTANT",
            "NULL_CONSTANT_TAG", "NVL", "ON", "OR", "ORDER", "ORDERBY_ASC_TYPE_TAG",
            "ORDERBY_DESC_TYPE_TAG", "OVERLAP", "PARAM_ARGUMENT_TAG", "PCSFILTER",
            "PERCENT", "PERCENTILE", "PLUS", "PLUS2FUN", "PLUSFUN", "PLUSPLUS", "POWER",
            "PRESENCE_CLAUSE_TAG", "PUT", "QUANTILENUM_TAG", "QUANTILETYPE_TAG", "RANK",
            "RELPERC", "RENAME", "RETURN", "ROLE", "ROLE_ATTRIBUTE_TAG", "ROLE_DIMENSION_TAG",
            "ROLE_MEASURE_TAG", "ROUND", "RULE_TAG", "SCALAR_ABS_TAG", "SCALAR_AND_TAG",
            "SCALAR_BETWEEN_CLAUSE_TAG", "SCALAR_BETWEEN_TAG", "SCALAR_CASE_ELSE_TAG",
            "SCALAR_CASE_IF_TAG", "SCALAR_CASE_TAG", "SCALAR_CONCAT_TAG", "SCALAR_DIVIDE_TAG",
            "SCALAR_EQ_CLAUSE_TAG", "SCALAR_EQ_TAG", "SCALAR_EXP_TAG", "SCALAR_GE_CLAUSE_TAG",
            "SCALAR_GE_TAG", "SCALAR_GT_CLAUSE_TAG", "SCALAR_GT_TAG", "SCALAR_INTDAY_TAG",
            "SCALAR_INTMONTH_TAG", "SCALAR_INTYEAR_TAG", "SCALAR_IN_SET_CLAUSE_TAG",
            "SCALAR_IN_SET_TAG", "SCALAR_LCASE_TAG", "SCALAR_LEN_TAG", "SCALAR_LE_CLAUSE_TAG",
            "SCALAR_LE_TAG", "SCALAR_LN_TAG", "SCALAR_LTRIM_TAG", "SCALAR_LT_CLAUSE_TAG",
            "SCALAR_LT_TAG", "SCALAR_MINUS_TAG", "SCALAR_MINUS_UNARY_TAG", "SCALAR_MOD_TAG",
            "SCALAR_MULTIPLY_TAG", "SCALAR_NE_CLAUSE_TAG", "SCALAR_NE_TAG", "SCALAR_NOT_BETWEEN_CLAUSE_TAG",
            "SCALAR_NOT_BETWEEN_TAG", "SCALAR_NOT_IN_SET_CLAUSE_TAG", "SCALAR_NOT_IN_SET_TAG",
            "SCALAR_NOT_TAG", "SCALAR_OR_TAG", "SCALAR_PERCENT_TAG", "SCALAR_PLUS_TAG",
            "SCALAR_POWER_TAG", "SCALAR_ROUND_TAG", "SCALAR_RTRIM_TAG", "SCALAR_SUBSTR_TAG",
            "SCALAR_TIMEDELAY_CLAUSE", "SCALAR_TRIM_TAG", "SCALAR_TRUNC_TAG", "SCALAR_UCASE_TAG",
            "SCALAR_XOR_TAG", "SETID_TAG", "SET_DIFF_TAG", "SET_INTERSECT_TAG", "SET_RANGE_TAG",
            "SET_UNION_TAG", "SEVERITY", "STD", "STRICT_CONDITION_TAG", "STRING_CONSTANT",
            "STRING_CONSTANT_TAG", "SUBSTR", "SUM", "SYSTIMESTAMP_TAG", "TAVG", "TCOUNT",
            "THEN", "THRESHOLD", "TIMEFILTER", "TIME_BEHAVIOR", "TIME_CLAUSE", "TMAX",
            "TMEDIAN", "TMIN", "TO", "TOLERANCE_TAG", "TO_CURR", "TRIM", "TRUNC",
            "TSTD", "TSUM", "TYPE", "UCASE", "UMEETS", "UNION", "USING", "VARID_TAG",
            "VARPAIR_CLAUSE_TAG", "VAR_DROP_TAG", "VAR_KEEP_TAG", "VAR_RANK_AS_TAG",
            "VAR_RENAME_AS_TAG", "VIRAL", "WITH", "WS", "XOR", "'#'", "'('", "')'",
            "'INF'", "'N'", "'SYSTIMESTAMP'", "'Y'", "'['", "']'", "'prod'"
    };
    public static final int EOF = -1;
    public static final int T__425 = 425;
    public static final int T__426 = 426;
    public static final int T__427 = 427;
    public static final int T__428 = 428;
    public static final int T__429 = 429;
    public static final int T__430 = 430;
    public static final int T__431 = 431;
    public static final int T__432 = 432;
    public static final int T__433 = 433;
    public static final int T__434 = 434;
    public static final int ABS = 4;
    public static final int AGGREGATE = 5;
    public static final int AGGREGATERULEID_TAG = 6;
    public static final int AGGREGATE_AVG_TAG = 7;
    public static final int AGGREGATE_COUNT_TAG = 8;
    public static final int AGGREGATE_MAX_TAG = 9;
    public static final int AGGREGATE_MEDIAN_TAG = 10;
    public static final int AGGREGATE_MIN_TAG = 11;
    public static final int AGGREGATE_PERCENTILE_TAG = 12;
    public static final int AGGREGATE_STD_TAG = 13;
    public static final int AGGREGATE_SUM_TAG = 14;
    public static final int ALL = 15;
    public static final int AND = 16;
    public static final int AS = 17;
    public static final int ASC = 18;
    public static final int ASSIGN = 19;
    public static final int ASSIGN_TAG = 20;
    public static final int ATTRCALC = 21;
    public static final int ATTRIBUTE = 22;
    public static final int AVG = 23;
    public static final int AVGPERIOD = 24;
    public static final int BETWEEN = 25;
    public static final int BOOLEAN_CONSTANT = 26;
    public static final int BOOLEAN_CONSTANT_TAG = 27;
    public static final int BREAK = 28;
    public static final int BREAKDATE = 29;
    public static final int BREAK_DATE_TAG = 30;
    public static final int BREAK_EXPR_TAG = 31;
    public static final int BY = 32;
    public static final int CALC = 33;
    public static final int CALC_CLAUSE_TAG = 34;
    public static final int CARTESIAN_PER = 35;
    public static final int CASE = 36;
    public static final int CHARLENGTH = 37;
    public static final int CHARSET_MATCH = 38;
    public static final int CHECK = 39;
    public static final int CODELIST_MATCH = 40;
    public static final int COMPATIBILITYRULEID_TAG = 41;
    public static final int COMPLCHECK = 42;
    public static final int COMPONENTID_TAG = 43;
    public static final int CONCAT = 44;
    public static final int COND = 45;
    public static final int COUNT = 46;
    public static final int COUNT_DISTINCT = 47;
    public static final int CURRCHANGE = 48;
    public static final int CURRENCY_TAG = 49;
    public static final int CURRENT_DATE = 50;
    public static final int DATARIF_TIMEDELAY_CLAUSE_TAG = 51;
    public static final int DATASETCONTEXTID_TAG = 52;
    public static final int DATASETID_TAG = 53;
    public static final int DATASET_ABS_TAG = 54;
    public static final int DATASET_AGGREGATION_CLAUSE_TAG = 55;
    public static final int DATASET_AGGREGATION_MEASURE_TAG = 56;
    public static final int DATASET_AGGREGATION_SUBCLAUSE_TAG = 57;
    public static final int DATASET_AND_TAG = 58;
    public static final int DATASET_A_TAG = 59;
    public static final int DATASET_BETWEEN_TAG = 60;
    public static final int DATASET_BREAK_TAG = 61;
    public static final int DATASET_B_TAG = 62;
    public static final int DATASET_CALC_CLAUSE_TAG = 63;
    public static final int DATASET_CASE_ELSE_TAG = 64;
    public static final int DATASET_CASE_IF_TAG = 65;
    public static final int DATASET_CASE_TAG = 66;
    public static final int DATASET_CHARLENGTH_TAG = 67;
    public static final int DATASET_CHARSET_TAG = 68;
    public static final int DATASET_CHECK_ALL_TAG = 69;
    public static final int DATASET_CHECK_CONDITION_TAG = 70;
    public static final int DATASET_CHECK_DISCREPANCY_TAG = 71;
    public static final int DATASET_CHECK_ERLEVEL_TAG = 72;
    public static final int DATASET_CHECK_ERRORCODE_TAG = 73;
    public static final int DATASET_CHECK_IMBALANCE_TAG = 74;
    public static final int DATASET_CHECK_LEVEL_TAG = 75;
    public static final int DATASET_CHECK_SEVERITY_TAG = 76;
    public static final int DATASET_CHECK_TAG = 77;
    public static final int DATASET_CHECK_THRESHOLD_TAG = 78;
    public static final int DATASET_CLAUSE_TAG = 79;
    public static final int DATASET_COMPARE_TAG = 80;
    public static final int DATASET_COMPLCHECK_TAG = 81;
    public static final int DATASET_CONCAT_TAG = 82;
    public static final int DATASET_CURRCHANGE_TAG = 83;
    public static final int DATASET_DIFFC_TAG = 84;
    public static final int DATASET_DIFFPERC_TAG = 85;
    public static final int DATASET_DIFF_TAG = 86;
    public static final int DATASET_DIVDIV_TAG = 87;
    public static final int DATASET_DIVIDEFUN_TAG = 88;
    public static final int DATASET_DIVIDE_TAG = 89;
    public static final int DATASET_DOT_MEASURE_TAG = 90;
    public static final int DATASET_DROP_CLAUSE_TAG = 91;
    public static final int DATASET_EQ_TAG = 92;
    public static final int DATASET_EXCHECK_COMPL_ERRORLEVEL_TAG = 93;
    public static final int DATASET_EXCHECK_COMPL_KEYSET_TAG = 94;
    public static final int DATASET_EXCHECK_TAG = 95;
    public static final int DATASET_EXCLUDE_CLAUSE_TAG = 96;
    public static final int DATASET_EXISTS_IN_ALL_TAG = 97;
    public static final int DATASET_EXISTS_IN_TAG = 98;
    public static final int DATASET_EXKEY_TAG = 99;
    public static final int DATASET_EXP_TAG = 100;
    public static final int DATASET_EX_TAG = 101;
    public static final int DATASET_FILTER_CLAUSE_TAG = 102;
    public static final int DATASET_FIRST_CLAUSE_TAG = 103;
    public static final int DATASET_GET_DATASETAGGR_TAG = 104;
    public static final int DATASET_GET_DATASETID_TAG = 105;
    public static final int DATASET_GET_FILTERS_CLAUSE_TAG = 106;
    public static final int DATASET_GET_FILTER_CLAUSE_TAG = 107;
    public static final int DATASET_GET_KEEP_CLAUSE_TAG = 108;
    public static final int DATASET_GET_MERGE_CLAUSE_TAG = 109;
    public static final int DATASET_GET_PCSFILTER_CLAUSE_TAG = 110;
    public static final int DATASET_GET_TAG = 111;
    public static final int DATASET_GET_TIMEDELAY_CLAUSE_TAG = 112;
    public static final int DATASET_GET_TIMEFILTER_CLAUSE_TAG = 113;
    public static final int DATASET_GE_TAG = 114;
    public static final int DATASET_GT_TAG = 115;
    public static final int DATASET_HMEETS_TAG = 116;
    public static final int DATASET_INBETWEEN_TAG = 117;
    public static final int DATASET_INCLUDE_CLAUSE_TAG = 118;
    public static final int DATASET_INDEXOF_TAG = 119;
    public static final int DATASET_INSET_TAG = 120;
    public static final int DATASET_INTERSECTC_TAG = 121;
    public static final int DATASET_INTERSECT_TAG = 122;
    public static final int DATASET_IN_TAG = 123;
    public static final int DATASET_KEEP_CLAUSE_TAG = 124;
    public static final int DATASET_KEYSET_TAG = 125;
    public static final int DATASET_LAG_CLAUSE_TAG = 126;
    public static final int DATASET_LAST_CLAUSE_TAG = 127;
    public static final int DATASET_LCASE_TAG = 128;
    public static final int DATASET_LEFTC_TAG = 129;
    public static final int DATASET_LEN_TAG = 130;
    public static final int DATASET_LEVEL = 131;
    public static final int DATASET_LE_TAG = 132;
    public static final int DATASET_LIST_TAG = 133;
    public static final int DATASET_LN_TAG = 134;
    public static final int DATASET_LOG_TAG = 135;
    public static final int DATASET_LTRIM_TAG = 136;
    public static final int DATASET_LT_TAG = 137;
    public static final int DATASET_MATCH_TAG = 138;
    public static final int DATASET_MAX_TAG = 139;
    public static final int DATASET_MERGE_TAG = 140;
    public static final int DATASET_MINUS2FUN_TAG = 141;
    public static final int DATASET_MINUSFUN_TAG = 142;
    public static final int DATASET_MINUSMINUS_TAG = 143;
    public static final int DATASET_MINUS_TAG = 144;
    public static final int DATASET_MINUS_UNARY_TAG = 145;
    public static final int DATASET_MIN_TAG = 146;
    public static final int DATASET_MOD_TAG = 147;
    public static final int DATASET_MULTIPLYFUN_TAG = 148;
    public static final int DATASET_MULTIPLY_TAG = 149;
    public static final int DATASET_MULTMULT_TAG = 150;
    public static final int DATASET_NEX_TAG = 151;
    public static final int DATASET_NE_TAG = 152;
    public static final int DATASET_NODUPLICATES_TAG = 153;
    public static final int DATASET_NOT_BETWEEN_TAG = 154;
    public static final int DATASET_NOT_EXISTS_IN_ALL_TAG = 155;
    public static final int DATASET_NOT_EXISTS_IN_TAG = 156;
    public static final int DATASET_NOT_IN_TAG = 157;
    public static final int DATASET_NOT_TAG = 158;
    public static final int DATASET_NROOT_TAG = 159;
    public static final int DATASET_NVL_TAG = 160;
    public static final int DATASET_OR_TAG = 161;
    public static final int DATASET_OVERLAP_TAG = 162;
    public static final int DATASET_PERCENT_TAG = 163;
    public static final int DATASET_PLUS2FUN_TAG = 164;
    public static final int DATASET_PLUSFUN_TAG = 165;
    public static final int DATASET_PLUSPLUS_TAG = 166;
    public static final int DATASET_PLUS_TAG = 167;
    public static final int DATASET_PLUS_UNARY_TAG = 168;
    public static final int DATASET_POWER_TAG = 169;
    public static final int DATASET_RANK_CLAUSE_TAG = 170;
    public static final int DATASET_RANK_ORDERBY_CLAUSE_TAG = 171;
    public static final int DATASET_RELPERC_TAG = 172;
    public static final int DATASET_RENAME_CLAUSE_TAG = 173;
    public static final int DATASET_ROUND_TAG = 174;
    public static final int DATASET_RTRIM_TAG = 175;
    public static final int DATASET_SUBSTR_TAG = 176;
    public static final int DATASET_TOP_CLAUSE_TAG = 177;
    public static final int DATASET_TOP_ORDERBY_CLAUSE_TAG = 178;
    public static final int DATASET_TRIM_TAG = 179;
    public static final int DATASET_TRUNC_TAG = 180;
    public static final int DATASET_TYPE_TAG = 181;
    public static final int DATASET_UCASE_TAG = 182;
    public static final int DATASET_UMEETS_TAG = 183;
    public static final int DATASET_UNIONC_TAG = 184;
    public static final int DATASET_UNION_TAG = 185;
    public static final int DATASET_USING_CLAUSE_TAG = 186;
    public static final int DATASET_VALIDATE_CONDITIONTYPE_TAG = 187;
    public static final int DATASET_XOR_TAG = 188;
    public static final int DESC = 189;
    public static final int DIFF = 190;
    public static final int DIFFPERC = 191;
    public static final int DIMENSION = 192;
    public static final int DIMENSIONID_TAG = 193;
    public static final int DISCREPANCY = 194;
    public static final int DIVDIV = 195;
    public static final int DIVFUN = 196;
    public static final int DIVIDE = 197;
    public static final int DROP = 198;
    public static final int ELSE = 199;
    public static final int ELSEIF = 200;
    public static final int ENDPERIOD = 201;
    public static final int EOL = 202;
    public static final int EQ = 203;
    public static final int ERLEVEL = 204;
    public static final int ERRORCODE = 205;
    public static final int EVAL = 206;
    public static final int EX = 207;
    public static final int EXCHECK = 208;
    public static final int EXCLUDE = 209;
    public static final int EXISTS_IN = 210;
    public static final int EXISTS_IN_ALL = 211;
    public static final int EXKEY = 212;
    public static final int EXP = 213;
    public static final int FILTER = 214;
    public static final int FIRST = 215;
    public static final int FLOATEXP = 216;
    public static final int FLOAT_CONSTANT = 217;
    public static final int FLOAT_CONSTANT_TAG = 218;
    public static final int FROM_CURR = 219;
    public static final int GE = 220;
    public static final int GET = 221;
    public static final int GT = 222;
    public static final int HIERARCHY = 223;
    public static final int HIGHPERC_TAG = 224;
    public static final int HMEETS = 225;
    public static final int IDENTIFIER = 226;
    public static final int IF = 227;
    public static final int IMBALANCE = 228;
    public static final int IN = 229;
    public static final int INBALANCE = 230;
    public static final int INCLUDE = 231;
    public static final int INDEXOF = 232;
    public static final int INTDAY = 233;
    public static final int INTEGER_CONSTANT = 234;
    public static final int INTERSECT = 235;
    public static final int INTMONTH = 236;
    public static final int INTYEAR = 237;
    public static final int INT_CONSTANT_TAG = 238;
    public static final int ISNULL = 239;
    public static final int IS_COMPL_LIST_TAG = 240;
    public static final int JOIN_CONDITION_TAG = 241;
    public static final int JOIN_LEFTARGUMENT_TAG = 242;
    public static final int JOIN_LEFTVAR_TAG = 243;
    public static final int JOIN_RENAME_TAG = 244;
    public static final int JOIN_RIGHTARGUMENT_TAG = 245;
    public static final int JOIN_RIGHTVAR_TAG = 246;
    public static final int KEEP = 247;
    public static final int KEY = 248;
    public static final int KEYS = 249;
    public static final int KEYSET_COMP_TAG = 250;
    public static final int KEYSET_KEYS_TAG = 251;
    public static final int KEYSET_LIST_TAG = 252;
    public static final int KEYSET_VARPARAM_TAG = 253;
    public static final int KEYSET_VARS_TAG = 254;
    public static final int KEYSET_VAR_LIST_TAG = 255;
    public static final int KEYSET_VAR_SETID_TAG = 256;
    public static final int KEYSET_VAR_TAG = 257;
    public static final int LAGLIST_TAG = 258;
    public static final int LAST = 259;
    public static final int LCASE = 260;
    public static final int LE = 261;
    public static final int LEFTC = 262;
    public static final int LEN = 263;
    public static final int LETTER = 264;
    public static final int LN = 265;
    public static final int LOG = 266;
    public static final int LOWPERC_TAG = 267;
    public static final int LT = 268;
    public static final int MATCHES_INVALID = 269;
    public static final int MATCHES_VALID = 270;
    public static final int MATCHKEY = 271;
    public static final int MAX = 272;
    public static final int MEASURE = 273;
    public static final int MEDIAN = 274;
    public static final int MERGE = 275;
    public static final int MERGE_ON = 276;
    public static final int MIN = 277;
    public static final int MINUS = 278;
    public static final int MINUS2FUN = 279;
    public static final int MINUSFUN = 280;
    public static final int MINUSMINUS = 281;
    public static final int MISSING = 282;
    public static final int ML_COMMENT = 283;
    public static final int MOD = 284;
    public static final int MULTFUN = 285;
    public static final int MULTIPLY = 286;
    public static final int MULTMULT = 287;
    public static final int NE = 288;
    public static final int NEX = 289;
    public static final int NODUPLICATES = 290;
    public static final int NOT = 291;
    public static final int NOT_EXISTS_IN = 292;
    public static final int NOT_EXISTS_IN_ALL = 293;
    public static final int NOT_IN = 294;
    public static final int NROOT = 295;
    public static final int NULL_CONSTANT = 296;
    public static final int NULL_CONSTANT_TAG = 297;
    public static final int NVL = 298;
    public static final int ON = 299;
    public static final int OR = 300;
    public static final int ORDER = 301;
    public static final int ORDERBY_ASC_TYPE_TAG = 302;
    public static final int ORDERBY_DESC_TYPE_TAG = 303;
    public static final int OVERLAP = 304;
    public static final int PARAM_ARGUMENT_TAG = 305;
    public static final int PCSFILTER = 306;
    public static final int PERCENT = 307;
    public static final int PERCENTILE = 308;
    public static final int PLUS = 309;
    public static final int PLUS2FUN = 310;
    public static final int PLUSFUN = 311;
    public static final int PLUSPLUS = 312;
    public static final int POWER = 313;
    public static final int PRESENCE_CLAUSE_TAG = 314;
    public static final int PUT = 315;
    public static final int QUANTILENUM_TAG = 316;
    public static final int QUANTILETYPE_TAG = 317;
    public static final int RANK = 318;
    public static final int RELPERC = 319;
    public static final int RENAME = 320;
    public static final int RETURN = 321;
    public static final int ROLE = 322;
    public static final int ROLE_ATTRIBUTE_TAG = 323;
    public static final int ROLE_DIMENSION_TAG = 324;
    public static final int ROLE_MEASURE_TAG = 325;
    public static final int ROUND = 326;
    public static final int RULE_TAG = 327;
    public static final int SCALAR_ABS_TAG = 328;
    public static final int SCALAR_AND_TAG = 329;
    public static final int SCALAR_BETWEEN_CLAUSE_TAG = 330;
    public static final int SCALAR_BETWEEN_TAG = 331;
    public static final int SCALAR_CASE_ELSE_TAG = 332;
    public static final int SCALAR_CASE_IF_TAG = 333;
    public static final int SCALAR_CASE_TAG = 334;
    public static final int SCALAR_CONCAT_TAG = 335;
    public static final int SCALAR_DIVIDE_TAG = 336;
    public static final int SCALAR_EQ_CLAUSE_TAG = 337;
    public static final int SCALAR_EQ_TAG = 338;
    public static final int SCALAR_EXP_TAG = 339;
    public static final int SCALAR_GE_CLAUSE_TAG = 340;
    public static final int SCALAR_GE_TAG = 341;
    public static final int SCALAR_GT_CLAUSE_TAG = 342;
    public static final int SCALAR_GT_TAG = 343;
    public static final int SCALAR_INTDAY_TAG = 344;
    public static final int SCALAR_INTMONTH_TAG = 345;
    public static final int SCALAR_INTYEAR_TAG = 346;
    public static final int SCALAR_IN_SET_CLAUSE_TAG = 347;
    public static final int SCALAR_IN_SET_TAG = 348;
    public static final int SCALAR_LCASE_TAG = 349;
    public static final int SCALAR_LEN_TAG = 350;
    public static final int SCALAR_LE_CLAUSE_TAG = 351;
    public static final int SCALAR_LE_TAG = 352;
    public static final int SCALAR_LN_TAG = 353;
    public static final int SCALAR_LTRIM_TAG = 354;
    public static final int SCALAR_LT_CLAUSE_TAG = 355;
    public static final int SCALAR_LT_TAG = 356;
    public static final int SCALAR_MINUS_TAG = 357;
    public static final int SCALAR_MINUS_UNARY_TAG = 358;
    public static final int SCALAR_MOD_TAG = 359;
    public static final int SCALAR_MULTIPLY_TAG = 360;
    public static final int SCALAR_NE_CLAUSE_TAG = 361;
    public static final int SCALAR_NE_TAG = 362;
    public static final int SCALAR_NOT_BETWEEN_CLAUSE_TAG = 363;
    public static final int SCALAR_NOT_BETWEEN_TAG = 364;
    public static final int SCALAR_NOT_IN_SET_CLAUSE_TAG = 365;
    public static final int SCALAR_NOT_IN_SET_TAG = 366;
    public static final int SCALAR_NOT_TAG = 367;
    public static final int SCALAR_OR_TAG = 368;
    public static final int SCALAR_PERCENT_TAG = 369;
    public static final int SCALAR_PLUS_TAG = 370;
    public static final int SCALAR_POWER_TAG = 371;
    public static final int SCALAR_ROUND_TAG = 372;
    public static final int SCALAR_RTRIM_TAG = 373;
    public static final int SCALAR_SUBSTR_TAG = 374;
    public static final int SCALAR_TIMEDELAY_CLAUSE = 375;
    public static final int SCALAR_TRIM_TAG = 376;
    public static final int SCALAR_TRUNC_TAG = 377;
    public static final int SCALAR_UCASE_TAG = 378;
    public static final int SCALAR_XOR_TAG = 379;
    public static final int SETID_TAG = 380;
    public static final int SET_DIFF_TAG = 381;
    public static final int SET_INTERSECT_TAG = 382;
    public static final int SET_RANGE_TAG = 383;
    public static final int SET_UNION_TAG = 384;
    public static final int SEVERITY = 385;
    public static final int STD = 386;
    public static final int STRICT_CONDITION_TAG = 387;
    public static final int STRING_CONSTANT = 388;
    public static final int STRING_CONSTANT_TAG = 389;
    public static final int SUBSTR = 390;
    public static final int SUM = 391;
    public static final int SYSTIMESTAMP_TAG = 392;
    public static final int TAVG = 393;
    public static final int TCOUNT = 394;
    public static final int THEN = 395;
    public static final int THRESHOLD = 396;
    public static final int TIMEFILTER = 397;
    public static final int TIME_BEHAVIOR = 398;
    public static final int TIME_CLAUSE = 399;
    public static final int TMAX = 400;
    public static final int TMEDIAN = 401;
    public static final int TMIN = 402;
    public static final int TO = 403;
    public static final int TOLERANCE_TAG = 404;
    public static final int TO_CURR = 405;
    public static final int TRIM = 406;
    public static final int TRUNC = 407;
    public static final int TSTD = 408;
    public static final int TSUM = 409;
    public static final int TYPE = 410;
    public static final int UCASE = 411;
    public static final int UMEETS = 412;
    public static final int UNION = 413;
    public static final int USING = 414;
    public static final int VARID_TAG = 415;
    public static final int VARPAIR_CLAUSE_TAG = 416;
    public static final int VAR_DROP_TAG = 417;
    public static final int VAR_KEEP_TAG = 418;
    public static final int VAR_RANK_AS_TAG = 419;
    public static final int VAR_RENAME_AS_TAG = 420;
    public static final int VIRAL = 421;
    public static final int WITH = 422;
    public static final int WS = 423;
    public static final int XOR = 424;
    public static final BitSet FOLLOW_statement_in_start4526 = new BitSet(new long[]{0x0000000004000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402000000L, 0x0000010000000000L, 0x0000000000000000L, 0x0000040000000010L});

    // delegators
    public static final BitSet FOLLOW_EOF_in_start4529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableRef_in_statement4567 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ASSIGN_in_statement4569 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_statement4571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprOr_in_expr4610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_expr4616 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_exprOr_in_expr4618 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000800L});
    public static final BitSet FOLLOW_THEN_in_expr4620 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_exprOr_in_expr4622 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000180L});
    public static final BitSet FOLLOW_ELSEIF_in_expr4625 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_exprOr_in_expr4627 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000800L});
    public static final BitSet FOLLOW_THEN_in_expr4629 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    ;
    public static final BitSet FOLLOW_exprOr_in_expr4631 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000180L});
    // $ANTLR end "start"
    public static final BitSet FOLLOW_ELSE_in_expr4636 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    ;
    public static final BitSet FOLLOW_exprOr_in_expr4638 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000080L});
    // $ANTLR end "statement"
    public static final BitSet FOLLOW_exprAnd_in_exprOr4659 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000100000000000L, 0x0000000000000000L, 0x0000010000000000L});
    ;
    public static final BitSet FOLLOW_OR_in_exprOr4689 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "expr"
    public static final BitSet FOLLOW_exprAnd_in_exprOr4693 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000100000000000L, 0x0000000000000000L, 0x0000010000000000L});
    ;
    public static final BitSet FOLLOW_XOR_in_exprOr4751 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "exprOr"
    public static final BitSet FOLLOW_exprAnd_in_exprOr4755 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000100000000000L, 0x0000000000000000L, 0x0000010000000000L});
    ;
    public static final BitSet FOLLOW_exprEq_in_exprAnd4816 = new BitSet(new long[]{0x0000000000010002L});
    // $ANTLR end "exprAnd"
    public static final BitSet FOLLOW_AND_in_exprAnd4838 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    ;
    public static final BitSet FOLLOW_exprEq_in_exprAnd4842 = new BitSet(new long[]{0x0000000000010002L});
    // $ANTLR end "exprEq"
    public static final BitSet FOLLOW_exprExists_in_exprEq4893 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000010000800L, 0x0000000100000020L});
    ;
    public static final BitSet FOLLOW_EQ_in_exprEq4932 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "exprExists"
    public static final BitSet FOLLOW_NE_in_exprEq4944 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    ;
    public static final BitSet FOLLOW_LE_in_exprEq4956 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "exprComp"
    public static final BitSet FOLLOW_GE_in_exprEq4968 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    ;
    public static final BitSet FOLLOW_exprExists_in_exprEq4986 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000010000800L, 0x0000000100000020L});
    // $ANTLR end "exprAdd"
    public static final BitSet FOLLOW_exprComp_in_exprExists5042 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x00000000000C0000L, 0x0000003000000000L});
    ;
    public static final BitSet FOLLOW_EXISTS_IN_in_exprExists5072 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "exprMultiply"
    public static final BitSet FOLLOW_exprComp_in_exprExists5076 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x00000000000C0000L, 0x0000003000000000L});
    ;
    public static final BitSet FOLLOW_EXISTS_IN_ALL_in_exprExists5134 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "exprFactor"
    public static final BitSet FOLLOW_exprComp_in_exprExists5138 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x00000000000C0000L, 0x0000003000000000L});
    ;
    public static final BitSet FOLLOW_NOT_EXISTS_IN_in_exprExists5196 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "exprMember"
    public static final BitSet FOLLOW_exprComp_in_exprExists5200 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x00000000000C0000L, 0x0000003000000000L});
    ;
    public static final BitSet FOLLOW_NOT_EXISTS_IN_ALL_in_exprExists5258 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "exprAtom"
    public static final BitSet FOLLOW_exprComp_in_exprExists5262 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x00000000000C0000L, 0x0000003000000000L});
    ;
    public static final BitSet FOLLOW_exprAdd_in_exprComp5323 = new BitSet(new long[]{0x0000000002000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000002040000000L, 0x0000000800001000L});
    // $ANTLR end "variableRef"
    public static final BitSet FOLLOW_IN_in_exprComp5353 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x4000000000000000L, 0x0000080000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040020000000L});
    ;
    public static final BitSet FOLLOW_setExpr_in_exprComp5357 = new BitSet(new long[]{0x0000000002000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000002040000000L, 0x0000000800001000L});
    // $ANTLR end "getExpr"
    public static final BitSet FOLLOW_NOT_in_exprComp5415 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000002000000000L});
    ;
    public static final BitSet FOLLOW_IN_in_exprComp5417 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x4000000000000000L, 0x0000080000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040020000000L});
    // $ANTLR end "persistentDatasetID"
    public static final BitSet FOLLOW_setExpr_in_exprComp5421 = new BitSet(new long[]{0x0000000002000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000002040000000L, 0x0000000800001000L});
    ;
    public static final BitSet FOLLOW_GT_in_exprComp5500 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "putExpr"
    public static final BitSet FOLLOW_LT_in_exprComp5514 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    ;
    public static final BitSet FOLLOW_exprAdd_in_exprComp5536 = new BitSet(new long[]{0x0000000002000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000002040000000L, 0x0000000800001000L});
    // $ANTLR end "evalExpr"
    public static final BitSet FOLLOW_BETWEEN_in_exprComp5601 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    ;
    public static final BitSet FOLLOW_exprAdd_in_exprComp5605 = new BitSet(new long[]{0x0000000000010000L});
    // $ANTLR end "validationExpr"
    public static final BitSet FOLLOW_AND_in_exprComp5607 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    ;
    public static final BitSet FOLLOW_exprAdd_in_exprComp5611 = new BitSet(new long[]{0x0000000002000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000002040000000L, 0x0000000800001000L});
    // $ANTLR end "mergeExpr"
    public static final BitSet FOLLOW_NOT_in_exprComp5672 = new BitSet(new long[]{0x0000000002000000L});
    ;
    public static final BitSet FOLLOW_BETWEEN_in_exprComp5674 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "hierarchyExpr"
    public static final BitSet FOLLOW_exprAdd_in_exprComp5678 = new BitSet(new long[]{0x0000000000010000L});
    ;
    public static final BitSet FOLLOW_AND_in_exprComp5680 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "mappingExpr"
    public static final BitSet FOLLOW_exprAdd_in_exprComp5684 = new BitSet(new long[]{0x0000000002000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000002040000000L, 0x0000000800001000L});
    ;
    public static final BitSet FOLLOW_exprMultiply_in_exprAdd5748 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0120000002400000L});
    // $ANTLR end "aggrParam"
    public static final BitSet FOLLOW_PLUSPLUS_in_exprAdd5788 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    ;
    public static final BitSet FOLLOW_exprMultiply_in_exprAdd5792 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0120000002400000L});
    // $ANTLR end "aggregategetClause"
    public static final BitSet FOLLOW_MINUSMINUS_in_exprAdd5862 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    ;
    public static final BitSet FOLLOW_exprMultiply_in_exprAdd5866 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0120000002400000L});
    // $ANTLR end "aggregateClause"
    public static final BitSet FOLLOW_PLUS_in_exprAdd5936 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    ;
    public static final BitSet FOLLOW_exprMultiply_in_exprAdd5940 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0120000002400000L});
    // $ANTLR end "aggrFunctionClause"
    public static final BitSet FOLLOW_MINUS_in_exprAdd6010 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    ;
    public static final BitSet FOLLOW_exprMultiply_in_exprAdd6014 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0120000002400000L});
    // $ANTLR end "datasetIDGroup"
    public static final BitSet FOLLOW_exprFactor_in_exprMultiply6087 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000028L, 0x00080000C0000000L});
    ;
    public static final BitSet FOLLOW_MULTMULT_in_exprMultiply6127 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "caseElseClause"
    public static final BitSet FOLLOW_exprFactor_in_exprMultiply6131 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000028L, 0x00080000C0000000L});
    ;
    public static final BitSet FOLLOW_DIVDIV_in_exprMultiply6201 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "caseCaseClause"
    public static final BitSet FOLLOW_exprFactor_in_exprMultiply6205 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000028L, 0x00080000C0000000L});
    ;
    public static final BitSet FOLLOW_MULTIPLY_in_exprMultiply6275 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "getFiltersClause"
    public static final BitSet FOLLOW_exprFactor_in_exprMultiply6279 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000028L, 0x00080000C0000000L});
    ;
    public static final BitSet FOLLOW_DIVIDE_in_exprMultiply6349 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "getFilterClause"
    public static final BitSet FOLLOW_exprFactor_in_exprMultiply6353 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000028L, 0x00080000C0000000L});
    ;
    public static final BitSet FOLLOW_PERCENT_in_exprMultiply6423 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "datasetClause"
    public static final BitSet FOLLOW_exprFactor_in_exprMultiply6427 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000028L, 0x00080000C0000000L});
    ;
    public static final BitSet FOLLOW_exprMember_in_exprFactor6500 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "aggrFilterClause"
    public static final BitSet FOLLOW_PLUS_in_exprFactor6518 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A0005C014290690L, 0x0000000000000040L, 0x000004002CC00050L});
    ;
    public static final BitSet FOLLOW_exprMember_in_exprFactor6520 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "filterClause"
    public static final BitSet FOLLOW_MINUS_in_exprFactor6550 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A0005C014290690L, 0x0000000000000040L, 0x000004002CC00050L});
    ;
    public static final BitSet FOLLOW_exprMember_in_exprFactor6552 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "ascdescClause"
    public static final BitSet FOLLOW_NOT_in_exprFactor6581 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A0005C014290690L, 0x0000000000000040L, 0x000004002CC00050L});
    ;
    public static final BitSet FOLLOW_exprMember_in_exprFactor6583 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "renameClause"
    public static final BitSet FOLLOW_exprAtom_in_exprMember6601 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0001020000000000L});
    ;
    public static final BitSet FOLLOW_432_in_exprMember6604 = new BitSet(new long[]{0x0000100204200010L, 0x0000000000000000L, 0x0000000000000000L, 0x0080040452600840L, 0x02200109104012B0L, 0x0000000000000041L, 0x0000440008C00050L});
    // $ANTLR end "aggrFunction"
    public static final BitSet FOLLOW_datasetClause_in_exprMember6606 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0002000000000000L});
    ;
    public static final BitSet FOLLOW_433_in_exprMember6608 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000020000000000L});
    // $ANTLR end "percentileFunction"
    public static final BitSet FOLLOW_425_in_exprMember6612 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000400000000L});
    ;
    public static final BitSet FOLLOW_componentID_in_exprMember6614 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "calcClause"
    public static final BitSet FOLLOW_ROUND_in_exprAtom6635 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    ;
    public static final BitSet FOLLOW_426_in_exprAtom6637 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "attrCalcClause"
    public static final BitSet FOLLOW_expr_in_exprAtom6641 = new BitSet(new long[]{0x0000000800000000L});
    ;
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom6643 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    // $ANTLR end "calcClauseItem"
    public static final BitSet FOLLOW_INTEGER_CONSTANT_in_exprAtom6647 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    ;
    public static final BitSet FOLLOW_427_in_exprAtom6649 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "calcExpr"
    public static final BitSet FOLLOW_MIN_in_exprAtom6678 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    ;
    public static final BitSet FOLLOW_426_in_exprAtom6680 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "dropClause"
    public static final BitSet FOLLOW_expr_in_exprAtom6684 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    ;
    public static final BitSet FOLLOW_427_in_exprAtom6686 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "dropClauseItem"
    public static final BitSet FOLLOW_MAX_in_exprAtom6717 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    ;
    public static final BitSet FOLLOW_426_in_exprAtom6719 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "keepClause"
    public static final BitSet FOLLOW_expr_in_exprAtom6723 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    ;
    public static final BitSet FOLLOW_427_in_exprAtom6725 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "keepClauseItem"
    public static final BitSet FOLLOW_ABS_in_exprAtom6756 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    ;
    public static final BitSet FOLLOW_426_in_exprAtom6758 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "compareClause"
    public static final BitSet FOLLOW_expr_in_exprAtom6762 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    ;
    public static final BitSet FOLLOW_427_in_exprAtom6764 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "inBetweenClause"
    public static final BitSet FOLLOW_EXP_in_exprAtom6795 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    ;
    public static final BitSet FOLLOW_426_in_exprAtom6797 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "dimClause"
    public static final BitSet FOLLOW_expr_in_exprAtom6801 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    ;
    public static final BitSet FOLLOW_427_in_exprAtom6803 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "varRole"
    public static final BitSet FOLLOW_LN_in_exprAtom6834 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    ;
    public static final BitSet FOLLOW_426_in_exprAtom6836 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "bScalarExpr"
    public static final BitSet FOLLOW_expr_in_exprAtom6840 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    ;
    public static final BitSet FOLLOW_427_in_exprAtom6842 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "sExprOr"
    public static final BitSet FOLLOW_LOG_in_exprAtom6873 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    ;
    public static final BitSet FOLLOW_426_in_exprAtom6875 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "sExprAnd"
    public static final BitSet FOLLOW_expr_in_exprAtom6879 = new BitSet(new long[]{0x0000000800000000L});
    ;
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom6881 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    // $ANTLR end "sExprPredicate"
    public static final BitSet FOLLOW_logBase_in_exprAtom6885 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    ;
    public static final BitSet FOLLOW_427_in_exprAtom6887 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "scalarExpr"
    public static final BitSet FOLLOW_TRUNC_in_exprAtom6921 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    ;
    public static final BitSet FOLLOW_426_in_exprAtom6923 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "sExprAdd"
    public static final BitSet FOLLOW_expr_in_exprAtom6927 = new BitSet(new long[]{0x0000000800000000L});
    ;
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom6929 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    // $ANTLR end "sExprFactor"
    public static final BitSet FOLLOW_INTEGER_CONSTANT_in_exprAtom6933 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    ;
    public static final BitSet FOLLOW_427_in_exprAtom6935 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "sExprAtom"
    public static final BitSet FOLLOW_POWER_in_exprAtom6969 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    ;
    public static final BitSet FOLLOW_426_in_exprAtom6971 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "componentID"
    public static final BitSet FOLLOW_expr_in_exprAtom6975 = new BitSet(new long[]{0x0000000800000000L});
    ;
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom6977 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L, 0x0020000000400000L});
    // $ANTLR end "compOpScalarClause"
    public static final BitSet FOLLOW_powerExponent_in_exprAtom6981 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    ;
    public static final BitSet FOLLOW_427_in_exprAtom6983 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "logBase"
    public static final BitSet FOLLOW_NROOT_in_exprAtom7017 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    ;
    public static final BitSet FOLLOW_426_in_exprAtom7019 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "powerExponent"
    public static final BitSet FOLLOW_expr_in_exprAtom7023 = new BitSet(new long[]{0x0000000800000000L});
    ;
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom7025 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    // $ANTLR end "exponent"
    public static final BitSet FOLLOW_INTEGER_CONSTANT_in_exprAtom7029 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    ;
    public static final BitSet FOLLOW_427_in_exprAtom7031 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "setExpr"
    public static final BitSet FOLLOW_LEN_in_exprAtom7065 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    ;
    public static final BitSet FOLLOW_426_in_exprAtom7067 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "varID"
    public static final BitSet FOLLOW_expr_in_exprAtom7069 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    ;
    public static final BitSet FOLLOW_427_in_exprAtom7071 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "compOp"
    public static final BitSet FOLLOW_TRIM_in_exprAtom7101 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    ;
    public static final BitSet FOLLOW_426_in_exprAtom7103 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "compOpScalar"
    public static final BitSet FOLLOW_expr_in_exprAtom7105 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    ;
    public static final BitSet FOLLOW_427_in_exprAtom7107 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "constant"
    public static final BitSet FOLLOW_UCASE_in_exprAtom7137 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    ;
    public static final BitSet FOLLOW_426_in_exprAtom7139 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    // $ANTLR end "isCompl"
    public static final BitSet FOLLOW_expr_in_exprAtom7141 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    ;
    public static final BitSet FOLLOW_427_in_exprAtom7143 = new BitSet(new long[]{0x0000000000000002L});
    // $ANTLR end "lhperc"

    // Delegated rules
    public static final BitSet FOLLOW_LCASE_in_exprAtom7173 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_exprAtom7175 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7177 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_exprAtom7179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUBSTR_in_exprAtom7216 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_exprAtom7218 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7222 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom7224 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_exprAtom7228 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom7231 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_exprAtom7235 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_exprAtom7239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INDEXOF_in_exprAtom7280 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_exprAtom7282 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7286 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom7288 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_STRING_CONSTANT_in_exprAtom7292 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_exprAtom7294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MISSING_in_exprAtom7328 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_exprAtom7330 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7334 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_exprAtom7336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARSET_MATCH_in_exprAtom7374 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_exprAtom7376 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7380 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom7382 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_STRING_CONSTANT_in_exprAtom7386 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom7389 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_ALL_in_exprAtom7391 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_exprAtom7395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CODELIST_MATCH_in_exprAtom7439 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_exprAtom7441 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7445 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom7447 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x4000000000000000L, 0x0000080000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040020000000L});
    public static final BitSet FOLLOW_setExpr_in_exprAtom7451 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom7454 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_ALL_in_exprAtom7456 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_exprAtom7460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHARLENGTH_in_exprAtom7497 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_exprAtom7499 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7503 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_exprAtom7505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_exprAtom7536 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_exprAtom7538 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7542 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_exprAtom7544 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000800L});
    public static final BitSet FOLLOW_EQ_in_exprAtom7546 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_STRING_CONSTANT_in_exprAtom7550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTERSECT_in_exprAtom7618 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_exprAtom7620 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7624 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom7626 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7630 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_exprAtom7632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNION_in_exprAtom7660 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_exprAtom7662 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7666 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom7668 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7672 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_exprAtom7674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIFF_in_exprAtom7702 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_exprAtom7704 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7708 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom7710 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7714 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_exprAtom7716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_IN_in_exprAtom7744 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_exprAtom7746 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7750 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom7752 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7756 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_exprAtom7758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ISNULL_in_exprAtom7786 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_exprAtom7788 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7792 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_exprAtom7794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NVL_in_exprAtom7819 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_exprAtom7821 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7825 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom7827 = new BitSet(new long[]{0x0000000004000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L, 0x0000010000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_constant_in_exprAtom7831 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_exprAtom7833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MOD_in_exprAtom7862 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_exprAtom7864 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7868 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_exprAtom7870 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_exprAtom7874 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_exprAtom7876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_validationExpr_in_exprAtom7908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_getExpr_in_exprAtom7914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableRef_in_exprAtom7920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_putExpr_in_exprAtom7926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_evalExpr_in_exprAtom7932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mergeExpr_in_exprAtom7938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_hierarchyExpr_in_exprAtom7944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_426_in_variableRef7962 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_exprOr_in_variableRef7965 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_variableRef7967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varID_in_variableRef7974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constant_in_variableRef7980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GET_in_getExpr7999 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_getExpr8001 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_persistentDatasetID_in_getExpr8003 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_getExpr8006 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_persistentDatasetID_in_getExpr8008 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_getExpr8013 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0080000000000000L});
    public static final BitSet FOLLOW_keepClause_in_getExpr8015 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_getExpr8020 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402600000L, 0x0220010810400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_filterClause_in_getExpr8022 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_getExpr8027 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_aggregategetClause_in_getExpr8029 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_getExpr8033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_CONSTANT_in_persistentDatasetID8048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PUT_in_putExpr8063 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_putExpr8065 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_putExpr8067 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_putExpr8069 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_persistentDatasetID_in_putExpr8071 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_putExpr8073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EVAL_in_evalExpr8088 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_evalExpr8090 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_STRING_CONSTANT_in_evalExpr8092 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_evalExpr8095 = new BitSet(new long[]{0x0000000004000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402000000L, 0x0000010000000000L, 0x0000000000000000L, 0x0000040000000010L});
    public static final BitSet FOLLOW_variableRef_in_evalExpr8097 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_evalExpr8101 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_persistentDatasetID_in_evalExpr8103 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_evalExpr8105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHECK_in_validationExpr8120 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_validationExpr8122 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_exprOr_in_validationExpr8126 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_validationExpr8129 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000001000000000L});
    public static final BitSet FOLLOW_IMBALANCE_in_validationExpr8131 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_validationExpr8133 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_exprOr_in_validationExpr8137 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_validationExpr8139 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_validationExpr8144 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000001000L});
    public static final BitSet FOLLOW_ERLEVEL_in_validationExpr8146 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_validationExpr8148 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_exprOr_in_validationExpr8152 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_validationExpr8154 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_validationExpr8159 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000002000L});
    public static final BitSet FOLLOW_ERRORCODE_in_validationExpr8161 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_validationExpr8163 = new BitSet(new long[]{0x0000000004000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L, 0x0000010000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_constant_in_validationExpr8167 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_validationExpr8169 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_validationExpr8174 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000001000L});
    public static final BitSet FOLLOW_THRESHOLD_in_validationExpr8176 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_validationExpr8178 = new BitSet(new long[]{0x0000000004000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L, 0x0000010000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_constant_in_validationExpr8182 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_validationExpr8184 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_validationExpr8189 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_ALL_in_validationExpr8193 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_validationExpr8197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MERGE_in_mergeExpr8340 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_mergeExpr8342 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_mergeExpr8344 = new BitSet(new long[]{0x0000000000020000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_AS_in_mergeExpr8346 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_STRING_CONSTANT_in_mergeExpr8349 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_mergeExpr8352 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_mergeExpr8354 = new BitSet(new long[]{0x0000000000020000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_AS_in_mergeExpr8356 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_STRING_CONSTANT_in_mergeExpr8359 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_mergeExpr8363 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_ON_in_mergeExpr8365 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_mergeExpr8367 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_mergeExpr8369 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_mergeExpr8371 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_mergeExpr8373 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_mergeExpr8375 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_mergeExpr8377 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_mergeExpr8380 = new BitSet(new long[]{0x0000000000020000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_AS_in_mergeExpr8382 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_STRING_CONSTANT_in_mergeExpr8385 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_mergeExpr8389 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_mergeExpr8391 = new BitSet(new long[]{0x0000000000020000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_AS_in_mergeExpr8393 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_STRING_CONSTANT_in_mergeExpr8396 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_mergeExpr8400 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_mergeExpr8402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HIERARCHY_in_hierarchyExpr8417 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_hierarchyExpr8419 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D0CA2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_expr_in_hierarchyExpr8421 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_hierarchyExpr8423 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000400000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_hierarchyExpr8425 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_hierarchyExpr8427 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000010L});
    public static final BitSet FOLLOW_STRING_CONSTANT_in_hierarchyExpr8437 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_mappingExpr_in_hierarchyExpr8446 = new BitSet(new long[]{0x0000000800020000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_hierarchyExpr8449 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_mappingExpr_in_hierarchyExpr8451 = new BitSet(new long[]{0x0000000800020000L});
    public static final BitSet FOLLOW_AS_in_hierarchyExpr8455 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_STRING_CONSTANT_in_hierarchyExpr8457 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_hierarchyExpr8466 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_BOOLEAN_CONSTANT_in_hierarchyExpr8468 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_hierarchyExpr8471 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0004000000000080L});
    public static final BitSet FOLLOW_aggrParam_in_hierarchyExpr8473 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_hierarchyExpr8477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_426_in_mappingExpr8492 = new BitSet(new long[]{0x0000000004000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L, 0x0000010000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_constant_in_mappingExpr8494 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_mappingExpr8496 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_INTEGER_CONSTANT_in_mappingExpr8498 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_mappingExpr8500 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0020000000400000L});
    public static final BitSet FOLLOW_set_in_mappingExpr8504 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_mappingExpr8526 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000080000L});
    public static final BitSet FOLLOW_TO_in_mappingExpr8528 = new BitSet(new long[]{0x0000000004000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L, 0x0000010000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_constant_in_mappingExpr8530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AGGREGATE_in_aggregategetClause8566 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_aggregategetClause8568 = new BitSet(new long[]{0x0000C00000800000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000250000L, 0x0000000000000000L, 0x0000000000000084L});
    public static final BitSet FOLLOW_aggrFunction_in_aggregategetClause8570 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_aggregategetClause8572 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_aggregategetClause8574 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_aggregategetClause8576 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_aggregategetClause8579 = new BitSet(new long[]{0x0000C00000800000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000250000L, 0x0000000000000000L, 0x0000000000000084L});
    public static final BitSet FOLLOW_aggrFunction_in_aggregategetClause8581 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_aggregategetClause8583 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_aggregategetClause8585 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_aggregategetClause8587 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_aggregategetClause8591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_aggrFunctionClause_in_aggregateClause8625 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_aggregateClause8628 = new BitSet(new long[]{0x0000C00000800000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0010000000250000L, 0x0000000000000000L, 0x0000000000000084L});
    public static final BitSet FOLLOW_aggrFunctionClause_in_aggregateClause8630 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_aggrFunction_in_aggrFunctionClause8664 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_aggrFunctionClause8666 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_aggrFunctionClause8668 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_aggrFunctionClause8670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_percentileFunction_in_aggrFunctionClause8694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varID_in_datasetIDGroup8709 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_datasetIDGroup8712 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000400000000L});
    public static final BitSet FOLLOW_varID_in_datasetIDGroup8714 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_caseElseClause8755 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000080L});
    public static final BitSet FOLLOW_ELSE_in_caseElseClause8757 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_exprAdd_in_caseElseClause8759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exprOr_in_caseCaseClause8808 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_caseCaseClause8810 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_exprOr_in_caseCaseClause8812 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_caseCaseClause8815 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_exprOr_in_caseCaseClause8817 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_caseCaseClause8819 = new BitSet(new long[]{0x000001E004000010L, 0x0000000000000000L, 0x4000000000000000L, 0x00008D04A2204000L, 0x0A2005C814690690L, 0x0000000000000040L, 0x000004002CC00050L});
    public static final BitSet FOLLOW_exprOr_in_caseCaseClause8821 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_getFilterClause_in_getFiltersClause8874 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_getFiltersClause8877 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402600000L, 0x0220010810400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_getFilterClause_in_getFiltersClause8879 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_FILTER_in_getFilterClause8928 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010810400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_bScalarExpr_in_getFilterClause8933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RENAME_in_datasetClause8977 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000400000000L});
    public static final BitSet FOLLOW_renameClause_in_datasetClause8979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_aggrFilterClause_in_datasetClause9008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_calcClause_in_datasetClause9015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attrCalcClause_in_datasetClause9045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keepClause_in_datasetClause9075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dropClause_in_datasetClause9105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compareClause_in_datasetClause9135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_filterClause_in_aggrFilterClause9183 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_aggrFilterClause9186 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0080000000000000L});
    public static final BitSet FOLLOW_keepClause_in_aggrFilterClause9188 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_aggrFilterClause9190 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_AGGREGATE_in_aggrFilterClause9192 = new BitSet(new long[]{0x0000C00000800000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0010000000250000L, 0x0000000000000000L, 0x0000000000000084L});
    public static final BitSet FOLLOW_aggregateClause_in_aggrFilterClause9194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keepClause_in_aggrFilterClause9250 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_dropClause_in_aggrFilterClause9260 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_aggrFilterClause9272 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_AGGREGATE_in_aggrFilterClause9274 = new BitSet(new long[]{0x0000C00000800000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0010000000250000L, 0x0000000000000000L, 0x0000000000000084L});
    public static final BitSet FOLLOW_aggregateClause_in_aggrFilterClause9276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILTER_in_filterClause9330 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010810400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_bScalarExpr_in_filterClause9335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASC_in_ascdescClause9379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DESC_in_ascdescClause9397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varID_in_renameClause9429 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_AS_in_renameClause9431 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000400000000L});
    public static final BitSet FOLLOW_varID_in_renameClause9433 = new BitSet(new long[]{0x0000000800000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000004L});
    public static final BitSet FOLLOW_ROLE_in_renameClause9436 = new BitSet(new long[]{0x0000000000400000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000001L, 0x0000000000020000L});
    public static final BitSet FOLLOW_varRole_in_renameClause9438 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_renameClause9443 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000400000000L});
    public static final BitSet FOLLOW_varID_in_renameClause9445 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_AS_in_renameClause9447 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000400000000L});
    public static final BitSet FOLLOW_varID_in_renameClause9449 = new BitSet(new long[]{0x0000000800000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000004L});
    public static final BitSet FOLLOW_ROLE_in_renameClause9452 = new BitSet(new long[]{0x0000000000400000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000001L, 0x0000000000020000L});
    public static final BitSet FOLLOW_varRole_in_renameClause9454 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_SUM_in_aggrFunction9502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AVG_in_aggrFunction9516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MIN_in_aggrFunction9530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MAX_in_aggrFunction9544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STD_in_aggrFunction9558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_aggrFunction9572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_DISTINCT_in_aggrFunction9586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MEDIAN_in_aggrFunction9600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENTILE_in_percentileFunction9624 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_percentileFunction9626 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_percentileFunction9630 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_percentileFunction9632 = new BitSet(new long[]{0x0000000004000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L, 0x0000010000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_constant_in_percentileFunction9636 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_percentileFunction9638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALC_in_calcClause9687 = new BitSet(new long[]{0x0000D00004800010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010650290L, 0x0000000000000040L, 0x0000440008C000D4L});
    public static final BitSet FOLLOW_calcClauseItem_in_calcClause9690 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_calcClause9693 = new BitSet(new long[]{0x0000D00004800010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010650290L, 0x0000000000000040L, 0x0000440008C000D4L});
    public static final BitSet FOLLOW_calcClauseItem_in_calcClause9695 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_ATTRCALC_in_attrCalcClause9737 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_attrCalcClause9739 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_AS_in_attrCalcClause9741 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_STRING_CONSTANT_in_attrCalcClause9743 = new BitSet(new long[]{0x0000000800000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000002000000000L});
    public static final BitSet FOLLOW_VIRAL_in_attrCalcClause9746 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_attrCalcClause9751 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000400000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_attrCalcClause9753 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_AS_in_attrCalcClause9755 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_STRING_CONSTANT_in_attrCalcClause9757 = new BitSet(new long[]{0x0000000800000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000002000000000L});
    public static final BitSet FOLLOW_VIRAL_in_attrCalcClause9760 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_calcExpr_in_calcClauseItem9785 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_AS_in_calcClauseItem9787 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_STRING_CONSTANT_in_calcClauseItem9789 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000004L});
    public static final BitSet FOLLOW_ROLE_in_calcClauseItem9792 = new BitSet(new long[]{0x0000000000400000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000001L, 0x0000000000020000L});
    public static final BitSet FOLLOW_varRole_in_calcClauseItem9794 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000002000000000L});
    public static final BitSet FOLLOW_VIRAL_in_calcClauseItem9797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_aggrFunction_in_calcExpr9848 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_calcExpr9850 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_calcExpr9854 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_calcExpr9856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_scalarExpr_in_calcExpr9884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DROP_in_dropClause9899 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_dropClause9901 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000400000000L});
    public static final BitSet FOLLOW_dropClauseItem_in_dropClause9903 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_dropClause9906 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000400000000L});
    public static final BitSet FOLLOW_dropClauseItem_in_dropClause9908 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_dropClause9912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varID_in_dropClauseItem9945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEEP_in_keepClause9983 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_keepClause9985 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000400000000L});
    public static final BitSet FOLLOW_keepClauseItem_in_keepClause9987 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_keepClause9990 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000400000000L});
    public static final BitSet FOLLOW_keepClauseItem_in_keepClause9992 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_keepClause9996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varID_in_keepClauseItem10029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compOpScalarClause_in_compareClause10070 = new BitSet(new long[]{0x0000000004000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L, 0x0000010000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_constant_in_compareClause10074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IN_in_inBetweenClause10121 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x4000000000000000L, 0x0000080000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040020000000L});
    public static final BitSet FOLLOW_setExpr_in_inBetweenClause10125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BETWEEN_in_inBetweenClause10168 = new BitSet(new long[]{0x0000000004000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L, 0x0000010000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_constant_in_inBetweenClause10172 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_AND_in_inBetweenClause10174 = new BitSet(new long[]{0x0000000004000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L, 0x0000010000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_constant_in_inBetweenClause10178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_inBetweenClause10224 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000002000000000L});
    public static final BitSet FOLLOW_IN_in_inBetweenClause10226 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x4000000000000000L, 0x0000080000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040020000000L});
    public static final BitSet FOLLOW_setExpr_in_inBetweenClause10230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_inBetweenClause10273 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_BETWEEN_in_inBetweenClause10275 = new BitSet(new long[]{0x0000000004000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L, 0x0000010000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_constant_in_inBetweenClause10279 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_AND_in_inBetweenClause10281 = new BitSet(new long[]{0x0000000004000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L, 0x0000010000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_constant_in_inBetweenClause10285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compareClause_in_dimClause10333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_inBetweenClause_in_dimClause10339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIMENSION_in_varRole10355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MEASURE_in_varRole10373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATTRIBUTE_in_varRole10391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sExprOr_in_bScalarExpr10418 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000100000000000L, 0x0000000000000000L, 0x0000010000000000L});
    public static final BitSet FOLLOW_OR_in_bScalarExpr10448 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010810400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_sExprOr_in_bScalarExpr10452 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000100000000000L, 0x0000000000000000L, 0x0000010000000000L});
    public static final BitSet FOLLOW_XOR_in_bScalarExpr10510 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010810400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_sExprOr_in_bScalarExpr10514 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000100000000000L, 0x0000000000000000L, 0x0000010000000000L});
    public static final BitSet FOLLOW_sExprAnd_in_sExprOr10572 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_AND_in_sExprOr10594 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010810400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_sExprAnd_in_sExprOr10598 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_NOT_in_sExprAnd10652 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_sExprPredicate_in_sExprAnd10654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sExprPredicate_in_sExprAnd10687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprPredicate10703 = new BitSet(new long[]{0x0000000002000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000002050000800L, 0x0000000900001020L});
    public static final BitSet FOLLOW_compOpScalar_in_sExprPredicate10733 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprPredicate10737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IN_in_sExprPredicate10795 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x4000000000000000L, 0x0000080000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040020000000L});
    public static final BitSet FOLLOW_setExpr_in_sExprPredicate10799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BETWEEN_in_sExprPredicate10857 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprPredicate10861 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_AND_in_sExprPredicate10863 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprPredicate10867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_sExprPredicate10928 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000002000000000L});
    public static final BitSet FOLLOW_IN_in_sExprPredicate10930 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x4000000000000000L, 0x0000080000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040020000000L});
    public static final BitSet FOLLOW_setExpr_in_sExprPredicate10934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_sExprPredicate10992 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_BETWEEN_in_sExprPredicate10994 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprPredicate10998 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_AND_in_sExprPredicate11000 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprPredicate11004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sExprAdd_in_scalarExpr11065 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0020000000400000L});
    public static final BitSet FOLLOW_PLUS_in_scalarExpr11095 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_sExprAdd_in_scalarExpr11099 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0020000000400000L});
    public static final BitSet FOLLOW_MINUS_in_scalarExpr11157 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_sExprAdd_in_scalarExpr11161 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0020000000400000L});
    public static final BitSet FOLLOW_sExprFactor_in_sExprAdd11219 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000020L, 0x0008000040000000L});
    public static final BitSet FOLLOW_DIVIDE_in_sExprAdd11249 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_sExprFactor_in_sExprAdd11253 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000020L, 0x0008000040000000L});
    public static final BitSet FOLLOW_MULTIPLY_in_sExprAdd11311 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_sExprFactor_in_sExprAdd11315 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000020L, 0x0008000040000000L});
    public static final BitSet FOLLOW_PERCENT_in_sExprAdd11373 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_sExprFactor_in_sExprAdd11377 = new BitSet(new long[]{0x0000000000000002L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000020L, 0x0008000040000000L});
    public static final BitSet FOLLOW_sExprAtom_in_sExprFactor11441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_sExprFactor11475 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0200010010000290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_sExprAtom_in_sExprFactor11477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_sExprFactor11517 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0200010010000290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_sExprAtom_in_sExprFactor11519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_varID_in_sExprAtom11571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constant_in_sExprAtom11577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_426_in_sExprAtom11583 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010810400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_bScalarExpr_in_sExprAtom11586 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_sExprAtom11588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEN_in_sExprAtom11596 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_sExprAtom11598 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprAtom11600 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_sExprAtom11602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONCAT_in_sExprAtom11639 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_sExprAtom11641 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprAtom11645 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_sExprAtom11648 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprAtom11652 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_sExprAtom11656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRIM_in_sExprAtom11694 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_sExprAtom11696 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprAtom11698 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_sExprAtom11700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UCASE_in_sExprAtom11730 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_sExprAtom11732 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprAtom11734 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_sExprAtom11736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCASE_in_sExprAtom11766 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_sExprAtom11768 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprAtom11770 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_sExprAtom11772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUBSTR_in_sExprAtom11802 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_sExprAtom11804 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprAtom11808 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_sExprAtom11810 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprAtom11814 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_sExprAtom11816 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprAtom11820 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_sExprAtom11822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ROUND_in_sExprAtom11859 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_sExprAtom11861 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprAtom11863 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_sExprAtom11865 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_INTEGER_CONSTANT_in_sExprAtom11867 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_sExprAtom11869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUNC_in_sExprAtom11901 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_sExprAtom11903 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprAtom11905 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_sExprAtom11907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LN_in_sExprAtom11937 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_sExprAtom11939 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprAtom11941 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_sExprAtom11943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXP_in_sExprAtom11973 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_sExprAtom11975 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprAtom11977 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_sExprAtom11979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MOD_in_sExprAtom12009 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_sExprAtom12011 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprAtom12013 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_sExprAtom12015 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_INTEGER_CONSTANT_in_sExprAtom12017 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_sExprAtom12019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ABS_in_sExprAtom12051 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_sExprAtom12053 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprAtom12055 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_sExprAtom12057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_POWER_in_sExprAtom12087 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_sExprAtom12089 = new BitSet(new long[]{0x0000100004000010L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040402200000L, 0x0220010010400290L, 0x0000000000000040L, 0x0000440008C00050L});
    public static final BitSet FOLLOW_scalarExpr_in_sExprAtom12091 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_sExprAtom12093 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L, 0x0020000000400000L});
    public static final BitSet FOLLOW_powerExponent_in_sExprAtom12095 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_sExprAtom12097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_430_in_sExprAtom12131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_componentID12168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpScalarClause12203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpScalarClause12231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpScalarClause12259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LE_in_compOpScalarClause12287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GE_in_compOpScalarClause12315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NE_in_compOpScalarClause12343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_CONSTANT_in_logBase12379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exponent_in_powerExponent12419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_powerExponent12453 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L});
    public static final BitSet FOLLOW_exponent_in_powerExponent12455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_powerExponent12495 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L});
    public static final BitSet FOLLOW_exponent_in_powerExponent12497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_CONSTANT_in_exponent12549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_CONSTANT_in_exponent12573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_426_in_setExpr12606 = new BitSet(new long[]{0x0000000004000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L, 0x0000010000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_constant_in_setExpr12609 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_setExpr12612 = new BitSet(new long[]{0x0000000004000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040002000000L, 0x0000010000000000L, 0x0000000000000000L, 0x0000000000000010L});
    public static final BitSet FOLLOW_constant_in_setExpr12615 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_setExpr12619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UNION_in_setExpr12626 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_setExpr12628 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x4000000000000000L, 0x0000080000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040020000000L});
    public static final BitSet FOLLOW_setExpr_in_setExpr12630 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_setExpr12633 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x4000000000000000L, 0x0000080000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040020000000L});
    public static final BitSet FOLLOW_setExpr_in_setExpr12635 = new BitSet(new long[]{0x0000000800000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_setExpr12639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIFF_in_setExpr12667 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_setExpr12669 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x4000000000000000L, 0x0000080000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040020000000L});
    public static final BitSet FOLLOW_setExpr_in_setExpr12673 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_setExpr12675 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x4000000000000000L, 0x0000080000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040020000000L});
    public static final BitSet FOLLOW_setExpr_in_setExpr12679 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_setExpr12681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTERSECT_in_setExpr12715 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040000000000L});
    public static final BitSet FOLLOW_426_in_setExpr12717 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x4000000000000000L, 0x0000080000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040020000000L});
    public static final BitSet FOLLOW_setExpr_in_setExpr12721 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_CARTESIAN_PER_in_setExpr12723 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x4000000000000000L, 0x0000080000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000040020000000L});
    public static final BitSet FOLLOW_setExpr_in_setExpr12727 = new BitSet(new long[]{0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000000000000000L, 0x0000080000000000L});
    public static final BitSet FOLLOW_427_in_setExpr12729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_varID12771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOp12805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOp12833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOp12861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LE_in_compOp12889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GE_in_compOp12917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NE_in_compOp12945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_compOpScalar12982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_compOpScalar13010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_compOpScalar13038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LE_in_compOpScalar13066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GE_in_compOpScalar13094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NE_in_compOpScalar13122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_CONSTANT_in_constant13158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_CONSTANT_in_constant13182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_CONSTANT_in_constant13206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_CONSTANT_in_constant13230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_CONSTANT_in_constant13254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_CONSTANT_in_lhperc13691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_428_in_lhperc13698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_CONSTANT_in_lhperc13730 = new BitSet(new long[]{0x0000000000000002L});
    static final String DFA50_eotS =
            "\24\uffff";
    static final String DFA50_eofS =
            "\24\uffff";
    static final String DFA50_minS =
            "\1\4\2\uffff\2\u01aa\3\uffff\2\u00e2\2\43\1\u00e2\1\43\1\u00e2\2\43\1" +
                    "\uffff\1\43\1\uffff";
    static final String DFA50_maxS =
            "\1\u01b1\2\uffff\2\u01aa\3\uffff\2\u00e2\2\u01ab\1\u00e2\1\u01b1\1\u00e2" +
                    "\1\u01b1\1\u01ab\1\uffff\1\u01ab\1\uffff";
    static final String DFA50_acceptS =
            "\1\uffff\1\1\1\2\2\uffff\1\3\1\4\1\7\11\uffff\1\5\1\uffff\1\6";
    static final String DFA50_specialS =
            "\24\uffff}>";
    static final String[] DFA50_transitionS = {
            "\1\2\20\uffff\1\6\4\uffff\1\2\6\uffff\1\5\12\uffff\1\2\u0099\uffff\1" +
                    "\4\4\uffff\1\7\11\uffff\2\2\2\uffff\1\2\2\uffff\1\7\1\uffff\1\7\3\uffff" +
                    "\1\2\7\uffff\1\2\14\uffff\1\3\14\uffff\1\2\1\7\1\uffff\1\2\1\uffff\1" +
                    "\2\2\uffff\1\7\11\uffff\1\2\5\uffff\1\2\3\uffff\1\7\2\uffff\1\2\4\uffff" +
                    "\1\2\14\uffff\1\2\3\uffff\1\2\6\uffff\1\1\5\uffff\1\2\75\uffff\1\2\1" +
                    "\uffff\1\2\17\uffff\2\2\3\uffff\1\2\16\uffff\1\2\3\uffff\1\2\2\uffff" +
                    "\1\2",
            "",
            "",
            "\1\10",
            "\1\11",
            "",
            "",
            "",
            "\1\12",
            "\1\13",
            "\1\14\u0187\uffff\1\15",
            "\1\16\u0187\uffff\1\17",
            "\1\20",
            "\1\2\u018d\uffff\1\21",
            "\1\22",
            "\1\2\u018d\uffff\1\23",
            "\1\14\u0187\uffff\1\15",
            "",
            "\1\16\u0187\uffff\1\17",
            ""
    };
    static final short[] DFA50_eot = DFA.unpackEncodedString(DFA50_eotS);
    static final short[] DFA50_eof = DFA.unpackEncodedString(DFA50_eofS);
    static final char[] DFA50_min = DFA.unpackEncodedStringToUnsignedChars(DFA50_minS);
    static final char[] DFA50_max = DFA.unpackEncodedStringToUnsignedChars(DFA50_maxS);
    static final short[] DFA50_accept = DFA.unpackEncodedString(DFA50_acceptS);
    static final short[] DFA50_special = DFA.unpackEncodedString(DFA50_specialS);
    static final short[][] DFA50_transition;

    static {
        int numStates = DFA50_transitionS.length;
        DFA50_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA50_transition[i] = DFA.unpackEncodedString(DFA50_transitionS[i]);
        }
    }

    protected TreeAdaptor adaptor = new CommonTreeAdaptor();
    protected DFA50 dfa50 = new DFA50(this);
    List<RecognitionException> exceptions = new ArrayList<RecognitionException>();

    public ValidationMlParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }

    public ValidationMlParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public static void main(String[] args) throws Exception {
        ValidationMlLexer lex = new ValidationMlLexer(new ANTLRFileStream(args[0]));
        CommonTokenStream tokens = new CommonTokenStream(lex);

        ValidationMlParser parser = new ValidationMlParser(tokens);

        try {
            parser.start();
        } catch (RecognitionException e) {
            e.printStackTrace();
        }
    }

    // delegates
    public Parser[] getDelegates() {
        return new Parser[]{};
    }

    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }

    @Override
    public String[] getTokenNames() {
        return ValidationMlParser.tokenNames;
    }

    @Override
    public String getGrammarFileName() {
        return "/Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g";
    }

    public List<RecognitionException> getExceptions() {
        return exceptions;
    }

    public void reportError(RecognitionException e) {
        super.reportError(e);
        exceptions.add(e);
    }

    // $ANTLR start "start"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:475:1: start : ( statement )* EOF -> ^( RULE_TAG ( statement )* ) ;
    public final ValidationMlParser.start_return start() throws Exception {
        ValidationMlParser.start_return retval = new ValidationMlParser.start_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF2 = null;
        ParserRuleReturnScope statement1 = null;

        Object EOF2_tree = null;
        RewriteRuleTokenStream stream_EOF = new RewriteRuleTokenStream(adaptor, "token EOF");
        RewriteRuleSubtreeStream stream_statement = new RewriteRuleSubtreeStream(adaptor, "rule statement");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:476:3: ( ( statement )* EOF -> ^( RULE_TAG ( statement )* ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:477:3: ( statement )* EOF
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:477:3: ( statement )*
                loop1:
                while (true) {
                    int alt1 = 2;
                    int LA1_0 = input.LA(1);
                    if ((LA1_0 == BOOLEAN_CONSTANT || LA1_0 == FLOAT_CONSTANT || LA1_0 == IDENTIFIER || LA1_0 == INTEGER_CONSTANT || LA1_0 == NULL_CONSTANT || LA1_0 == STRING_CONSTANT || LA1_0 == 426)) {
                        alt1 = 1;
                    }

                    switch (alt1) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:477:3: statement
                        {
                            pushFollow(FOLLOW_statement_in_start4526);
                            statement1 = statement();
                            state._fsp--;

                            stream_statement.add(statement1.getTree());
                        }
                        break;

                        default:
                            break loop1;
                    }
                }

                EOF2 = (Token) match(input, EOF, FOLLOW_EOF_in_start4529);
                stream_EOF.add(EOF2);

                // AST REWRITE
                // elements: statement
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                root_0 = (Object) adaptor.nil();
                // 478:5: -> ^( RULE_TAG ( statement )* )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:479:7: ^( RULE_TAG ( statement )* )
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(RULE_TAG, "RULE_TAG"), root_1);
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:479:18: ( statement )*
                        while (stream_statement.hasNext()) {
                            adaptor.addChild(root_1, stream_statement.nextTree());
                        }
                        stream_statement.reset();

                        adaptor.addChild(root_0, root_1);
                    }

                }


                retval.tree = root_0;

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "statement"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:484:1: statement : ( variableRef ASSIGN expr ) -> ^( ASSIGN_TAG variableRef expr ) ;
    public final ValidationMlParser.statement_return statement() throws Exception {
        ValidationMlParser.statement_return retval = new ValidationMlParser.statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ASSIGN4 = null;
        ParserRuleReturnScope variableRef3 = null;
        ParserRuleReturnScope expr5 = null;

        Object ASSIGN4_tree = null;
        RewriteRuleTokenStream stream_ASSIGN = new RewriteRuleTokenStream(adaptor, "token ASSIGN");
        RewriteRuleSubtreeStream stream_expr = new RewriteRuleSubtreeStream(adaptor, "rule expr");
        RewriteRuleSubtreeStream stream_variableRef = new RewriteRuleSubtreeStream(adaptor, "rule variableRef");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:485:3: ( ( variableRef ASSIGN expr ) -> ^( ASSIGN_TAG variableRef expr ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:486:3: ( variableRef ASSIGN expr )
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:486:3: ( variableRef ASSIGN expr )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:486:4: variableRef ASSIGN expr
                {
                    pushFollow(FOLLOW_variableRef_in_statement4567);
                    variableRef3 = variableRef();
                    state._fsp--;

                    stream_variableRef.add(variableRef3.getTree());
                    ASSIGN4 = (Token) match(input, ASSIGN, FOLLOW_ASSIGN_in_statement4569);
                    stream_ASSIGN.add(ASSIGN4);

                    pushFollow(FOLLOW_expr_in_statement4571);
                    expr5 = expr();
                    state._fsp--;

                    stream_expr.add(expr5.getTree());
                }

                // AST REWRITE
                // elements: variableRef, expr
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                root_0 = (Object) adaptor.nil();
                // 487:5: -> ^( ASSIGN_TAG variableRef expr )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:488:7: ^( ASSIGN_TAG variableRef expr )
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(ASSIGN_TAG, "ASSIGN_TAG"), root_1);
                        adaptor.addChild(root_1, stream_variableRef.nextTree());
                        adaptor.addChild(root_1, stream_expr.nextTree());
                        adaptor.addChild(root_0, root_1);
                    }

                }


                retval.tree = root_0;

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "expr"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:493:1: expr : ( exprOr | IF exprOr THEN exprOr ( ELSEIF exprOr THEN exprOr )* ( ELSE exprOr )* );
    public final ValidationMlParser.expr_return expr() throws Exception {
        ValidationMlParser.expr_return retval = new ValidationMlParser.expr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IF7 = null;
        Token THEN9 = null;
        Token ELSEIF11 = null;
        Token THEN13 = null;
        Token ELSE15 = null;
        ParserRuleReturnScope exprOr6 = null;
        ParserRuleReturnScope exprOr8 = null;
        ParserRuleReturnScope exprOr10 = null;
        ParserRuleReturnScope exprOr12 = null;
        ParserRuleReturnScope exprOr14 = null;
        ParserRuleReturnScope exprOr16 = null;

        Object IF7_tree = null;
        Object THEN9_tree = null;
        Object ELSEIF11_tree = null;
        Object THEN13_tree = null;
        Object ELSE15_tree = null;

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:494:3: ( exprOr | IF exprOr THEN exprOr ( ELSEIF exprOr THEN exprOr )* ( ELSE exprOr )* )
            int alt4 = 2;
            int LA4_0 = input.LA(1);
            if ((LA4_0 == ABS || LA4_0 == BOOLEAN_CONSTANT || (LA4_0 >= CHARLENGTH && LA4_0 <= CODELIST_MATCH) || LA4_0 == DIFF || LA4_0 == EVAL || LA4_0 == EXP || LA4_0 == FLOAT_CONSTANT || LA4_0 == GET || LA4_0 == HIERARCHY || LA4_0 == IDENTIFIER || LA4_0 == INDEXOF || (LA4_0 >= INTEGER_CONSTANT && LA4_0 <= INTERSECT) || LA4_0 == ISNULL || LA4_0 == LCASE || LA4_0 == LEN || (LA4_0 >= LN && LA4_0 <= LOG) || LA4_0 == MAX || LA4_0 == MERGE || (LA4_0 >= MIN && LA4_0 <= MINUS) || LA4_0 == MISSING || LA4_0 == MOD || LA4_0 == NOT || (LA4_0 >= NOT_IN && LA4_0 <= NULL_CONSTANT) || LA4_0 == NVL || LA4_0 == PLUS || LA4_0 == POWER || LA4_0 == PUT || LA4_0 == ROUND || LA4_0 == STRING_CONSTANT || LA4_0 == SUBSTR || (LA4_0 >= TRIM && LA4_0 <= TRUNC) || (LA4_0 >= TYPE && LA4_0 <= UCASE) || LA4_0 == UNION || LA4_0 == 426)) {
                alt4 = 1;
            } else if ((LA4_0 == IF)) {
                alt4 = 2;
            } else {
                NoViableAltException nvae =
                        new NoViableAltException("", 4, 0, input);
                throw nvae;
            }

            switch (alt4) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:495:3: exprOr
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_exprOr_in_expr4610);
                    exprOr6 = exprOr();
                    state._fsp--;

                    adaptor.addChild(root_0, exprOr6.getTree());

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:496:5: IF exprOr THEN exprOr ( ELSEIF exprOr THEN exprOr )* ( ELSE exprOr )*
                {
                    root_0 = (Object) adaptor.nil();


                    IF7 = (Token) match(input, IF, FOLLOW_IF_in_expr4616);
                    IF7_tree = (Object) adaptor.create(IF7);
                    adaptor.addChild(root_0, IF7_tree);

                    pushFollow(FOLLOW_exprOr_in_expr4618);
                    exprOr8 = exprOr();
                    state._fsp--;

                    adaptor.addChild(root_0, exprOr8.getTree());

                    THEN9 = (Token) match(input, THEN, FOLLOW_THEN_in_expr4620);
                    THEN9_tree = (Object) adaptor.create(THEN9);
                    adaptor.addChild(root_0, THEN9_tree);

                    pushFollow(FOLLOW_exprOr_in_expr4622);
                    exprOr10 = exprOr();
                    state._fsp--;

                    adaptor.addChild(root_0, exprOr10.getTree());

                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:496:27: ( ELSEIF exprOr THEN exprOr )*
                    loop2:
                    while (true) {
                        int alt2 = 2;
                        int LA2_0 = input.LA(1);
                        if ((LA2_0 == ELSEIF)) {
                            alt2 = 1;
                        }

                        switch (alt2) {
                            case 1:
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:496:28: ELSEIF exprOr THEN exprOr
                            {
                                ELSEIF11 = (Token) match(input, ELSEIF, FOLLOW_ELSEIF_in_expr4625);
                                ELSEIF11_tree = (Object) adaptor.create(ELSEIF11);
                                adaptor.addChild(root_0, ELSEIF11_tree);

                                pushFollow(FOLLOW_exprOr_in_expr4627);
                                exprOr12 = exprOr();
                                state._fsp--;

                                adaptor.addChild(root_0, exprOr12.getTree());

                                THEN13 = (Token) match(input, THEN, FOLLOW_THEN_in_expr4629);
                                THEN13_tree = (Object) adaptor.create(THEN13);
                                adaptor.addChild(root_0, THEN13_tree);

                                pushFollow(FOLLOW_exprOr_in_expr4631);
                                exprOr14 = exprOr();
                                state._fsp--;

                                adaptor.addChild(root_0, exprOr14.getTree());

                            }
                            break;

                            default:
                                break loop2;
                        }
                    }

                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:496:56: ( ELSE exprOr )*
                    loop3:
                    while (true) {
                        int alt3 = 2;
                        int LA3_0 = input.LA(1);
                        if ((LA3_0 == ELSE)) {
                            alt3 = 1;
                        }

                        switch (alt3) {
                            case 1:
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:496:57: ELSE exprOr
                            {
                                ELSE15 = (Token) match(input, ELSE, FOLLOW_ELSE_in_expr4636);
                                ELSE15_tree = (Object) adaptor.create(ELSE15);
                                adaptor.addChild(root_0, ELSE15_tree);

                                pushFollow(FOLLOW_exprOr_in_expr4638);
                                exprOr16 = exprOr();
                                state._fsp--;

                                adaptor.addChild(root_0, exprOr16.getTree());

                            }
                            break;

                            default:
                                break loop3;
                        }
                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "exprOr"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:501:1: exprOr : ( exprAnd -> exprAnd ) ( ( ( OR b1= exprAnd ) -> ^( DATASET_OR_TAG $exprOr $b1) ) | ( ( XOR b1= exprAnd ) -> ^( DATASET_XOR_TAG $exprOr $b1) ) )* ;
    public final ValidationMlParser.exprOr_return exprOr() throws Exception {
        ValidationMlParser.exprOr_return retval = new ValidationMlParser.exprOr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token OR18 = null;
        Token XOR19 = null;
        ParserRuleReturnScope b1 = null;
        ParserRuleReturnScope exprAnd17 = null;

        Object OR18_tree = null;
        Object XOR19_tree = null;
        RewriteRuleTokenStream stream_OR = new RewriteRuleTokenStream(adaptor, "token OR");
        RewriteRuleTokenStream stream_XOR = new RewriteRuleTokenStream(adaptor, "token XOR");
        RewriteRuleSubtreeStream stream_exprAnd = new RewriteRuleSubtreeStream(adaptor, "rule exprAnd");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:502:3: ( ( exprAnd -> exprAnd ) ( ( ( OR b1= exprAnd ) -> ^( DATASET_OR_TAG $exprOr $b1) ) | ( ( XOR b1= exprAnd ) -> ^( DATASET_XOR_TAG $exprOr $b1) ) )* )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:503:3: ( exprAnd -> exprAnd ) ( ( ( OR b1= exprAnd ) -> ^( DATASET_OR_TAG $exprOr $b1) ) | ( ( XOR b1= exprAnd ) -> ^( DATASET_XOR_TAG $exprOr $b1) ) )*
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:503:3: ( exprAnd -> exprAnd )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:503:4: exprAnd
                {
                    pushFollow(FOLLOW_exprAnd_in_exprOr4659);
                    exprAnd17 = exprAnd();
                    state._fsp--;

                    stream_exprAnd.add(exprAnd17.getTree());
                    // AST REWRITE
                    // elements: exprAnd
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 504:7: -> exprAnd
                    {
                        adaptor.addChild(root_0, stream_exprAnd.nextTree());
                    }


                    retval.tree = root_0;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:505:3: ( ( ( OR b1= exprAnd ) -> ^( DATASET_OR_TAG $exprOr $b1) ) | ( ( XOR b1= exprAnd ) -> ^( DATASET_XOR_TAG $exprOr $b1) ) )*
                loop5:
                while (true) {
                    int alt5 = 3;
                    int LA5_0 = input.LA(1);
                    if ((LA5_0 == OR)) {
                        alt5 = 1;
                    } else if ((LA5_0 == XOR)) {
                        alt5 = 2;
                    }

                    switch (alt5) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:506:5: ( ( OR b1= exprAnd ) -> ^( DATASET_OR_TAG $exprOr $b1) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:506:5: ( ( OR b1= exprAnd ) -> ^( DATASET_OR_TAG $exprOr $b1) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:507:7: ( OR b1= exprAnd )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:507:7: ( OR b1= exprAnd )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:507:8: OR b1= exprAnd
                                {
                                    OR18 = (Token) match(input, OR, FOLLOW_OR_in_exprOr4689);
                                    stream_OR.add(OR18);

                                    pushFollow(FOLLOW_exprAnd_in_exprOr4693);
                                    b1 = exprAnd();
                                    state._fsp--;

                                    stream_exprAnd.add(b1.getTree());
                                }

                                // AST REWRITE
                                // elements: b1, exprOr
                                // token labels:
                                // rule labels: retval, b1
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                                RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 508:9: -> ^( DATASET_OR_TAG $exprOr $b1)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:509:11: ^( DATASET_OR_TAG $exprOr $b1)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_OR_TAG, "DATASET_OR_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_b1.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;
                        case 2:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:512:5: ( ( XOR b1= exprAnd ) -> ^( DATASET_XOR_TAG $exprOr $b1) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:512:5: ( ( XOR b1= exprAnd ) -> ^( DATASET_XOR_TAG $exprOr $b1) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:513:7: ( XOR b1= exprAnd )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:513:7: ( XOR b1= exprAnd )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:513:8: XOR b1= exprAnd
                                {
                                    XOR19 = (Token) match(input, XOR, FOLLOW_XOR_in_exprOr4751);
                                    stream_XOR.add(XOR19);

                                    pushFollow(FOLLOW_exprAnd_in_exprOr4755);
                                    b1 = exprAnd();
                                    state._fsp--;

                                    stream_exprAnd.add(b1.getTree());
                                }

                                // AST REWRITE
                                // elements: b1, exprOr
                                // token labels:
                                // rule labels: retval, b1
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                                RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 514:9: -> ^( DATASET_XOR_TAG $exprOr $b1)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:515:11: ^( DATASET_XOR_TAG $exprOr $b1)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_XOR_TAG, "DATASET_XOR_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_b1.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;

                        default:
                            break loop5;
                    }
                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "exprAnd"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:522:1: exprAnd : ( exprEq -> exprEq ) ( ( AND b1= exprEq ) -> ^( DATASET_AND_TAG $exprAnd $b1) )* ;
    public final ValidationMlParser.exprAnd_return exprAnd() throws Exception {
        ValidationMlParser.exprAnd_return retval = new ValidationMlParser.exprAnd_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AND21 = null;
        ParserRuleReturnScope b1 = null;
        ParserRuleReturnScope exprEq20 = null;

        Object AND21_tree = null;
        RewriteRuleTokenStream stream_AND = new RewriteRuleTokenStream(adaptor, "token AND");
        RewriteRuleSubtreeStream stream_exprEq = new RewriteRuleSubtreeStream(adaptor, "rule exprEq");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:523:3: ( ( exprEq -> exprEq ) ( ( AND b1= exprEq ) -> ^( DATASET_AND_TAG $exprAnd $b1) )* )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:524:3: ( exprEq -> exprEq ) ( ( AND b1= exprEq ) -> ^( DATASET_AND_TAG $exprAnd $b1) )*
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:524:3: ( exprEq -> exprEq )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:524:4: exprEq
                {
                    pushFollow(FOLLOW_exprEq_in_exprAnd4816);
                    exprEq20 = exprEq();
                    state._fsp--;

                    stream_exprEq.add(exprEq20.getTree());
                    // AST REWRITE
                    // elements: exprEq
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 525:7: -> exprEq
                    {
                        adaptor.addChild(root_0, stream_exprEq.nextTree());
                    }


                    retval.tree = root_0;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:526:3: ( ( AND b1= exprEq ) -> ^( DATASET_AND_TAG $exprAnd $b1) )*
                loop6:
                while (true) {
                    int alt6 = 2;
                    int LA6_0 = input.LA(1);
                    if ((LA6_0 == AND)) {
                        alt6 = 1;
                    }

                    switch (alt6) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:527:5: ( AND b1= exprEq )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:527:5: ( AND b1= exprEq )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:527:6: AND b1= exprEq
                            {
                                AND21 = (Token) match(input, AND, FOLLOW_AND_in_exprAnd4838);
                                stream_AND.add(AND21);

                                pushFollow(FOLLOW_exprEq_in_exprAnd4842);
                                b1 = exprEq();
                                state._fsp--;

                                stream_exprEq.add(b1.getTree());
                            }

                            // AST REWRITE
                            // elements: b1, exprAnd
                            // token labels:
                            // rule labels: retval, b1
                            // token list labels:
                            // rule list labels:
                            // wildcard labels:
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                            RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                            root_0 = (Object) adaptor.nil();
                            // 528:7: -> ^( DATASET_AND_TAG $exprAnd $b1)
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:529:9: ^( DATASET_AND_TAG $exprAnd $b1)
                                {
                                    Object root_1 = (Object) adaptor.nil();
                                    root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_AND_TAG, "DATASET_AND_TAG"), root_1);
                                    adaptor.addChild(root_1, stream_retval.nextTree());
                                    adaptor.addChild(root_1, stream_b1.nextTree());
                                    adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;

                        }
                        break;

                        default:
                            break loop6;
                    }
                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "exprEq"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:535:1: exprEq : ( exprExists -> exprExists ) ( ( ( EQ | NE | LE | GE ) b2= exprExists ) -> ^( DATASET_EQ_TAG $exprEq $b2) )* ;
    public final ValidationMlParser.exprEq_return exprEq() throws Exception {
        ValidationMlParser.exprEq_return retval = new ValidationMlParser.exprEq_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EQ23 = null;
        Token NE24 = null;
        Token LE25 = null;
        Token GE26 = null;
        ParserRuleReturnScope b2 = null;
        ParserRuleReturnScope exprExists22 = null;

        Object EQ23_tree = null;
        Object NE24_tree = null;
        Object LE25_tree = null;
        Object GE26_tree = null;
        RewriteRuleTokenStream stream_NE = new RewriteRuleTokenStream(adaptor, "token NE");
        RewriteRuleTokenStream stream_LE = new RewriteRuleTokenStream(adaptor, "token LE");
        RewriteRuleTokenStream stream_EQ = new RewriteRuleTokenStream(adaptor, "token EQ");
        RewriteRuleTokenStream stream_GE = new RewriteRuleTokenStream(adaptor, "token GE");
        RewriteRuleSubtreeStream stream_exprExists = new RewriteRuleSubtreeStream(adaptor, "rule exprExists");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:536:3: ( ( exprExists -> exprExists ) ( ( ( EQ | NE | LE | GE ) b2= exprExists ) -> ^( DATASET_EQ_TAG $exprEq $b2) )* )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:537:3: ( exprExists -> exprExists ) ( ( ( EQ | NE | LE | GE ) b2= exprExists ) -> ^( DATASET_EQ_TAG $exprEq $b2) )*
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:537:3: ( exprExists -> exprExists )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:537:4: exprExists
                {
                    pushFollow(FOLLOW_exprExists_in_exprEq4893);
                    exprExists22 = exprExists();
                    state._fsp--;

                    stream_exprExists.add(exprExists22.getTree());
                    // AST REWRITE
                    // elements: exprExists
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 538:7: -> exprExists
                    {
                        adaptor.addChild(root_0, stream_exprExists.nextTree());
                    }


                    retval.tree = root_0;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:539:3: ( ( ( EQ | NE | LE | GE ) b2= exprExists ) -> ^( DATASET_EQ_TAG $exprEq $b2) )*
                loop8:
                while (true) {
                    int alt8 = 2;
                    int LA8_0 = input.LA(1);
                    if ((LA8_0 == EQ || LA8_0 == GE || LA8_0 == LE || LA8_0 == NE)) {
                        alt8 = 1;
                    }

                    switch (alt8) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:540:5: ( ( EQ | NE | LE | GE ) b2= exprExists )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:540:5: ( ( EQ | NE | LE | GE ) b2= exprExists )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:541:7: ( EQ | NE | LE | GE ) b2= exprExists
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:541:7: ( EQ | NE | LE | GE )
                                int alt7 = 4;
                                switch (input.LA(1)) {
                                    case EQ: {
                                        alt7 = 1;
                                    }
                                    break;
                                    case NE: {
                                        alt7 = 2;
                                    }
                                    break;
                                    case LE: {
                                        alt7 = 3;
                                    }
                                    break;
                                    case GE: {
                                        alt7 = 4;
                                    }
                                    break;
                                    default:
                                        NoViableAltException nvae =
                                                new NoViableAltException("", 7, 0, input);
                                        throw nvae;
                                }
                                switch (alt7) {
                                    case 1:
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:542:9: EQ
                                    {
                                        EQ23 = (Token) match(input, EQ, FOLLOW_EQ_in_exprEq4932);
                                        stream_EQ.add(EQ23);

                                    }
                                    break;
                                    case 2:
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:543:11: NE
                                    {
                                        NE24 = (Token) match(input, NE, FOLLOW_NE_in_exprEq4944);
                                        stream_NE.add(NE24);

                                    }
                                    break;
                                    case 3:
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:544:11: LE
                                    {
                                        LE25 = (Token) match(input, LE, FOLLOW_LE_in_exprEq4956);
                                        stream_LE.add(LE25);

                                    }
                                    break;
                                    case 4:
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:545:11: GE
                                    {
                                        GE26 = (Token) match(input, GE, FOLLOW_GE_in_exprEq4968);
                                        stream_GE.add(GE26);

                                    }
                                    break;

                                }

                                pushFollow(FOLLOW_exprExists_in_exprEq4986);
                                b2 = exprExists();
                                state._fsp--;

                                stream_exprExists.add(b2.getTree());
                            }

                            // AST REWRITE
                            // elements: b2, exprEq
                            // token labels:
                            // rule labels: b2, retval
                            // token list labels:
                            // rule list labels:
                            // wildcard labels:
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                            RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                            root_0 = (Object) adaptor.nil();
                            // 549:7: -> ^( DATASET_EQ_TAG $exprEq $b2)
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:550:9: ^( DATASET_EQ_TAG $exprEq $b2)
                                {
                                    Object root_1 = (Object) adaptor.nil();
                                    root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_EQ_TAG, "DATASET_EQ_TAG"), root_1);
                                    adaptor.addChild(root_1, stream_retval.nextTree());
                                    adaptor.addChild(root_1, stream_b2.nextTree());
                                    adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;

                        }
                        break;

                        default:
                            break loop8;
                    }
                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "exprExists"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:556:1: exprExists : ( exprComp -> exprComp ) ( ( ( EXISTS_IN e= exprComp ) -> ^( DATASET_EXISTS_IN_TAG $exprExists $e) ) | ( ( EXISTS_IN_ALL e= exprComp ) -> ^( DATASET_EXISTS_IN_ALL_TAG $exprExists $e) ) | ( ( NOT_EXISTS_IN e= exprComp ) -> ^( DATASET_NOT_EXISTS_IN_TAG $exprExists $e) ) | ( ( NOT_EXISTS_IN_ALL e= exprComp ) -> ^( DATASET_NOT_EXISTS_IN_ALL_TAG $exprExists $e) ) )* ;
    public final ValidationMlParser.exprExists_return exprExists() throws Exception {
        ValidationMlParser.exprExists_return retval = new ValidationMlParser.exprExists_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EXISTS_IN28 = null;
        Token EXISTS_IN_ALL29 = null;
        Token NOT_EXISTS_IN30 = null;
        Token NOT_EXISTS_IN_ALL31 = null;
        ParserRuleReturnScope e = null;
        ParserRuleReturnScope exprComp27 = null;

        Object EXISTS_IN28_tree = null;
        Object EXISTS_IN_ALL29_tree = null;
        Object NOT_EXISTS_IN30_tree = null;
        Object NOT_EXISTS_IN_ALL31_tree = null;
        RewriteRuleTokenStream stream_EXISTS_IN = new RewriteRuleTokenStream(adaptor, "token EXISTS_IN");
        RewriteRuleTokenStream stream_NOT_EXISTS_IN_ALL = new RewriteRuleTokenStream(adaptor, "token NOT_EXISTS_IN_ALL");
        RewriteRuleTokenStream stream_EXISTS_IN_ALL = new RewriteRuleTokenStream(adaptor, "token EXISTS_IN_ALL");
        RewriteRuleTokenStream stream_NOT_EXISTS_IN = new RewriteRuleTokenStream(adaptor, "token NOT_EXISTS_IN");
        RewriteRuleSubtreeStream stream_exprComp = new RewriteRuleSubtreeStream(adaptor, "rule exprComp");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:557:3: ( ( exprComp -> exprComp ) ( ( ( EXISTS_IN e= exprComp ) -> ^( DATASET_EXISTS_IN_TAG $exprExists $e) ) | ( ( EXISTS_IN_ALL e= exprComp ) -> ^( DATASET_EXISTS_IN_ALL_TAG $exprExists $e) ) | ( ( NOT_EXISTS_IN e= exprComp ) -> ^( DATASET_NOT_EXISTS_IN_TAG $exprExists $e) ) | ( ( NOT_EXISTS_IN_ALL e= exprComp ) -> ^( DATASET_NOT_EXISTS_IN_ALL_TAG $exprExists $e) ) )* )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:558:3: ( exprComp -> exprComp ) ( ( ( EXISTS_IN e= exprComp ) -> ^( DATASET_EXISTS_IN_TAG $exprExists $e) ) | ( ( EXISTS_IN_ALL e= exprComp ) -> ^( DATASET_EXISTS_IN_ALL_TAG $exprExists $e) ) | ( ( NOT_EXISTS_IN e= exprComp ) -> ^( DATASET_NOT_EXISTS_IN_TAG $exprExists $e) ) | ( ( NOT_EXISTS_IN_ALL e= exprComp ) -> ^( DATASET_NOT_EXISTS_IN_ALL_TAG $exprExists $e) ) )*
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:558:3: ( exprComp -> exprComp )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:558:4: exprComp
                {
                    pushFollow(FOLLOW_exprComp_in_exprExists5042);
                    exprComp27 = exprComp();
                    state._fsp--;

                    stream_exprComp.add(exprComp27.getTree());
                    // AST REWRITE
                    // elements: exprComp
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 559:7: -> exprComp
                    {
                        adaptor.addChild(root_0, stream_exprComp.nextTree());
                    }


                    retval.tree = root_0;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:560:3: ( ( ( EXISTS_IN e= exprComp ) -> ^( DATASET_EXISTS_IN_TAG $exprExists $e) ) | ( ( EXISTS_IN_ALL e= exprComp ) -> ^( DATASET_EXISTS_IN_ALL_TAG $exprExists $e) ) | ( ( NOT_EXISTS_IN e= exprComp ) -> ^( DATASET_NOT_EXISTS_IN_TAG $exprExists $e) ) | ( ( NOT_EXISTS_IN_ALL e= exprComp ) -> ^( DATASET_NOT_EXISTS_IN_ALL_TAG $exprExists $e) ) )*
                loop9:
                while (true) {
                    int alt9 = 5;
                    switch (input.LA(1)) {
                        case EXISTS_IN: {
                            alt9 = 1;
                        }
                        break;
                        case EXISTS_IN_ALL: {
                            alt9 = 2;
                        }
                        break;
                        case NOT_EXISTS_IN: {
                            alt9 = 3;
                        }
                        break;
                        case NOT_EXISTS_IN_ALL: {
                            alt9 = 4;
                        }
                        break;
                    }
                    switch (alt9) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:561:5: ( ( EXISTS_IN e= exprComp ) -> ^( DATASET_EXISTS_IN_TAG $exprExists $e) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:561:5: ( ( EXISTS_IN e= exprComp ) -> ^( DATASET_EXISTS_IN_TAG $exprExists $e) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:562:7: ( EXISTS_IN e= exprComp )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:562:7: ( EXISTS_IN e= exprComp )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:562:8: EXISTS_IN e= exprComp
                                {
                                    EXISTS_IN28 = (Token) match(input, EXISTS_IN, FOLLOW_EXISTS_IN_in_exprExists5072);
                                    stream_EXISTS_IN.add(EXISTS_IN28);

                                    pushFollow(FOLLOW_exprComp_in_exprExists5076);
                                    e = exprComp();
                                    state._fsp--;

                                    stream_exprComp.add(e.getTree());
                                }

                                // AST REWRITE
                                // elements: e, exprExists
                                // token labels:
                                // rule labels: e, retval
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_e = new RewriteRuleSubtreeStream(adaptor, "rule e", e != null ? e.getTree() : null);
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 563:9: -> ^( DATASET_EXISTS_IN_TAG $exprExists $e)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:564:11: ^( DATASET_EXISTS_IN_TAG $exprExists $e)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_EXISTS_IN_TAG, "DATASET_EXISTS_IN_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_e.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;
                        case 2:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:567:5: ( ( EXISTS_IN_ALL e= exprComp ) -> ^( DATASET_EXISTS_IN_ALL_TAG $exprExists $e) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:567:5: ( ( EXISTS_IN_ALL e= exprComp ) -> ^( DATASET_EXISTS_IN_ALL_TAG $exprExists $e) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:568:7: ( EXISTS_IN_ALL e= exprComp )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:568:7: ( EXISTS_IN_ALL e= exprComp )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:568:8: EXISTS_IN_ALL e= exprComp
                                {
                                    EXISTS_IN_ALL29 = (Token) match(input, EXISTS_IN_ALL, FOLLOW_EXISTS_IN_ALL_in_exprExists5134);
                                    stream_EXISTS_IN_ALL.add(EXISTS_IN_ALL29);

                                    pushFollow(FOLLOW_exprComp_in_exprExists5138);
                                    e = exprComp();
                                    state._fsp--;

                                    stream_exprComp.add(e.getTree());
                                }

                                // AST REWRITE
                                // elements: e, exprExists
                                // token labels:
                                // rule labels: e, retval
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_e = new RewriteRuleSubtreeStream(adaptor, "rule e", e != null ? e.getTree() : null);
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 569:9: -> ^( DATASET_EXISTS_IN_ALL_TAG $exprExists $e)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:570:11: ^( DATASET_EXISTS_IN_ALL_TAG $exprExists $e)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_EXISTS_IN_ALL_TAG, "DATASET_EXISTS_IN_ALL_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_e.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;
                        case 3:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:573:5: ( ( NOT_EXISTS_IN e= exprComp ) -> ^( DATASET_NOT_EXISTS_IN_TAG $exprExists $e) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:573:5: ( ( NOT_EXISTS_IN e= exprComp ) -> ^( DATASET_NOT_EXISTS_IN_TAG $exprExists $e) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:574:7: ( NOT_EXISTS_IN e= exprComp )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:574:7: ( NOT_EXISTS_IN e= exprComp )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:574:8: NOT_EXISTS_IN e= exprComp
                                {
                                    NOT_EXISTS_IN30 = (Token) match(input, NOT_EXISTS_IN, FOLLOW_NOT_EXISTS_IN_in_exprExists5196);
                                    stream_NOT_EXISTS_IN.add(NOT_EXISTS_IN30);

                                    pushFollow(FOLLOW_exprComp_in_exprExists5200);
                                    e = exprComp();
                                    state._fsp--;

                                    stream_exprComp.add(e.getTree());
                                }

                                // AST REWRITE
                                // elements: exprExists, e
                                // token labels:
                                // rule labels: e, retval
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_e = new RewriteRuleSubtreeStream(adaptor, "rule e", e != null ? e.getTree() : null);
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 575:9: -> ^( DATASET_NOT_EXISTS_IN_TAG $exprExists $e)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:576:11: ^( DATASET_NOT_EXISTS_IN_TAG $exprExists $e)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_NOT_EXISTS_IN_TAG, "DATASET_NOT_EXISTS_IN_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_e.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;
                        case 4:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:579:5: ( ( NOT_EXISTS_IN_ALL e= exprComp ) -> ^( DATASET_NOT_EXISTS_IN_ALL_TAG $exprExists $e) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:579:5: ( ( NOT_EXISTS_IN_ALL e= exprComp ) -> ^( DATASET_NOT_EXISTS_IN_ALL_TAG $exprExists $e) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:580:7: ( NOT_EXISTS_IN_ALL e= exprComp )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:580:7: ( NOT_EXISTS_IN_ALL e= exprComp )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:580:8: NOT_EXISTS_IN_ALL e= exprComp
                                {
                                    NOT_EXISTS_IN_ALL31 = (Token) match(input, NOT_EXISTS_IN_ALL, FOLLOW_NOT_EXISTS_IN_ALL_in_exprExists5258);
                                    stream_NOT_EXISTS_IN_ALL.add(NOT_EXISTS_IN_ALL31);

                                    pushFollow(FOLLOW_exprComp_in_exprExists5262);
                                    e = exprComp();
                                    state._fsp--;

                                    stream_exprComp.add(e.getTree());
                                }

                                // AST REWRITE
                                // elements: exprExists, e
                                // token labels:
                                // rule labels: e, retval
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_e = new RewriteRuleSubtreeStream(adaptor, "rule e", e != null ? e.getTree() : null);
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 581:9: -> ^( DATASET_NOT_EXISTS_IN_ALL_TAG $exprExists $e)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:582:11: ^( DATASET_NOT_EXISTS_IN_ALL_TAG $exprExists $e)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_NOT_EXISTS_IN_ALL_TAG, "DATASET_NOT_EXISTS_IN_ALL_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_e.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;

                        default:
                            break loop9;
                    }
                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "exprComp"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:589:1: exprComp : ( exprAdd -> exprAdd ) ( ( ( IN b2= setExpr ) -> ^( DATASET_IN_TAG $exprComp $b2) ) | ( ( NOT IN b2= setExpr ) -> ^( DATASET_NOT_IN_TAG $exprComp $b2) ) | ( ( ( GT | LT ) b2= exprAdd ) -> ^( DATASET_COMPARE_TAG $exprComp $b2) ) | ( ( BETWEEN b1= exprAdd AND b2= exprAdd ) -> ^( DATASET_BETWEEN_TAG $exprComp $b1 $b2) ) | ( ( NOT BETWEEN b1= exprAdd AND b2= exprAdd ) -> ^( DATASET_NOT_BETWEEN_TAG $exprComp $b1 $b2) ) )* ;
    public final ValidationMlParser.exprComp_return exprComp() throws Exception {
        ValidationMlParser.exprComp_return retval = new ValidationMlParser.exprComp_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IN33 = null;
        Token NOT34 = null;
        Token IN35 = null;
        Token GT36 = null;
        Token LT37 = null;
        Token BETWEEN38 = null;
        Token AND39 = null;
        Token NOT40 = null;
        Token BETWEEN41 = null;
        Token AND42 = null;
        ParserRuleReturnScope b2 = null;
        ParserRuleReturnScope b1 = null;
        ParserRuleReturnScope exprAdd32 = null;

        Object IN33_tree = null;
        Object NOT34_tree = null;
        Object IN35_tree = null;
        Object GT36_tree = null;
        Object LT37_tree = null;
        Object BETWEEN38_tree = null;
        Object AND39_tree = null;
        Object NOT40_tree = null;
        Object BETWEEN41_tree = null;
        Object AND42_tree = null;
        RewriteRuleTokenStream stream_NOT = new RewriteRuleTokenStream(adaptor, "token NOT");
        RewriteRuleTokenStream stream_IN = new RewriteRuleTokenStream(adaptor, "token IN");
        RewriteRuleTokenStream stream_AND = new RewriteRuleTokenStream(adaptor, "token AND");
        RewriteRuleTokenStream stream_LT = new RewriteRuleTokenStream(adaptor, "token LT");
        RewriteRuleTokenStream stream_BETWEEN = new RewriteRuleTokenStream(adaptor, "token BETWEEN");
        RewriteRuleTokenStream stream_GT = new RewriteRuleTokenStream(adaptor, "token GT");
        RewriteRuleSubtreeStream stream_exprAdd = new RewriteRuleSubtreeStream(adaptor, "rule exprAdd");
        RewriteRuleSubtreeStream stream_setExpr = new RewriteRuleSubtreeStream(adaptor, "rule setExpr");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:590:3: ( ( exprAdd -> exprAdd ) ( ( ( IN b2= setExpr ) -> ^( DATASET_IN_TAG $exprComp $b2) ) | ( ( NOT IN b2= setExpr ) -> ^( DATASET_NOT_IN_TAG $exprComp $b2) ) | ( ( ( GT | LT ) b2= exprAdd ) -> ^( DATASET_COMPARE_TAG $exprComp $b2) ) | ( ( BETWEEN b1= exprAdd AND b2= exprAdd ) -> ^( DATASET_BETWEEN_TAG $exprComp $b1 $b2) ) | ( ( NOT BETWEEN b1= exprAdd AND b2= exprAdd ) -> ^( DATASET_NOT_BETWEEN_TAG $exprComp $b1 $b2) ) )* )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:591:3: ( exprAdd -> exprAdd ) ( ( ( IN b2= setExpr ) -> ^( DATASET_IN_TAG $exprComp $b2) ) | ( ( NOT IN b2= setExpr ) -> ^( DATASET_NOT_IN_TAG $exprComp $b2) ) | ( ( ( GT | LT ) b2= exprAdd ) -> ^( DATASET_COMPARE_TAG $exprComp $b2) ) | ( ( BETWEEN b1= exprAdd AND b2= exprAdd ) -> ^( DATASET_BETWEEN_TAG $exprComp $b1 $b2) ) | ( ( NOT BETWEEN b1= exprAdd AND b2= exprAdd ) -> ^( DATASET_NOT_BETWEEN_TAG $exprComp $b1 $b2) ) )*
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:591:3: ( exprAdd -> exprAdd )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:591:4: exprAdd
                {
                    pushFollow(FOLLOW_exprAdd_in_exprComp5323);
                    exprAdd32 = exprAdd();
                    state._fsp--;

                    stream_exprAdd.add(exprAdd32.getTree());
                    // AST REWRITE
                    // elements: exprAdd
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 592:7: -> exprAdd
                    {
                        adaptor.addChild(root_0, stream_exprAdd.nextTree());
                    }


                    retval.tree = root_0;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:593:3: ( ( ( IN b2= setExpr ) -> ^( DATASET_IN_TAG $exprComp $b2) ) | ( ( NOT IN b2= setExpr ) -> ^( DATASET_NOT_IN_TAG $exprComp $b2) ) | ( ( ( GT | LT ) b2= exprAdd ) -> ^( DATASET_COMPARE_TAG $exprComp $b2) ) | ( ( BETWEEN b1= exprAdd AND b2= exprAdd ) -> ^( DATASET_BETWEEN_TAG $exprComp $b1 $b2) ) | ( ( NOT BETWEEN b1= exprAdd AND b2= exprAdd ) -> ^( DATASET_NOT_BETWEEN_TAG $exprComp $b1 $b2) ) )*
                loop11:
                while (true) {
                    int alt11 = 6;
                    switch (input.LA(1)) {
                        case IN: {
                            alt11 = 1;
                        }
                        break;
                        case NOT: {
                            int LA11_3 = input.LA(2);
                            if ((LA11_3 == IN)) {
                                alt11 = 2;
                            } else if ((LA11_3 == BETWEEN)) {
                                alt11 = 5;
                            }

                        }
                        break;
                        case GT:
                        case LT: {
                            alt11 = 3;
                        }
                        break;
                        case BETWEEN: {
                            alt11 = 4;
                        }
                        break;
                    }
                    switch (alt11) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:594:5: ( ( IN b2= setExpr ) -> ^( DATASET_IN_TAG $exprComp $b2) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:594:5: ( ( IN b2= setExpr ) -> ^( DATASET_IN_TAG $exprComp $b2) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:595:7: ( IN b2= setExpr )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:595:7: ( IN b2= setExpr )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:595:8: IN b2= setExpr
                                {
                                    IN33 = (Token) match(input, IN, FOLLOW_IN_in_exprComp5353);
                                    stream_IN.add(IN33);

                                    pushFollow(FOLLOW_setExpr_in_exprComp5357);
                                    b2 = setExpr();
                                    state._fsp--;

                                    stream_setExpr.add(b2.getTree());
                                }

                                // AST REWRITE
                                // elements: exprComp, b2
                                // token labels:
                                // rule labels: b2, retval
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 596:9: -> ^( DATASET_IN_TAG $exprComp $b2)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:597:11: ^( DATASET_IN_TAG $exprComp $b2)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_IN_TAG, "DATASET_IN_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_b2.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;
                        case 2:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:600:5: ( ( NOT IN b2= setExpr ) -> ^( DATASET_NOT_IN_TAG $exprComp $b2) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:600:5: ( ( NOT IN b2= setExpr ) -> ^( DATASET_NOT_IN_TAG $exprComp $b2) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:601:7: ( NOT IN b2= setExpr )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:601:7: ( NOT IN b2= setExpr )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:601:8: NOT IN b2= setExpr
                                {
                                    NOT34 = (Token) match(input, NOT, FOLLOW_NOT_in_exprComp5415);
                                    stream_NOT.add(NOT34);

                                    IN35 = (Token) match(input, IN, FOLLOW_IN_in_exprComp5417);
                                    stream_IN.add(IN35);

                                    pushFollow(FOLLOW_setExpr_in_exprComp5421);
                                    b2 = setExpr();
                                    state._fsp--;

                                    stream_setExpr.add(b2.getTree());
                                }

                                // AST REWRITE
                                // elements: b2, exprComp
                                // token labels:
                                // rule labels: b2, retval
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 602:9: -> ^( DATASET_NOT_IN_TAG $exprComp $b2)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:603:11: ^( DATASET_NOT_IN_TAG $exprComp $b2)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_NOT_IN_TAG, "DATASET_NOT_IN_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_b2.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;
                        case 3:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:606:5: ( ( ( GT | LT ) b2= exprAdd ) -> ^( DATASET_COMPARE_TAG $exprComp $b2) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:606:5: ( ( ( GT | LT ) b2= exprAdd ) -> ^( DATASET_COMPARE_TAG $exprComp $b2) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:607:7: ( ( GT | LT ) b2= exprAdd )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:607:7: ( ( GT | LT ) b2= exprAdd )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:608:9: ( GT | LT ) b2= exprAdd
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:608:9: ( GT | LT )
                                    int alt10 = 2;
                                    int LA10_0 = input.LA(1);
                                    if ((LA10_0 == GT)) {
                                        alt10 = 1;
                                    } else if ((LA10_0 == LT)) {
                                        alt10 = 2;
                                    } else {
                                        NoViableAltException nvae =
                                                new NoViableAltException("", 10, 0, input);
                                        throw nvae;
                                    }

                                    switch (alt10) {
                                        case 1:
                                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:609:11: GT
                                        {
                                            GT36 = (Token) match(input, GT, FOLLOW_GT_in_exprComp5500);
                                            stream_GT.add(GT36);

                                        }
                                        break;
                                        case 2:
                                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:610:13: LT
                                        {
                                            LT37 = (Token) match(input, LT, FOLLOW_LT_in_exprComp5514);
                                            stream_LT.add(LT37);

                                        }
                                        break;

                                    }

                                    pushFollow(FOLLOW_exprAdd_in_exprComp5536);
                                    b2 = exprAdd();
                                    state._fsp--;

                                    stream_exprAdd.add(b2.getTree());
                                }

                                // AST REWRITE
                                // elements: b2, exprComp
                                // token labels:
                                // rule labels: b2, retval
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 614:9: -> ^( DATASET_COMPARE_TAG $exprComp $b2)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:615:11: ^( DATASET_COMPARE_TAG $exprComp $b2)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_COMPARE_TAG, "DATASET_COMPARE_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_b2.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;
                        case 4:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:618:5: ( ( BETWEEN b1= exprAdd AND b2= exprAdd ) -> ^( DATASET_BETWEEN_TAG $exprComp $b1 $b2) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:618:5: ( ( BETWEEN b1= exprAdd AND b2= exprAdd ) -> ^( DATASET_BETWEEN_TAG $exprComp $b1 $b2) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:619:7: ( BETWEEN b1= exprAdd AND b2= exprAdd )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:619:7: ( BETWEEN b1= exprAdd AND b2= exprAdd )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:619:8: BETWEEN b1= exprAdd AND b2= exprAdd
                                {
                                    BETWEEN38 = (Token) match(input, BETWEEN, FOLLOW_BETWEEN_in_exprComp5601);
                                    stream_BETWEEN.add(BETWEEN38);

                                    pushFollow(FOLLOW_exprAdd_in_exprComp5605);
                                    b1 = exprAdd();
                                    state._fsp--;

                                    stream_exprAdd.add(b1.getTree());
                                    AND39 = (Token) match(input, AND, FOLLOW_AND_in_exprComp5607);
                                    stream_AND.add(AND39);

                                    pushFollow(FOLLOW_exprAdd_in_exprComp5611);
                                    b2 = exprAdd();
                                    state._fsp--;

                                    stream_exprAdd.add(b2.getTree());
                                }

                                // AST REWRITE
                                // elements: exprComp, b1, b2
                                // token labels:
                                // rule labels: b2, retval, b1
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                                RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 620:9: -> ^( DATASET_BETWEEN_TAG $exprComp $b1 $b2)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:621:11: ^( DATASET_BETWEEN_TAG $exprComp $b1 $b2)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_BETWEEN_TAG, "DATASET_BETWEEN_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_b1.nextTree());
                                        adaptor.addChild(root_1, stream_b2.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;
                        case 5:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:624:5: ( ( NOT BETWEEN b1= exprAdd AND b2= exprAdd ) -> ^( DATASET_NOT_BETWEEN_TAG $exprComp $b1 $b2) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:624:5: ( ( NOT BETWEEN b1= exprAdd AND b2= exprAdd ) -> ^( DATASET_NOT_BETWEEN_TAG $exprComp $b1 $b2) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:625:7: ( NOT BETWEEN b1= exprAdd AND b2= exprAdd )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:625:7: ( NOT BETWEEN b1= exprAdd AND b2= exprAdd )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:625:8: NOT BETWEEN b1= exprAdd AND b2= exprAdd
                                {
                                    NOT40 = (Token) match(input, NOT, FOLLOW_NOT_in_exprComp5672);
                                    stream_NOT.add(NOT40);

                                    BETWEEN41 = (Token) match(input, BETWEEN, FOLLOW_BETWEEN_in_exprComp5674);
                                    stream_BETWEEN.add(BETWEEN41);

                                    pushFollow(FOLLOW_exprAdd_in_exprComp5678);
                                    b1 = exprAdd();
                                    state._fsp--;

                                    stream_exprAdd.add(b1.getTree());
                                    AND42 = (Token) match(input, AND, FOLLOW_AND_in_exprComp5680);
                                    stream_AND.add(AND42);

                                    pushFollow(FOLLOW_exprAdd_in_exprComp5684);
                                    b2 = exprAdd();
                                    state._fsp--;

                                    stream_exprAdd.add(b2.getTree());
                                }

                                // AST REWRITE
                                // elements: b2, exprComp, b1
                                // token labels:
                                // rule labels: b2, retval, b1
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                                RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 626:9: -> ^( DATASET_NOT_BETWEEN_TAG $exprComp $b1 $b2)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:627:11: ^( DATASET_NOT_BETWEEN_TAG $exprComp $b1 $b2)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_NOT_BETWEEN_TAG, "DATASET_NOT_BETWEEN_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_b1.nextTree());
                                        adaptor.addChild(root_1, stream_b2.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;

                        default:
                            break loop11;
                    }
                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "exprAdd"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:634:1: exprAdd : ( exprMultiply -> exprMultiply ) ( ( ( ( PLUSPLUS b1= exprMultiply ) -> ^( DATASET_PLUSPLUS_TAG $exprAdd $b1) ) | ( ( MINUSMINUS b1= exprMultiply ) -> ^( DATASET_MINUSMINUS_TAG $exprAdd $b1) ) | ( ( PLUS b1= exprMultiply ) -> ^( DATASET_PLUS_TAG $exprAdd $b1) ) | ( ( MINUS b1= exprMultiply ) -> ^( DATASET_MINUS_TAG $exprAdd $b1) ) ) )* ;
    public final ValidationMlParser.exprAdd_return exprAdd() throws Exception {
        ValidationMlParser.exprAdd_return retval = new ValidationMlParser.exprAdd_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PLUSPLUS44 = null;
        Token MINUSMINUS45 = null;
        Token PLUS46 = null;
        Token MINUS47 = null;
        ParserRuleReturnScope b1 = null;
        ParserRuleReturnScope exprMultiply43 = null;

        Object PLUSPLUS44_tree = null;
        Object MINUSMINUS45_tree = null;
        Object PLUS46_tree = null;
        Object MINUS47_tree = null;
        RewriteRuleTokenStream stream_MINUSMINUS = new RewriteRuleTokenStream(adaptor, "token MINUSMINUS");
        RewriteRuleTokenStream stream_PLUSPLUS = new RewriteRuleTokenStream(adaptor, "token PLUSPLUS");
        RewriteRuleTokenStream stream_PLUS = new RewriteRuleTokenStream(adaptor, "token PLUS");
        RewriteRuleTokenStream stream_MINUS = new RewriteRuleTokenStream(adaptor, "token MINUS");
        RewriteRuleSubtreeStream stream_exprMultiply = new RewriteRuleSubtreeStream(adaptor, "rule exprMultiply");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:635:3: ( ( exprMultiply -> exprMultiply ) ( ( ( ( PLUSPLUS b1= exprMultiply ) -> ^( DATASET_PLUSPLUS_TAG $exprAdd $b1) ) | ( ( MINUSMINUS b1= exprMultiply ) -> ^( DATASET_MINUSMINUS_TAG $exprAdd $b1) ) | ( ( PLUS b1= exprMultiply ) -> ^( DATASET_PLUS_TAG $exprAdd $b1) ) | ( ( MINUS b1= exprMultiply ) -> ^( DATASET_MINUS_TAG $exprAdd $b1) ) ) )* )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:636:3: ( exprMultiply -> exprMultiply ) ( ( ( ( PLUSPLUS b1= exprMultiply ) -> ^( DATASET_PLUSPLUS_TAG $exprAdd $b1) ) | ( ( MINUSMINUS b1= exprMultiply ) -> ^( DATASET_MINUSMINUS_TAG $exprAdd $b1) ) | ( ( PLUS b1= exprMultiply ) -> ^( DATASET_PLUS_TAG $exprAdd $b1) ) | ( ( MINUS b1= exprMultiply ) -> ^( DATASET_MINUS_TAG $exprAdd $b1) ) ) )*
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:636:3: ( exprMultiply -> exprMultiply )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:636:4: exprMultiply
                {
                    pushFollow(FOLLOW_exprMultiply_in_exprAdd5748);
                    exprMultiply43 = exprMultiply();
                    state._fsp--;

                    stream_exprMultiply.add(exprMultiply43.getTree());
                    // AST REWRITE
                    // elements: exprMultiply
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 637:7: -> exprMultiply
                    {
                        adaptor.addChild(root_0, stream_exprMultiply.nextTree());
                    }


                    retval.tree = root_0;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:638:3: ( ( ( ( PLUSPLUS b1= exprMultiply ) -> ^( DATASET_PLUSPLUS_TAG $exprAdd $b1) ) | ( ( MINUSMINUS b1= exprMultiply ) -> ^( DATASET_MINUSMINUS_TAG $exprAdd $b1) ) | ( ( PLUS b1= exprMultiply ) -> ^( DATASET_PLUS_TAG $exprAdd $b1) ) | ( ( MINUS b1= exprMultiply ) -> ^( DATASET_MINUS_TAG $exprAdd $b1) ) ) )*
                loop13:
                while (true) {
                    int alt13 = 2;
                    int LA13_0 = input.LA(1);
                    if ((LA13_0 == MINUS || LA13_0 == MINUSMINUS || LA13_0 == PLUS || LA13_0 == PLUSPLUS)) {
                        alt13 = 1;
                    }

                    switch (alt13) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:639:5: ( ( ( PLUSPLUS b1= exprMultiply ) -> ^( DATASET_PLUSPLUS_TAG $exprAdd $b1) ) | ( ( MINUSMINUS b1= exprMultiply ) -> ^( DATASET_MINUSMINUS_TAG $exprAdd $b1) ) | ( ( PLUS b1= exprMultiply ) -> ^( DATASET_PLUS_TAG $exprAdd $b1) ) | ( ( MINUS b1= exprMultiply ) -> ^( DATASET_MINUS_TAG $exprAdd $b1) ) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:639:5: ( ( ( PLUSPLUS b1= exprMultiply ) -> ^( DATASET_PLUSPLUS_TAG $exprAdd $b1) ) | ( ( MINUSMINUS b1= exprMultiply ) -> ^( DATASET_MINUSMINUS_TAG $exprAdd $b1) ) | ( ( PLUS b1= exprMultiply ) -> ^( DATASET_PLUS_TAG $exprAdd $b1) ) | ( ( MINUS b1= exprMultiply ) -> ^( DATASET_MINUS_TAG $exprAdd $b1) ) )
                            int alt12 = 4;
                            switch (input.LA(1)) {
                                case PLUSPLUS: {
                                    alt12 = 1;
                                }
                                break;
                                case MINUSMINUS: {
                                    alt12 = 2;
                                }
                                break;
                                case PLUS: {
                                    alt12 = 3;
                                }
                                break;
                                case MINUS: {
                                    alt12 = 4;
                                }
                                break;
                                default:
                                    NoViableAltException nvae =
                                            new NoViableAltException("", 12, 0, input);
                                    throw nvae;
                            }
                            switch (alt12) {
                                case 1:
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:640:7: ( ( PLUSPLUS b1= exprMultiply ) -> ^( DATASET_PLUSPLUS_TAG $exprAdd $b1) )
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:640:7: ( ( PLUSPLUS b1= exprMultiply ) -> ^( DATASET_PLUSPLUS_TAG $exprAdd $b1) )
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:641:9: ( PLUSPLUS b1= exprMultiply )
                                    {
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:641:9: ( PLUSPLUS b1= exprMultiply )
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:641:10: PLUSPLUS b1= exprMultiply
                                        {
                                            PLUSPLUS44 = (Token) match(input, PLUSPLUS, FOLLOW_PLUSPLUS_in_exprAdd5788);
                                            stream_PLUSPLUS.add(PLUSPLUS44);

                                            pushFollow(FOLLOW_exprMultiply_in_exprAdd5792);
                                            b1 = exprMultiply();
                                            state._fsp--;

                                            stream_exprMultiply.add(b1.getTree());
                                        }

                                        // AST REWRITE
                                        // elements: b1, exprAdd
                                        // token labels:
                                        // rule labels: retval, b1
                                        // token list labels:
                                        // rule list labels:
                                        // wildcard labels:
                                        retval.tree = root_0;
                                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                                        root_0 = (Object) adaptor.nil();
                                        // 642:11: -> ^( DATASET_PLUSPLUS_TAG $exprAdd $b1)
                                        {
                                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:643:13: ^( DATASET_PLUSPLUS_TAG $exprAdd $b1)
                                            {
                                                Object root_1 = (Object) adaptor.nil();
                                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_PLUSPLUS_TAG, "DATASET_PLUSPLUS_TAG"), root_1);
                                                adaptor.addChild(root_1, stream_retval.nextTree());
                                                adaptor.addChild(root_1, stream_b1.nextTree());
                                                adaptor.addChild(root_0, root_1);
                                            }

                                        }


                                        retval.tree = root_0;

                                    }

                                }
                                break;
                                case 2:
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:646:7: ( ( MINUSMINUS b1= exprMultiply ) -> ^( DATASET_MINUSMINUS_TAG $exprAdd $b1) )
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:646:7: ( ( MINUSMINUS b1= exprMultiply ) -> ^( DATASET_MINUSMINUS_TAG $exprAdd $b1) )
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:647:9: ( MINUSMINUS b1= exprMultiply )
                                    {
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:647:9: ( MINUSMINUS b1= exprMultiply )
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:647:10: MINUSMINUS b1= exprMultiply
                                        {
                                            MINUSMINUS45 = (Token) match(input, MINUSMINUS, FOLLOW_MINUSMINUS_in_exprAdd5862);
                                            stream_MINUSMINUS.add(MINUSMINUS45);

                                            pushFollow(FOLLOW_exprMultiply_in_exprAdd5866);
                                            b1 = exprMultiply();
                                            state._fsp--;

                                            stream_exprMultiply.add(b1.getTree());
                                        }

                                        // AST REWRITE
                                        // elements: b1, exprAdd
                                        // token labels:
                                        // rule labels: retval, b1
                                        // token list labels:
                                        // rule list labels:
                                        // wildcard labels:
                                        retval.tree = root_0;
                                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                                        root_0 = (Object) adaptor.nil();
                                        // 648:11: -> ^( DATASET_MINUSMINUS_TAG $exprAdd $b1)
                                        {
                                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:649:13: ^( DATASET_MINUSMINUS_TAG $exprAdd $b1)
                                            {
                                                Object root_1 = (Object) adaptor.nil();
                                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_MINUSMINUS_TAG, "DATASET_MINUSMINUS_TAG"), root_1);
                                                adaptor.addChild(root_1, stream_retval.nextTree());
                                                adaptor.addChild(root_1, stream_b1.nextTree());
                                                adaptor.addChild(root_0, root_1);
                                            }

                                        }


                                        retval.tree = root_0;

                                    }

                                }
                                break;
                                case 3:
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:652:7: ( ( PLUS b1= exprMultiply ) -> ^( DATASET_PLUS_TAG $exprAdd $b1) )
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:652:7: ( ( PLUS b1= exprMultiply ) -> ^( DATASET_PLUS_TAG $exprAdd $b1) )
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:653:9: ( PLUS b1= exprMultiply )
                                    {
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:653:9: ( PLUS b1= exprMultiply )
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:653:10: PLUS b1= exprMultiply
                                        {
                                            PLUS46 = (Token) match(input, PLUS, FOLLOW_PLUS_in_exprAdd5936);
                                            stream_PLUS.add(PLUS46);

                                            pushFollow(FOLLOW_exprMultiply_in_exprAdd5940);
                                            b1 = exprMultiply();
                                            state._fsp--;

                                            stream_exprMultiply.add(b1.getTree());
                                        }

                                        // AST REWRITE
                                        // elements: exprAdd, b1
                                        // token labels:
                                        // rule labels: retval, b1
                                        // token list labels:
                                        // rule list labels:
                                        // wildcard labels:
                                        retval.tree = root_0;
                                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                                        root_0 = (Object) adaptor.nil();
                                        // 654:11: -> ^( DATASET_PLUS_TAG $exprAdd $b1)
                                        {
                                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:655:13: ^( DATASET_PLUS_TAG $exprAdd $b1)
                                            {
                                                Object root_1 = (Object) adaptor.nil();
                                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_PLUS_TAG, "DATASET_PLUS_TAG"), root_1);
                                                adaptor.addChild(root_1, stream_retval.nextTree());
                                                adaptor.addChild(root_1, stream_b1.nextTree());
                                                adaptor.addChild(root_0, root_1);
                                            }

                                        }


                                        retval.tree = root_0;

                                    }

                                }
                                break;
                                case 4:
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:658:7: ( ( MINUS b1= exprMultiply ) -> ^( DATASET_MINUS_TAG $exprAdd $b1) )
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:658:7: ( ( MINUS b1= exprMultiply ) -> ^( DATASET_MINUS_TAG $exprAdd $b1) )
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:659:9: ( MINUS b1= exprMultiply )
                                    {
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:659:9: ( MINUS b1= exprMultiply )
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:659:10: MINUS b1= exprMultiply
                                        {
                                            MINUS47 = (Token) match(input, MINUS, FOLLOW_MINUS_in_exprAdd6010);
                                            stream_MINUS.add(MINUS47);

                                            pushFollow(FOLLOW_exprMultiply_in_exprAdd6014);
                                            b1 = exprMultiply();
                                            state._fsp--;

                                            stream_exprMultiply.add(b1.getTree());
                                        }

                                        // AST REWRITE
                                        // elements: b1, exprAdd
                                        // token labels:
                                        // rule labels: retval, b1
                                        // token list labels:
                                        // rule list labels:
                                        // wildcard labels:
                                        retval.tree = root_0;
                                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                                        root_0 = (Object) adaptor.nil();
                                        // 660:11: -> ^( DATASET_MINUS_TAG $exprAdd $b1)
                                        {
                                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:661:13: ^( DATASET_MINUS_TAG $exprAdd $b1)
                                            {
                                                Object root_1 = (Object) adaptor.nil();
                                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_MINUS_TAG, "DATASET_MINUS_TAG"), root_1);
                                                adaptor.addChild(root_1, stream_retval.nextTree());
                                                adaptor.addChild(root_1, stream_b1.nextTree());
                                                adaptor.addChild(root_0, root_1);
                                            }

                                        }


                                        retval.tree = root_0;

                                    }

                                }
                                break;

                            }

                        }
                        break;

                        default:
                            break loop13;
                    }
                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "exprMultiply"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:669:1: exprMultiply : ( exprFactor -> exprFactor ) ( ( ( ( MULTMULT b1= exprFactor ) -> ^( DATASET_MULTMULT_TAG $exprMultiply $b1) ) | ( ( DIVDIV b1= exprFactor ) -> ^( DATASET_DIVDIV_TAG $exprMultiply $b1) ) | ( ( MULTIPLY b1= exprFactor ) -> ^( DATASET_MULTIPLY_TAG $exprMultiply $b1) ) | ( ( DIVIDE b1= exprFactor ) -> ^( DATASET_DIVIDE_TAG $exprMultiply $b1) ) | ( ( PERCENT b1= exprFactor ) -> ^( DATASET_PERCENT_TAG $exprMultiply $b1) ) ) )* ;
    public final ValidationMlParser.exprMultiply_return exprMultiply() throws Exception {
        ValidationMlParser.exprMultiply_return retval = new ValidationMlParser.exprMultiply_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MULTMULT49 = null;
        Token DIVDIV50 = null;
        Token MULTIPLY51 = null;
        Token DIVIDE52 = null;
        Token PERCENT53 = null;
        ParserRuleReturnScope b1 = null;
        ParserRuleReturnScope exprFactor48 = null;

        Object MULTMULT49_tree = null;
        Object DIVDIV50_tree = null;
        Object MULTIPLY51_tree = null;
        Object DIVIDE52_tree = null;
        Object PERCENT53_tree = null;
        RewriteRuleTokenStream stream_MULTMULT = new RewriteRuleTokenStream(adaptor, "token MULTMULT");
        RewriteRuleTokenStream stream_DIVDIV = new RewriteRuleTokenStream(adaptor, "token DIVDIV");
        RewriteRuleTokenStream stream_PERCENT = new RewriteRuleTokenStream(adaptor, "token PERCENT");
        RewriteRuleTokenStream stream_MULTIPLY = new RewriteRuleTokenStream(adaptor, "token MULTIPLY");
        RewriteRuleTokenStream stream_DIVIDE = new RewriteRuleTokenStream(adaptor, "token DIVIDE");
        RewriteRuleSubtreeStream stream_exprFactor = new RewriteRuleSubtreeStream(adaptor, "rule exprFactor");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:670:3: ( ( exprFactor -> exprFactor ) ( ( ( ( MULTMULT b1= exprFactor ) -> ^( DATASET_MULTMULT_TAG $exprMultiply $b1) ) | ( ( DIVDIV b1= exprFactor ) -> ^( DATASET_DIVDIV_TAG $exprMultiply $b1) ) | ( ( MULTIPLY b1= exprFactor ) -> ^( DATASET_MULTIPLY_TAG $exprMultiply $b1) ) | ( ( DIVIDE b1= exprFactor ) -> ^( DATASET_DIVIDE_TAG $exprMultiply $b1) ) | ( ( PERCENT b1= exprFactor ) -> ^( DATASET_PERCENT_TAG $exprMultiply $b1) ) ) )* )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:671:3: ( exprFactor -> exprFactor ) ( ( ( ( MULTMULT b1= exprFactor ) -> ^( DATASET_MULTMULT_TAG $exprMultiply $b1) ) | ( ( DIVDIV b1= exprFactor ) -> ^( DATASET_DIVDIV_TAG $exprMultiply $b1) ) | ( ( MULTIPLY b1= exprFactor ) -> ^( DATASET_MULTIPLY_TAG $exprMultiply $b1) ) | ( ( DIVIDE b1= exprFactor ) -> ^( DATASET_DIVIDE_TAG $exprMultiply $b1) ) | ( ( PERCENT b1= exprFactor ) -> ^( DATASET_PERCENT_TAG $exprMultiply $b1) ) ) )*
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:671:3: ( exprFactor -> exprFactor )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:671:4: exprFactor
                {
                    pushFollow(FOLLOW_exprFactor_in_exprMultiply6087);
                    exprFactor48 = exprFactor();
                    state._fsp--;

                    stream_exprFactor.add(exprFactor48.getTree());
                    // AST REWRITE
                    // elements: exprFactor
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 672:7: -> exprFactor
                    {
                        adaptor.addChild(root_0, stream_exprFactor.nextTree());
                    }


                    retval.tree = root_0;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:673:3: ( ( ( ( MULTMULT b1= exprFactor ) -> ^( DATASET_MULTMULT_TAG $exprMultiply $b1) ) | ( ( DIVDIV b1= exprFactor ) -> ^( DATASET_DIVDIV_TAG $exprMultiply $b1) ) | ( ( MULTIPLY b1= exprFactor ) -> ^( DATASET_MULTIPLY_TAG $exprMultiply $b1) ) | ( ( DIVIDE b1= exprFactor ) -> ^( DATASET_DIVIDE_TAG $exprMultiply $b1) ) | ( ( PERCENT b1= exprFactor ) -> ^( DATASET_PERCENT_TAG $exprMultiply $b1) ) ) )*
                loop15:
                while (true) {
                    int alt15 = 2;
                    int LA15_0 = input.LA(1);
                    if ((LA15_0 == DIVDIV || LA15_0 == DIVIDE || (LA15_0 >= MULTIPLY && LA15_0 <= MULTMULT) || LA15_0 == PERCENT)) {
                        alt15 = 1;
                    }

                    switch (alt15) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:674:5: ( ( ( MULTMULT b1= exprFactor ) -> ^( DATASET_MULTMULT_TAG $exprMultiply $b1) ) | ( ( DIVDIV b1= exprFactor ) -> ^( DATASET_DIVDIV_TAG $exprMultiply $b1) ) | ( ( MULTIPLY b1= exprFactor ) -> ^( DATASET_MULTIPLY_TAG $exprMultiply $b1) ) | ( ( DIVIDE b1= exprFactor ) -> ^( DATASET_DIVIDE_TAG $exprMultiply $b1) ) | ( ( PERCENT b1= exprFactor ) -> ^( DATASET_PERCENT_TAG $exprMultiply $b1) ) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:674:5: ( ( ( MULTMULT b1= exprFactor ) -> ^( DATASET_MULTMULT_TAG $exprMultiply $b1) ) | ( ( DIVDIV b1= exprFactor ) -> ^( DATASET_DIVDIV_TAG $exprMultiply $b1) ) | ( ( MULTIPLY b1= exprFactor ) -> ^( DATASET_MULTIPLY_TAG $exprMultiply $b1) ) | ( ( DIVIDE b1= exprFactor ) -> ^( DATASET_DIVIDE_TAG $exprMultiply $b1) ) | ( ( PERCENT b1= exprFactor ) -> ^( DATASET_PERCENT_TAG $exprMultiply $b1) ) )
                            int alt14 = 5;
                            switch (input.LA(1)) {
                                case MULTMULT: {
                                    alt14 = 1;
                                }
                                break;
                                case DIVDIV: {
                                    alt14 = 2;
                                }
                                break;
                                case MULTIPLY: {
                                    alt14 = 3;
                                }
                                break;
                                case DIVIDE: {
                                    alt14 = 4;
                                }
                                break;
                                case PERCENT: {
                                    alt14 = 5;
                                }
                                break;
                                default:
                                    NoViableAltException nvae =
                                            new NoViableAltException("", 14, 0, input);
                                    throw nvae;
                            }
                            switch (alt14) {
                                case 1:
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:675:7: ( ( MULTMULT b1= exprFactor ) -> ^( DATASET_MULTMULT_TAG $exprMultiply $b1) )
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:675:7: ( ( MULTMULT b1= exprFactor ) -> ^( DATASET_MULTMULT_TAG $exprMultiply $b1) )
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:676:9: ( MULTMULT b1= exprFactor )
                                    {
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:676:9: ( MULTMULT b1= exprFactor )
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:676:10: MULTMULT b1= exprFactor
                                        {
                                            MULTMULT49 = (Token) match(input, MULTMULT, FOLLOW_MULTMULT_in_exprMultiply6127);
                                            stream_MULTMULT.add(MULTMULT49);

                                            pushFollow(FOLLOW_exprFactor_in_exprMultiply6131);
                                            b1 = exprFactor();
                                            state._fsp--;

                                            stream_exprFactor.add(b1.getTree());
                                        }

                                        // AST REWRITE
                                        // elements: exprMultiply, b1
                                        // token labels:
                                        // rule labels: retval, b1
                                        // token list labels:
                                        // rule list labels:
                                        // wildcard labels:
                                        retval.tree = root_0;
                                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                                        root_0 = (Object) adaptor.nil();
                                        // 677:11: -> ^( DATASET_MULTMULT_TAG $exprMultiply $b1)
                                        {
                                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:678:13: ^( DATASET_MULTMULT_TAG $exprMultiply $b1)
                                            {
                                                Object root_1 = (Object) adaptor.nil();
                                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_MULTMULT_TAG, "DATASET_MULTMULT_TAG"), root_1);
                                                adaptor.addChild(root_1, stream_retval.nextTree());
                                                adaptor.addChild(root_1, stream_b1.nextTree());
                                                adaptor.addChild(root_0, root_1);
                                            }

                                        }


                                        retval.tree = root_0;

                                    }

                                }
                                break;
                                case 2:
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:681:7: ( ( DIVDIV b1= exprFactor ) -> ^( DATASET_DIVDIV_TAG $exprMultiply $b1) )
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:681:7: ( ( DIVDIV b1= exprFactor ) -> ^( DATASET_DIVDIV_TAG $exprMultiply $b1) )
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:682:9: ( DIVDIV b1= exprFactor )
                                    {
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:682:9: ( DIVDIV b1= exprFactor )
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:682:10: DIVDIV b1= exprFactor
                                        {
                                            DIVDIV50 = (Token) match(input, DIVDIV, FOLLOW_DIVDIV_in_exprMultiply6201);
                                            stream_DIVDIV.add(DIVDIV50);

                                            pushFollow(FOLLOW_exprFactor_in_exprMultiply6205);
                                            b1 = exprFactor();
                                            state._fsp--;

                                            stream_exprFactor.add(b1.getTree());
                                        }

                                        // AST REWRITE
                                        // elements: exprMultiply, b1
                                        // token labels:
                                        // rule labels: retval, b1
                                        // token list labels:
                                        // rule list labels:
                                        // wildcard labels:
                                        retval.tree = root_0;
                                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                                        root_0 = (Object) adaptor.nil();
                                        // 683:11: -> ^( DATASET_DIVDIV_TAG $exprMultiply $b1)
                                        {
                                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:684:13: ^( DATASET_DIVDIV_TAG $exprMultiply $b1)
                                            {
                                                Object root_1 = (Object) adaptor.nil();
                                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_DIVDIV_TAG, "DATASET_DIVDIV_TAG"), root_1);
                                                adaptor.addChild(root_1, stream_retval.nextTree());
                                                adaptor.addChild(root_1, stream_b1.nextTree());
                                                adaptor.addChild(root_0, root_1);
                                            }

                                        }


                                        retval.tree = root_0;

                                    }

                                }
                                break;
                                case 3:
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:687:7: ( ( MULTIPLY b1= exprFactor ) -> ^( DATASET_MULTIPLY_TAG $exprMultiply $b1) )
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:687:7: ( ( MULTIPLY b1= exprFactor ) -> ^( DATASET_MULTIPLY_TAG $exprMultiply $b1) )
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:688:9: ( MULTIPLY b1= exprFactor )
                                    {
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:688:9: ( MULTIPLY b1= exprFactor )
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:688:10: MULTIPLY b1= exprFactor
                                        {
                                            MULTIPLY51 = (Token) match(input, MULTIPLY, FOLLOW_MULTIPLY_in_exprMultiply6275);
                                            stream_MULTIPLY.add(MULTIPLY51);

                                            pushFollow(FOLLOW_exprFactor_in_exprMultiply6279);
                                            b1 = exprFactor();
                                            state._fsp--;

                                            stream_exprFactor.add(b1.getTree());
                                        }

                                        // AST REWRITE
                                        // elements: exprMultiply, b1
                                        // token labels:
                                        // rule labels: retval, b1
                                        // token list labels:
                                        // rule list labels:
                                        // wildcard labels:
                                        retval.tree = root_0;
                                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                                        root_0 = (Object) adaptor.nil();
                                        // 689:11: -> ^( DATASET_MULTIPLY_TAG $exprMultiply $b1)
                                        {
                                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:690:13: ^( DATASET_MULTIPLY_TAG $exprMultiply $b1)
                                            {
                                                Object root_1 = (Object) adaptor.nil();
                                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_MULTIPLY_TAG, "DATASET_MULTIPLY_TAG"), root_1);
                                                adaptor.addChild(root_1, stream_retval.nextTree());
                                                adaptor.addChild(root_1, stream_b1.nextTree());
                                                adaptor.addChild(root_0, root_1);
                                            }

                                        }


                                        retval.tree = root_0;

                                    }

                                }
                                break;
                                case 4:
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:693:7: ( ( DIVIDE b1= exprFactor ) -> ^( DATASET_DIVIDE_TAG $exprMultiply $b1) )
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:693:7: ( ( DIVIDE b1= exprFactor ) -> ^( DATASET_DIVIDE_TAG $exprMultiply $b1) )
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:694:9: ( DIVIDE b1= exprFactor )
                                    {
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:694:9: ( DIVIDE b1= exprFactor )
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:694:10: DIVIDE b1= exprFactor
                                        {
                                            DIVIDE52 = (Token) match(input, DIVIDE, FOLLOW_DIVIDE_in_exprMultiply6349);
                                            stream_DIVIDE.add(DIVIDE52);

                                            pushFollow(FOLLOW_exprFactor_in_exprMultiply6353);
                                            b1 = exprFactor();
                                            state._fsp--;

                                            stream_exprFactor.add(b1.getTree());
                                        }

                                        // AST REWRITE
                                        // elements: exprMultiply, b1
                                        // token labels:
                                        // rule labels: retval, b1
                                        // token list labels:
                                        // rule list labels:
                                        // wildcard labels:
                                        retval.tree = root_0;
                                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                                        root_0 = (Object) adaptor.nil();
                                        // 695:11: -> ^( DATASET_DIVIDE_TAG $exprMultiply $b1)
                                        {
                                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:696:13: ^( DATASET_DIVIDE_TAG $exprMultiply $b1)
                                            {
                                                Object root_1 = (Object) adaptor.nil();
                                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_DIVIDE_TAG, "DATASET_DIVIDE_TAG"), root_1);
                                                adaptor.addChild(root_1, stream_retval.nextTree());
                                                adaptor.addChild(root_1, stream_b1.nextTree());
                                                adaptor.addChild(root_0, root_1);
                                            }

                                        }


                                        retval.tree = root_0;

                                    }

                                }
                                break;
                                case 5:
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:699:7: ( ( PERCENT b1= exprFactor ) -> ^( DATASET_PERCENT_TAG $exprMultiply $b1) )
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:699:7: ( ( PERCENT b1= exprFactor ) -> ^( DATASET_PERCENT_TAG $exprMultiply $b1) )
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:700:9: ( PERCENT b1= exprFactor )
                                    {
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:700:9: ( PERCENT b1= exprFactor )
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:700:10: PERCENT b1= exprFactor
                                        {
                                            PERCENT53 = (Token) match(input, PERCENT, FOLLOW_PERCENT_in_exprMultiply6423);
                                            stream_PERCENT.add(PERCENT53);

                                            pushFollow(FOLLOW_exprFactor_in_exprMultiply6427);
                                            b1 = exprFactor();
                                            state._fsp--;

                                            stream_exprFactor.add(b1.getTree());
                                        }

                                        // AST REWRITE
                                        // elements: exprMultiply, b1
                                        // token labels:
                                        // rule labels: retval, b1
                                        // token list labels:
                                        // rule list labels:
                                        // wildcard labels:
                                        retval.tree = root_0;
                                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                                        root_0 = (Object) adaptor.nil();
                                        // 701:11: -> ^( DATASET_PERCENT_TAG $exprMultiply $b1)
                                        {
                                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:702:13: ^( DATASET_PERCENT_TAG $exprMultiply $b1)
                                            {
                                                Object root_1 = (Object) adaptor.nil();
                                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_PERCENT_TAG, "DATASET_PERCENT_TAG"), root_1);
                                                adaptor.addChild(root_1, stream_retval.nextTree());
                                                adaptor.addChild(root_1, stream_b1.nextTree());
                                                adaptor.addChild(root_0, root_1);
                                            }

                                        }


                                        retval.tree = root_0;

                                    }

                                }
                                break;

                            }

                        }
                        break;

                        default:
                            break loop15;
                    }
                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "exprFactor"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:710:1: exprFactor : ( ( exprMember -> exprMember ) | ( PLUS exprMember -> ^( DATASET_PLUS_UNARY_TAG exprMember ) ) | ( MINUS exprMember -> ^( DATASET_MINUS_UNARY_TAG exprMember ) ) | NOT exprMember );
    public final ValidationMlParser.exprFactor_return exprFactor() throws Exception {
        ValidationMlParser.exprFactor_return retval = new ValidationMlParser.exprFactor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PLUS55 = null;
        Token MINUS57 = null;
        Token NOT59 = null;
        ParserRuleReturnScope exprMember54 = null;
        ParserRuleReturnScope exprMember56 = null;
        ParserRuleReturnScope exprMember58 = null;
        ParserRuleReturnScope exprMember60 = null;

        Object PLUS55_tree = null;
        Object MINUS57_tree = null;
        Object NOT59_tree = null;
        RewriteRuleTokenStream stream_PLUS = new RewriteRuleTokenStream(adaptor, "token PLUS");
        RewriteRuleTokenStream stream_MINUS = new RewriteRuleTokenStream(adaptor, "token MINUS");
        RewriteRuleSubtreeStream stream_exprMember = new RewriteRuleSubtreeStream(adaptor, "rule exprMember");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:711:3: ( ( exprMember -> exprMember ) | ( PLUS exprMember -> ^( DATASET_PLUS_UNARY_TAG exprMember ) ) | ( MINUS exprMember -> ^( DATASET_MINUS_UNARY_TAG exprMember ) ) | NOT exprMember )
            int alt16 = 4;
            switch (input.LA(1)) {
                case ABS:
                case BOOLEAN_CONSTANT:
                case CHARLENGTH:
                case CHARSET_MATCH:
                case CHECK:
                case CODELIST_MATCH:
                case DIFF:
                case EVAL:
                case EXP:
                case FLOAT_CONSTANT:
                case GET:
                case HIERARCHY:
                case IDENTIFIER:
                case INDEXOF:
                case INTEGER_CONSTANT:
                case INTERSECT:
                case ISNULL:
                case LCASE:
                case LEN:
                case LN:
                case LOG:
                case MAX:
                case MERGE:
                case MIN:
                case MISSING:
                case MOD:
                case NOT_IN:
                case NROOT:
                case NULL_CONSTANT:
                case NVL:
                case POWER:
                case PUT:
                case ROUND:
                case STRING_CONSTANT:
                case SUBSTR:
                case TRIM:
                case TRUNC:
                case TYPE:
                case UCASE:
                case UNION:
                case 426: {
                    alt16 = 1;
                }
                break;
                case PLUS: {
                    alt16 = 2;
                }
                break;
                case MINUS: {
                    alt16 = 3;
                }
                break;
                case NOT: {
                    alt16 = 4;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 16, 0, input);
                    throw nvae;
            }
            switch (alt16) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:712:3: ( exprMember -> exprMember )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:712:3: ( exprMember -> exprMember )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:712:4: exprMember
                    {
                        pushFollow(FOLLOW_exprMember_in_exprFactor6500);
                        exprMember54 = exprMember();
                        state._fsp--;

                        stream_exprMember.add(exprMember54.getTree());
                        // AST REWRITE
                        // elements: exprMember
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 713:7: -> exprMember
                        {
                            adaptor.addChild(root_0, stream_exprMember.nextTree());
                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:714:5: ( PLUS exprMember -> ^( DATASET_PLUS_UNARY_TAG exprMember ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:714:5: ( PLUS exprMember -> ^( DATASET_PLUS_UNARY_TAG exprMember ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:714:6: PLUS exprMember
                    {
                        PLUS55 = (Token) match(input, PLUS, FOLLOW_PLUS_in_exprFactor6518);
                        stream_PLUS.add(PLUS55);

                        pushFollow(FOLLOW_exprMember_in_exprFactor6520);
                        exprMember56 = exprMember();
                        state._fsp--;

                        stream_exprMember.add(exprMember56.getTree());
                        // AST REWRITE
                        // elements: exprMember
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 715:7: -> ^( DATASET_PLUS_UNARY_TAG exprMember )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:716:9: ^( DATASET_PLUS_UNARY_TAG exprMember )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_PLUS_UNARY_TAG, "DATASET_PLUS_UNARY_TAG"), root_1);
                                adaptor.addChild(root_1, stream_exprMember.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 3:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:717:5: ( MINUS exprMember -> ^( DATASET_MINUS_UNARY_TAG exprMember ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:717:5: ( MINUS exprMember -> ^( DATASET_MINUS_UNARY_TAG exprMember ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:717:6: MINUS exprMember
                    {
                        MINUS57 = (Token) match(input, MINUS, FOLLOW_MINUS_in_exprFactor6550);
                        stream_MINUS.add(MINUS57);

                        pushFollow(FOLLOW_exprMember_in_exprFactor6552);
                        exprMember58 = exprMember();
                        state._fsp--;

                        stream_exprMember.add(exprMember58.getTree());
                        // AST REWRITE
                        // elements: exprMember
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 718:7: -> ^( DATASET_MINUS_UNARY_TAG exprMember )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:719:9: ^( DATASET_MINUS_UNARY_TAG exprMember )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_MINUS_UNARY_TAG, "DATASET_MINUS_UNARY_TAG"), root_1);
                                adaptor.addChild(root_1, stream_exprMember.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 4:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:720:5: NOT exprMember
                {
                    root_0 = (Object) adaptor.nil();


                    NOT59 = (Token) match(input, NOT, FOLLOW_NOT_in_exprFactor6581);
                    NOT59_tree = (Object) adaptor.create(NOT59);
                    adaptor.addChild(root_0, NOT59_tree);

                    pushFollow(FOLLOW_exprMember_in_exprFactor6583);
                    exprMember60 = exprMember();
                    state._fsp--;

                    adaptor.addChild(root_0, exprMember60.getTree());

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "exprMember"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:725:1: exprMember : exprAtom ( '[' datasetClause ']' )? ( '#' componentID )? ;
    public final ValidationMlParser.exprMember_return exprMember() throws Exception {
        ValidationMlParser.exprMember_return retval = new ValidationMlParser.exprMember_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal62 = null;
        Token char_literal64 = null;
        Token char_literal65 = null;
        ParserRuleReturnScope exprAtom61 = null;
        ParserRuleReturnScope datasetClause63 = null;
        ParserRuleReturnScope componentID66 = null;

        Object char_literal62_tree = null;
        Object char_literal64_tree = null;
        Object char_literal65_tree = null;

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:726:3: ( exprAtom ( '[' datasetClause ']' )? ( '#' componentID )? )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:727:3: exprAtom ( '[' datasetClause ']' )? ( '#' componentID )?
            {
                root_0 = (Object) adaptor.nil();


                pushFollow(FOLLOW_exprAtom_in_exprMember6601);
                exprAtom61 = exprAtom();
                state._fsp--;

                adaptor.addChild(root_0, exprAtom61.getTree());

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:727:12: ( '[' datasetClause ']' )?
                int alt17 = 2;
                int LA17_0 = input.LA(1);
                if ((LA17_0 == 432)) {
                    alt17 = 1;
                }
                switch (alt17) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:727:13: '[' datasetClause ']'
                    {
                        char_literal62 = (Token) match(input, 432, FOLLOW_432_in_exprMember6604);
                        char_literal62_tree = (Object) adaptor.create(char_literal62);
                        adaptor.addChild(root_0, char_literal62_tree);

                        pushFollow(FOLLOW_datasetClause_in_exprMember6606);
                        datasetClause63 = datasetClause();
                        state._fsp--;

                        adaptor.addChild(root_0, datasetClause63.getTree());

                        char_literal64 = (Token) match(input, 433, FOLLOW_433_in_exprMember6608);
                        char_literal64_tree = (Object) adaptor.create(char_literal64);
                        adaptor.addChild(root_0, char_literal64_tree);

                    }
                    break;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:727:36: ( '#' componentID )?
                int alt18 = 2;
                int LA18_0 = input.LA(1);
                if ((LA18_0 == 425)) {
                    alt18 = 1;
                }
                switch (alt18) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:727:37: '#' componentID
                    {
                        char_literal65 = (Token) match(input, 425, FOLLOW_425_in_exprMember6612);
                        char_literal65_tree = (Object) adaptor.create(char_literal65);
                        adaptor.addChild(root_0, char_literal65_tree);

                        pushFollow(FOLLOW_componentID_in_exprMember6614);
                        componentID66 = componentID();
                        state._fsp--;

                        adaptor.addChild(root_0, componentID66.getTree());

                    }
                    break;

                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "exprAtom"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:732:1: exprAtom : ( ROUND '(' b1= expr ',' b4= INTEGER_CONSTANT ')' -> ^( DATASET_ROUND_TAG $b1 $b4) | ( MIN '(' b1= expr ')' -> ^( DATASET_MIN_TAG $b1) ) | ( MAX '(' b1= expr ')' -> ^( DATASET_MAX_TAG $b1) ) | ( ABS '(' b1= expr ')' -> ^( DATASET_ABS_TAG $b1) ) | ( EXP '(' b1= expr ')' -> ^( DATASET_EXP_TAG $b1) ) | ( LN '(' b1= expr ')' -> ^( DATASET_LN_TAG $b1) ) | ( LOG '(' b1= expr ',' b3= logBase ')' -> ^( DATASET_LOG_TAG $b1 $b3) ) | ( TRUNC '(' b1= expr ',' b4= INTEGER_CONSTANT ')' -> ^( DATASET_TRUNC_TAG $b1 $b4) ) | ( POWER '(' b1= expr ',' b3= powerExponent ')' -> ^( DATASET_POWER_TAG $b1 $b3) ) | ( NROOT '(' b1= expr ',' b4= INTEGER_CONSTANT ')' -> ^( DATASET_NROOT_TAG $b1 $b4) ) | ( LEN '(' expr ')' -> ^( DATASET_LEN_TAG expr ) ) | ( TRIM '(' expr ')' -> ^( DATASET_TRIM_TAG expr ) ) | ( UCASE '(' expr ')' -> ^( DATASET_UCASE_TAG expr ) ) | ( LCASE '(' expr ')' -> ^( DATASET_LCASE_TAG expr ) ) | ( SUBSTR '(' b1= expr ',' b2= scalarExpr ( ',' b3= scalarExpr )? ')' -> ^( DATASET_SUBSTR_TAG $b1 $b2 ( $b3)? ) ) | ( INDEXOF '(' b1= expr ',' str= STRING_CONSTANT ')' -> ^( DATASET_INDEXOF_TAG $b1 $str) ) | ( MISSING '(' b1= expr ')' -> ^( DATASET_NODUPLICATES_TAG $b1) ) | ( CHARSET_MATCH '(' o1= expr ',' str= STRING_CONSTANT ( ',' ALL )? ')' -> ^( DATASET_CHARSET_TAG $o1 $str) ) | ( CODELIST_MATCH '(' o1= expr ',' s1= setExpr ( ',' ALL )? ')' -> ^( DATASET_INSET_TAG $o1 $s1) ) | ( CHARLENGTH '(' o1= expr ')' -> ^( DATASET_CHARLENGTH_TAG $o1) ) | ( TYPE '(' o1= expr ')' '=' o3= STRING_CONSTANT -> ^( DATASET_TYPE_TAG $o1 ^( STRING_CONSTANT_TAG $o3) ) ) | INTERSECT '(' o1= expr ',' o2= expr ')' -> ^( DATASET_INTERSECT_TAG $o1 $o2) | UNION '(' o1= expr ',' o2= expr ')' -> ^( DATASET_UNION_TAG $o1 $o2) | DIFF '(' o1= expr ',' o2= expr ')' -> ^( DATASET_DIFF_TAG $o1 $o2) | NOT_IN '(' o1= expr ',' o2= expr ')' -> ^( DATASET_NOT_IN_TAG $o1 $o2) | ISNULL '(' o1= expr ')' -> ^( DATASET_NOT_IN_TAG $o1) | NVL '(' b1= expr ',' c= constant ')' -> ^( DATASET_NVL_TAG $b1 $c) | ( MOD '(' b1= expr ',' b2= expr ')' -> ^( DATASET_MOD_TAG $b1 INTEGER_CONSTANT ) ) | validationExpr | getExpr | variableRef | putExpr | evalExpr | mergeExpr | hierarchyExpr );
    public final ValidationMlParser.exprAtom_return exprAtom() throws Exception {
        ValidationMlParser.exprAtom_return retval = new ValidationMlParser.exprAtom_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token b4 = null;
        Token str = null;
        Token o3 = null;
        Token ROUND67 = null;
        Token char_literal68 = null;
        Token char_literal69 = null;
        Token char_literal70 = null;
        Token MIN71 = null;
        Token char_literal72 = null;
        Token char_literal73 = null;
        Token MAX74 = null;
        Token char_literal75 = null;
        Token char_literal76 = null;
        Token ABS77 = null;
        Token char_literal78 = null;
        Token char_literal79 = null;
        Token EXP80 = null;
        Token char_literal81 = null;
        Token char_literal82 = null;
        Token LN83 = null;
        Token char_literal84 = null;
        Token char_literal85 = null;
        Token LOG86 = null;
        Token char_literal87 = null;
        Token char_literal88 = null;
        Token char_literal89 = null;
        Token TRUNC90 = null;
        Token char_literal91 = null;
        Token char_literal92 = null;
        Token char_literal93 = null;
        Token POWER94 = null;
        Token char_literal95 = null;
        Token char_literal96 = null;
        Token char_literal97 = null;
        Token NROOT98 = null;
        Token char_literal99 = null;
        Token char_literal100 = null;
        Token char_literal101 = null;
        Token LEN102 = null;
        Token char_literal103 = null;
        Token char_literal105 = null;
        Token TRIM106 = null;
        Token char_literal107 = null;
        Token char_literal109 = null;
        Token UCASE110 = null;
        Token char_literal111 = null;
        Token char_literal113 = null;
        Token LCASE114 = null;
        Token char_literal115 = null;
        Token char_literal117 = null;
        Token SUBSTR118 = null;
        Token char_literal119 = null;
        Token char_literal120 = null;
        Token char_literal121 = null;
        Token char_literal122 = null;
        Token INDEXOF123 = null;
        Token char_literal124 = null;
        Token char_literal125 = null;
        Token char_literal126 = null;
        Token MISSING127 = null;
        Token char_literal128 = null;
        Token char_literal129 = null;
        Token CHARSET_MATCH130 = null;
        Token char_literal131 = null;
        Token char_literal132 = null;
        Token char_literal133 = null;
        Token ALL134 = null;
        Token char_literal135 = null;
        Token CODELIST_MATCH136 = null;
        Token char_literal137 = null;
        Token char_literal138 = null;
        Token char_literal139 = null;
        Token ALL140 = null;
        Token char_literal141 = null;
        Token CHARLENGTH142 = null;
        Token char_literal143 = null;
        Token char_literal144 = null;
        Token TYPE145 = null;
        Token char_literal146 = null;
        Token char_literal147 = null;
        Token char_literal148 = null;
        Token INTERSECT149 = null;
        Token char_literal150 = null;
        Token char_literal151 = null;
        Token char_literal152 = null;
        Token UNION153 = null;
        Token char_literal154 = null;
        Token char_literal155 = null;
        Token char_literal156 = null;
        Token DIFF157 = null;
        Token char_literal158 = null;
        Token char_literal159 = null;
        Token char_literal160 = null;
        Token NOT_IN161 = null;
        Token char_literal162 = null;
        Token char_literal163 = null;
        Token char_literal164 = null;
        Token ISNULL165 = null;
        Token char_literal166 = null;
        Token char_literal167 = null;
        Token NVL168 = null;
        Token char_literal169 = null;
        Token char_literal170 = null;
        Token char_literal171 = null;
        Token MOD172 = null;
        Token char_literal173 = null;
        Token char_literal174 = null;
        Token char_literal175 = null;
        ParserRuleReturnScope b1 = null;
        ParserRuleReturnScope b3 = null;
        ParserRuleReturnScope b2 = null;
        ParserRuleReturnScope o1 = null;
        ParserRuleReturnScope s1 = null;
        ParserRuleReturnScope o2 = null;
        ParserRuleReturnScope c = null;
        ParserRuleReturnScope expr104 = null;
        ParserRuleReturnScope expr108 = null;
        ParserRuleReturnScope expr112 = null;
        ParserRuleReturnScope expr116 = null;
        ParserRuleReturnScope validationExpr176 = null;
        ParserRuleReturnScope getExpr177 = null;
        ParserRuleReturnScope variableRef178 = null;
        ParserRuleReturnScope putExpr179 = null;
        ParserRuleReturnScope evalExpr180 = null;
        ParserRuleReturnScope mergeExpr181 = null;
        ParserRuleReturnScope hierarchyExpr182 = null;

        Object b4_tree = null;
        Object str_tree = null;
        Object o3_tree = null;
        Object ROUND67_tree = null;
        Object char_literal68_tree = null;
        Object char_literal69_tree = null;
        Object char_literal70_tree = null;
        Object MIN71_tree = null;
        Object char_literal72_tree = null;
        Object char_literal73_tree = null;
        Object MAX74_tree = null;
        Object char_literal75_tree = null;
        Object char_literal76_tree = null;
        Object ABS77_tree = null;
        Object char_literal78_tree = null;
        Object char_literal79_tree = null;
        Object EXP80_tree = null;
        Object char_literal81_tree = null;
        Object char_literal82_tree = null;
        Object LN83_tree = null;
        Object char_literal84_tree = null;
        Object char_literal85_tree = null;
        Object LOG86_tree = null;
        Object char_literal87_tree = null;
        Object char_literal88_tree = null;
        Object char_literal89_tree = null;
        Object TRUNC90_tree = null;
        Object char_literal91_tree = null;
        Object char_literal92_tree = null;
        Object char_literal93_tree = null;
        Object POWER94_tree = null;
        Object char_literal95_tree = null;
        Object char_literal96_tree = null;
        Object char_literal97_tree = null;
        Object NROOT98_tree = null;
        Object char_literal99_tree = null;
        Object char_literal100_tree = null;
        Object char_literal101_tree = null;
        Object LEN102_tree = null;
        Object char_literal103_tree = null;
        Object char_literal105_tree = null;
        Object TRIM106_tree = null;
        Object char_literal107_tree = null;
        Object char_literal109_tree = null;
        Object UCASE110_tree = null;
        Object char_literal111_tree = null;
        Object char_literal113_tree = null;
        Object LCASE114_tree = null;
        Object char_literal115_tree = null;
        Object char_literal117_tree = null;
        Object SUBSTR118_tree = null;
        Object char_literal119_tree = null;
        Object char_literal120_tree = null;
        Object char_literal121_tree = null;
        Object char_literal122_tree = null;
        Object INDEXOF123_tree = null;
        Object char_literal124_tree = null;
        Object char_literal125_tree = null;
        Object char_literal126_tree = null;
        Object MISSING127_tree = null;
        Object char_literal128_tree = null;
        Object char_literal129_tree = null;
        Object CHARSET_MATCH130_tree = null;
        Object char_literal131_tree = null;
        Object char_literal132_tree = null;
        Object char_literal133_tree = null;
        Object ALL134_tree = null;
        Object char_literal135_tree = null;
        Object CODELIST_MATCH136_tree = null;
        Object char_literal137_tree = null;
        Object char_literal138_tree = null;
        Object char_literal139_tree = null;
        Object ALL140_tree = null;
        Object char_literal141_tree = null;
        Object CHARLENGTH142_tree = null;
        Object char_literal143_tree = null;
        Object char_literal144_tree = null;
        Object TYPE145_tree = null;
        Object char_literal146_tree = null;
        Object char_literal147_tree = null;
        Object char_literal148_tree = null;
        Object INTERSECT149_tree = null;
        Object char_literal150_tree = null;
        Object char_literal151_tree = null;
        Object char_literal152_tree = null;
        Object UNION153_tree = null;
        Object char_literal154_tree = null;
        Object char_literal155_tree = null;
        Object char_literal156_tree = null;
        Object DIFF157_tree = null;
        Object char_literal158_tree = null;
        Object char_literal159_tree = null;
        Object char_literal160_tree = null;
        Object NOT_IN161_tree = null;
        Object char_literal162_tree = null;
        Object char_literal163_tree = null;
        Object char_literal164_tree = null;
        Object ISNULL165_tree = null;
        Object char_literal166_tree = null;
        Object char_literal167_tree = null;
        Object NVL168_tree = null;
        Object char_literal169_tree = null;
        Object char_literal170_tree = null;
        Object char_literal171_tree = null;
        Object MOD172_tree = null;
        Object char_literal173_tree = null;
        Object char_literal174_tree = null;
        Object char_literal175_tree = null;
        RewriteRuleTokenStream stream_NROOT = new RewriteRuleTokenStream(adaptor, "token NROOT");
        RewriteRuleTokenStream stream_ALL = new RewriteRuleTokenStream(adaptor, "token ALL");
        RewriteRuleTokenStream stream_LN = new RewriteRuleTokenStream(adaptor, "token LN");
        RewriteRuleTokenStream stream_MAX = new RewriteRuleTokenStream(adaptor, "token MAX");
        RewriteRuleTokenStream stream_TRIM = new RewriteRuleTokenStream(adaptor, "token TRIM");
        RewriteRuleTokenStream stream_NVL = new RewriteRuleTokenStream(adaptor, "token NVL");
        RewriteRuleTokenStream stream_ROUND = new RewriteRuleTokenStream(adaptor, "token ROUND");
        RewriteRuleTokenStream stream_UCASE = new RewriteRuleTokenStream(adaptor, "token UCASE");
        RewriteRuleTokenStream stream_TRUNC = new RewriteRuleTokenStream(adaptor, "token TRUNC");
        RewriteRuleTokenStream stream_MISSING = new RewriteRuleTokenStream(adaptor, "token MISSING");
        RewriteRuleTokenStream stream_INTEGER_CONSTANT = new RewriteRuleTokenStream(adaptor, "token INTEGER_CONSTANT");
        RewriteRuleTokenStream stream_ABS = new RewriteRuleTokenStream(adaptor, "token ABS");
        RewriteRuleTokenStream stream_CODELIST_MATCH = new RewriteRuleTokenStream(adaptor, "token CODELIST_MATCH");
        RewriteRuleTokenStream stream_INDEXOF = new RewriteRuleTokenStream(adaptor, "token INDEXOF");
        RewriteRuleTokenStream stream_CARTESIAN_PER = new RewriteRuleTokenStream(adaptor, "token CARTESIAN_PER");
        RewriteRuleTokenStream stream_STRING_CONSTANT = new RewriteRuleTokenStream(adaptor, "token STRING_CONSTANT");
        RewriteRuleTokenStream stream_TYPE = new RewriteRuleTokenStream(adaptor, "token TYPE");
        RewriteRuleTokenStream stream_SUBSTR = new RewriteRuleTokenStream(adaptor, "token SUBSTR");
        RewriteRuleTokenStream stream_MOD = new RewriteRuleTokenStream(adaptor, "token MOD");
        RewriteRuleTokenStream stream_LOG = new RewriteRuleTokenStream(adaptor, "token LOG");
        RewriteRuleTokenStream stream_DIFF = new RewriteRuleTokenStream(adaptor, "token DIFF");
        RewriteRuleTokenStream stream_POWER = new RewriteRuleTokenStream(adaptor, "token POWER");
        RewriteRuleTokenStream stream_NOT_IN = new RewriteRuleTokenStream(adaptor, "token NOT_IN");
        RewriteRuleTokenStream stream_EQ = new RewriteRuleTokenStream(adaptor, "token EQ");
        RewriteRuleTokenStream stream_INTERSECT = new RewriteRuleTokenStream(adaptor, "token INTERSECT");
        RewriteRuleTokenStream stream_CHARSET_MATCH = new RewriteRuleTokenStream(adaptor, "token CHARSET_MATCH");
        RewriteRuleTokenStream stream_LCASE = new RewriteRuleTokenStream(adaptor, "token LCASE");
        RewriteRuleTokenStream stream_MIN = new RewriteRuleTokenStream(adaptor, "token MIN");
        RewriteRuleTokenStream stream_LEN = new RewriteRuleTokenStream(adaptor, "token LEN");
        RewriteRuleTokenStream stream_426 = new RewriteRuleTokenStream(adaptor, "token 426");
        RewriteRuleTokenStream stream_CHARLENGTH = new RewriteRuleTokenStream(adaptor, "token CHARLENGTH");
        RewriteRuleTokenStream stream_427 = new RewriteRuleTokenStream(adaptor, "token 427");
        RewriteRuleTokenStream stream_ISNULL = new RewriteRuleTokenStream(adaptor, "token ISNULL");
        RewriteRuleTokenStream stream_EXP = new RewriteRuleTokenStream(adaptor, "token EXP");
        RewriteRuleTokenStream stream_UNION = new RewriteRuleTokenStream(adaptor, "token UNION");
        RewriteRuleSubtreeStream stream_logBase = new RewriteRuleSubtreeStream(adaptor, "rule logBase");
        RewriteRuleSubtreeStream stream_powerExponent = new RewriteRuleSubtreeStream(adaptor, "rule powerExponent");
        RewriteRuleSubtreeStream stream_constant = new RewriteRuleSubtreeStream(adaptor, "rule constant");
        RewriteRuleSubtreeStream stream_scalarExpr = new RewriteRuleSubtreeStream(adaptor, "rule scalarExpr");
        RewriteRuleSubtreeStream stream_expr = new RewriteRuleSubtreeStream(adaptor, "rule expr");
        RewriteRuleSubtreeStream stream_setExpr = new RewriteRuleSubtreeStream(adaptor, "rule setExpr");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:733:3: ( ROUND '(' b1= expr ',' b4= INTEGER_CONSTANT ')' -> ^( DATASET_ROUND_TAG $b1 $b4) | ( MIN '(' b1= expr ')' -> ^( DATASET_MIN_TAG $b1) ) | ( MAX '(' b1= expr ')' -> ^( DATASET_MAX_TAG $b1) ) | ( ABS '(' b1= expr ')' -> ^( DATASET_ABS_TAG $b1) ) | ( EXP '(' b1= expr ')' -> ^( DATASET_EXP_TAG $b1) ) | ( LN '(' b1= expr ')' -> ^( DATASET_LN_TAG $b1) ) | ( LOG '(' b1= expr ',' b3= logBase ')' -> ^( DATASET_LOG_TAG $b1 $b3) ) | ( TRUNC '(' b1= expr ',' b4= INTEGER_CONSTANT ')' -> ^( DATASET_TRUNC_TAG $b1 $b4) ) | ( POWER '(' b1= expr ',' b3= powerExponent ')' -> ^( DATASET_POWER_TAG $b1 $b3) ) | ( NROOT '(' b1= expr ',' b4= INTEGER_CONSTANT ')' -> ^( DATASET_NROOT_TAG $b1 $b4) ) | ( LEN '(' expr ')' -> ^( DATASET_LEN_TAG expr ) ) | ( TRIM '(' expr ')' -> ^( DATASET_TRIM_TAG expr ) ) | ( UCASE '(' expr ')' -> ^( DATASET_UCASE_TAG expr ) ) | ( LCASE '(' expr ')' -> ^( DATASET_LCASE_TAG expr ) ) | ( SUBSTR '(' b1= expr ',' b2= scalarExpr ( ',' b3= scalarExpr )? ')' -> ^( DATASET_SUBSTR_TAG $b1 $b2 ( $b3)? ) ) | ( INDEXOF '(' b1= expr ',' str= STRING_CONSTANT ')' -> ^( DATASET_INDEXOF_TAG $b1 $str) ) | ( MISSING '(' b1= expr ')' -> ^( DATASET_NODUPLICATES_TAG $b1) ) | ( CHARSET_MATCH '(' o1= expr ',' str= STRING_CONSTANT ( ',' ALL )? ')' -> ^( DATASET_CHARSET_TAG $o1 $str) ) | ( CODELIST_MATCH '(' o1= expr ',' s1= setExpr ( ',' ALL )? ')' -> ^( DATASET_INSET_TAG $o1 $s1) ) | ( CHARLENGTH '(' o1= expr ')' -> ^( DATASET_CHARLENGTH_TAG $o1) ) | ( TYPE '(' o1= expr ')' '=' o3= STRING_CONSTANT -> ^( DATASET_TYPE_TAG $o1 ^( STRING_CONSTANT_TAG $o3) ) ) | INTERSECT '(' o1= expr ',' o2= expr ')' -> ^( DATASET_INTERSECT_TAG $o1 $o2) | UNION '(' o1= expr ',' o2= expr ')' -> ^( DATASET_UNION_TAG $o1 $o2) | DIFF '(' o1= expr ',' o2= expr ')' -> ^( DATASET_DIFF_TAG $o1 $o2) | NOT_IN '(' o1= expr ',' o2= expr ')' -> ^( DATASET_NOT_IN_TAG $o1 $o2) | ISNULL '(' o1= expr ')' -> ^( DATASET_NOT_IN_TAG $o1) | NVL '(' b1= expr ',' c= constant ')' -> ^( DATASET_NVL_TAG $b1 $c) | ( MOD '(' b1= expr ',' b2= expr ')' -> ^( DATASET_MOD_TAG $b1 INTEGER_CONSTANT ) ) | validationExpr | getExpr | variableRef | putExpr | evalExpr | mergeExpr | hierarchyExpr )
            int alt22 = 35;
            switch (input.LA(1)) {
                case ROUND: {
                    alt22 = 1;
                }
                break;
                case MIN: {
                    alt22 = 2;
                }
                break;
                case MAX: {
                    alt22 = 3;
                }
                break;
                case ABS: {
                    alt22 = 4;
                }
                break;
                case EXP: {
                    alt22 = 5;
                }
                break;
                case LN: {
                    alt22 = 6;
                }
                break;
                case LOG: {
                    alt22 = 7;
                }
                break;
                case TRUNC: {
                    alt22 = 8;
                }
                break;
                case POWER: {
                    alt22 = 9;
                }
                break;
                case NROOT: {
                    alt22 = 10;
                }
                break;
                case LEN: {
                    alt22 = 11;
                }
                break;
                case TRIM: {
                    alt22 = 12;
                }
                break;
                case UCASE: {
                    alt22 = 13;
                }
                break;
                case LCASE: {
                    alt22 = 14;
                }
                break;
                case SUBSTR: {
                    alt22 = 15;
                }
                break;
                case INDEXOF: {
                    alt22 = 16;
                }
                break;
                case MISSING: {
                    alt22 = 17;
                }
                break;
                case CHARSET_MATCH: {
                    alt22 = 18;
                }
                break;
                case CODELIST_MATCH: {
                    alt22 = 19;
                }
                break;
                case CHARLENGTH: {
                    alt22 = 20;
                }
                break;
                case TYPE: {
                    alt22 = 21;
                }
                break;
                case INTERSECT: {
                    alt22 = 22;
                }
                break;
                case UNION: {
                    alt22 = 23;
                }
                break;
                case DIFF: {
                    alt22 = 24;
                }
                break;
                case NOT_IN: {
                    alt22 = 25;
                }
                break;
                case ISNULL: {
                    alt22 = 26;
                }
                break;
                case NVL: {
                    alt22 = 27;
                }
                break;
                case MOD: {
                    alt22 = 28;
                }
                break;
                case CHECK: {
                    alt22 = 29;
                }
                break;
                case GET: {
                    alt22 = 30;
                }
                break;
                case BOOLEAN_CONSTANT:
                case FLOAT_CONSTANT:
                case IDENTIFIER:
                case INTEGER_CONSTANT:
                case NULL_CONSTANT:
                case STRING_CONSTANT:
                case 426: {
                    alt22 = 31;
                }
                break;
                case PUT: {
                    alt22 = 32;
                }
                break;
                case EVAL: {
                    alt22 = 33;
                }
                break;
                case MERGE: {
                    alt22 = 34;
                }
                break;
                case HIERARCHY: {
                    alt22 = 35;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 22, 0, input);
                    throw nvae;
            }
            switch (alt22) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:734:3: ROUND '(' b1= expr ',' b4= INTEGER_CONSTANT ')'
                {
                    ROUND67 = (Token) match(input, ROUND, FOLLOW_ROUND_in_exprAtom6635);
                    stream_ROUND.add(ROUND67);

                    char_literal68 = (Token) match(input, 426, FOLLOW_426_in_exprAtom6637);
                    stream_426.add(char_literal68);

                    pushFollow(FOLLOW_expr_in_exprAtom6641);
                    b1 = expr();
                    state._fsp--;

                    stream_expr.add(b1.getTree());
                    char_literal69 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom6643);
                    stream_CARTESIAN_PER.add(char_literal69);

                    b4 = (Token) match(input, INTEGER_CONSTANT, FOLLOW_INTEGER_CONSTANT_in_exprAtom6647);
                    stream_INTEGER_CONSTANT.add(b4);

                    char_literal70 = (Token) match(input, 427, FOLLOW_427_in_exprAtom6649);
                    stream_427.add(char_literal70);

                    // AST REWRITE
                    // elements: b4, b1
                    // token labels: b4
                    // rule labels: retval, b1
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_b4 = new RewriteRuleTokenStream(adaptor, "token b4", b4);
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                    RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 735:5: -> ^( DATASET_ROUND_TAG $b1 $b4)
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:736:7: ^( DATASET_ROUND_TAG $b1 $b4)
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_ROUND_TAG, "DATASET_ROUND_TAG"), root_1);
                            adaptor.addChild(root_1, stream_b1.nextTree());
                            adaptor.addChild(root_1, stream_b4.nextNode());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:737:5: ( MIN '(' b1= expr ')' -> ^( DATASET_MIN_TAG $b1) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:737:5: ( MIN '(' b1= expr ')' -> ^( DATASET_MIN_TAG $b1) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:737:6: MIN '(' b1= expr ')'
                    {
                        MIN71 = (Token) match(input, MIN, FOLLOW_MIN_in_exprAtom6678);
                        stream_MIN.add(MIN71);

                        char_literal72 = (Token) match(input, 426, FOLLOW_426_in_exprAtom6680);
                        stream_426.add(char_literal72);

                        pushFollow(FOLLOW_expr_in_exprAtom6684);
                        b1 = expr();
                        state._fsp--;

                        stream_expr.add(b1.getTree());
                        char_literal73 = (Token) match(input, 427, FOLLOW_427_in_exprAtom6686);
                        stream_427.add(char_literal73);

                        // AST REWRITE
                        // elements: b1
                        // token labels:
                        // rule labels: retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 738:7: -> ^( DATASET_MIN_TAG $b1)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:739:9: ^( DATASET_MIN_TAG $b1)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_MIN_TAG, "DATASET_MIN_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 3:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:740:5: ( MAX '(' b1= expr ')' -> ^( DATASET_MAX_TAG $b1) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:740:5: ( MAX '(' b1= expr ')' -> ^( DATASET_MAX_TAG $b1) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:740:6: MAX '(' b1= expr ')'
                    {
                        MAX74 = (Token) match(input, MAX, FOLLOW_MAX_in_exprAtom6717);
                        stream_MAX.add(MAX74);

                        char_literal75 = (Token) match(input, 426, FOLLOW_426_in_exprAtom6719);
                        stream_426.add(char_literal75);

                        pushFollow(FOLLOW_expr_in_exprAtom6723);
                        b1 = expr();
                        state._fsp--;

                        stream_expr.add(b1.getTree());
                        char_literal76 = (Token) match(input, 427, FOLLOW_427_in_exprAtom6725);
                        stream_427.add(char_literal76);

                        // AST REWRITE
                        // elements: b1
                        // token labels:
                        // rule labels: retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 741:7: -> ^( DATASET_MAX_TAG $b1)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:742:9: ^( DATASET_MAX_TAG $b1)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_MAX_TAG, "DATASET_MAX_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 4:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:743:5: ( ABS '(' b1= expr ')' -> ^( DATASET_ABS_TAG $b1) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:743:5: ( ABS '(' b1= expr ')' -> ^( DATASET_ABS_TAG $b1) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:743:6: ABS '(' b1= expr ')'
                    {
                        ABS77 = (Token) match(input, ABS, FOLLOW_ABS_in_exprAtom6756);
                        stream_ABS.add(ABS77);

                        char_literal78 = (Token) match(input, 426, FOLLOW_426_in_exprAtom6758);
                        stream_426.add(char_literal78);

                        pushFollow(FOLLOW_expr_in_exprAtom6762);
                        b1 = expr();
                        state._fsp--;

                        stream_expr.add(b1.getTree());
                        char_literal79 = (Token) match(input, 427, FOLLOW_427_in_exprAtom6764);
                        stream_427.add(char_literal79);

                        // AST REWRITE
                        // elements: b1
                        // token labels:
                        // rule labels: retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 744:7: -> ^( DATASET_ABS_TAG $b1)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:745:9: ^( DATASET_ABS_TAG $b1)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_ABS_TAG, "DATASET_ABS_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 5:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:746:5: ( EXP '(' b1= expr ')' -> ^( DATASET_EXP_TAG $b1) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:746:5: ( EXP '(' b1= expr ')' -> ^( DATASET_EXP_TAG $b1) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:746:6: EXP '(' b1= expr ')'
                    {
                        EXP80 = (Token) match(input, EXP, FOLLOW_EXP_in_exprAtom6795);
                        stream_EXP.add(EXP80);

                        char_literal81 = (Token) match(input, 426, FOLLOW_426_in_exprAtom6797);
                        stream_426.add(char_literal81);

                        pushFollow(FOLLOW_expr_in_exprAtom6801);
                        b1 = expr();
                        state._fsp--;

                        stream_expr.add(b1.getTree());
                        char_literal82 = (Token) match(input, 427, FOLLOW_427_in_exprAtom6803);
                        stream_427.add(char_literal82);

                        // AST REWRITE
                        // elements: b1
                        // token labels:
                        // rule labels: retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 747:7: -> ^( DATASET_EXP_TAG $b1)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:748:9: ^( DATASET_EXP_TAG $b1)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_EXP_TAG, "DATASET_EXP_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 6:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:749:5: ( LN '(' b1= expr ')' -> ^( DATASET_LN_TAG $b1) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:749:5: ( LN '(' b1= expr ')' -> ^( DATASET_LN_TAG $b1) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:749:6: LN '(' b1= expr ')'
                    {
                        LN83 = (Token) match(input, LN, FOLLOW_LN_in_exprAtom6834);
                        stream_LN.add(LN83);

                        char_literal84 = (Token) match(input, 426, FOLLOW_426_in_exprAtom6836);
                        stream_426.add(char_literal84);

                        pushFollow(FOLLOW_expr_in_exprAtom6840);
                        b1 = expr();
                        state._fsp--;

                        stream_expr.add(b1.getTree());
                        char_literal85 = (Token) match(input, 427, FOLLOW_427_in_exprAtom6842);
                        stream_427.add(char_literal85);

                        // AST REWRITE
                        // elements: b1
                        // token labels:
                        // rule labels: retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 750:7: -> ^( DATASET_LN_TAG $b1)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:751:9: ^( DATASET_LN_TAG $b1)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_LN_TAG, "DATASET_LN_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 7:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:752:5: ( LOG '(' b1= expr ',' b3= logBase ')' -> ^( DATASET_LOG_TAG $b1 $b3) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:752:5: ( LOG '(' b1= expr ',' b3= logBase ')' -> ^( DATASET_LOG_TAG $b1 $b3) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:752:6: LOG '(' b1= expr ',' b3= logBase ')'
                    {
                        LOG86 = (Token) match(input, LOG, FOLLOW_LOG_in_exprAtom6873);
                        stream_LOG.add(LOG86);

                        char_literal87 = (Token) match(input, 426, FOLLOW_426_in_exprAtom6875);
                        stream_426.add(char_literal87);

                        pushFollow(FOLLOW_expr_in_exprAtom6879);
                        b1 = expr();
                        state._fsp--;

                        stream_expr.add(b1.getTree());
                        char_literal88 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom6881);
                        stream_CARTESIAN_PER.add(char_literal88);

                        pushFollow(FOLLOW_logBase_in_exprAtom6885);
                        b3 = logBase();
                        state._fsp--;

                        stream_logBase.add(b3.getTree());
                        char_literal89 = (Token) match(input, 427, FOLLOW_427_in_exprAtom6887);
                        stream_427.add(char_literal89);

                        // AST REWRITE
                        // elements: b3, b1
                        // token labels:
                        // rule labels: b3, retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_b3 = new RewriteRuleSubtreeStream(adaptor, "rule b3", b3 != null ? b3.getTree() : null);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 753:7: -> ^( DATASET_LOG_TAG $b1 $b3)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:754:9: ^( DATASET_LOG_TAG $b1 $b3)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_LOG_TAG, "DATASET_LOG_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                adaptor.addChild(root_1, stream_b3.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 8:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:755:5: ( TRUNC '(' b1= expr ',' b4= INTEGER_CONSTANT ')' -> ^( DATASET_TRUNC_TAG $b1 $b4) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:755:5: ( TRUNC '(' b1= expr ',' b4= INTEGER_CONSTANT ')' -> ^( DATASET_TRUNC_TAG $b1 $b4) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:755:6: TRUNC '(' b1= expr ',' b4= INTEGER_CONSTANT ')'
                    {
                        TRUNC90 = (Token) match(input, TRUNC, FOLLOW_TRUNC_in_exprAtom6921);
                        stream_TRUNC.add(TRUNC90);

                        char_literal91 = (Token) match(input, 426, FOLLOW_426_in_exprAtom6923);
                        stream_426.add(char_literal91);

                        pushFollow(FOLLOW_expr_in_exprAtom6927);
                        b1 = expr();
                        state._fsp--;

                        stream_expr.add(b1.getTree());
                        char_literal92 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom6929);
                        stream_CARTESIAN_PER.add(char_literal92);

                        b4 = (Token) match(input, INTEGER_CONSTANT, FOLLOW_INTEGER_CONSTANT_in_exprAtom6933);
                        stream_INTEGER_CONSTANT.add(b4);

                        char_literal93 = (Token) match(input, 427, FOLLOW_427_in_exprAtom6935);
                        stream_427.add(char_literal93);

                        // AST REWRITE
                        // elements: b4, b1
                        // token labels: b4
                        // rule labels: retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleTokenStream stream_b4 = new RewriteRuleTokenStream(adaptor, "token b4", b4);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 756:7: -> ^( DATASET_TRUNC_TAG $b1 $b4)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:757:9: ^( DATASET_TRUNC_TAG $b1 $b4)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_TRUNC_TAG, "DATASET_TRUNC_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                adaptor.addChild(root_1, stream_b4.nextNode());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 9:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:758:5: ( POWER '(' b1= expr ',' b3= powerExponent ')' -> ^( DATASET_POWER_TAG $b1 $b3) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:758:5: ( POWER '(' b1= expr ',' b3= powerExponent ')' -> ^( DATASET_POWER_TAG $b1 $b3) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:758:6: POWER '(' b1= expr ',' b3= powerExponent ')'
                    {
                        POWER94 = (Token) match(input, POWER, FOLLOW_POWER_in_exprAtom6969);
                        stream_POWER.add(POWER94);

                        char_literal95 = (Token) match(input, 426, FOLLOW_426_in_exprAtom6971);
                        stream_426.add(char_literal95);

                        pushFollow(FOLLOW_expr_in_exprAtom6975);
                        b1 = expr();
                        state._fsp--;

                        stream_expr.add(b1.getTree());
                        char_literal96 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom6977);
                        stream_CARTESIAN_PER.add(char_literal96);

                        pushFollow(FOLLOW_powerExponent_in_exprAtom6981);
                        b3 = powerExponent();
                        state._fsp--;

                        stream_powerExponent.add(b3.getTree());
                        char_literal97 = (Token) match(input, 427, FOLLOW_427_in_exprAtom6983);
                        stream_427.add(char_literal97);

                        // AST REWRITE
                        // elements: b3, b1
                        // token labels:
                        // rule labels: b3, retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_b3 = new RewriteRuleSubtreeStream(adaptor, "rule b3", b3 != null ? b3.getTree() : null);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 759:7: -> ^( DATASET_POWER_TAG $b1 $b3)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:760:9: ^( DATASET_POWER_TAG $b1 $b3)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_POWER_TAG, "DATASET_POWER_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                adaptor.addChild(root_1, stream_b3.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 10:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:761:5: ( NROOT '(' b1= expr ',' b4= INTEGER_CONSTANT ')' -> ^( DATASET_NROOT_TAG $b1 $b4) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:761:5: ( NROOT '(' b1= expr ',' b4= INTEGER_CONSTANT ')' -> ^( DATASET_NROOT_TAG $b1 $b4) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:761:6: NROOT '(' b1= expr ',' b4= INTEGER_CONSTANT ')'
                    {
                        NROOT98 = (Token) match(input, NROOT, FOLLOW_NROOT_in_exprAtom7017);
                        stream_NROOT.add(NROOT98);

                        char_literal99 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7019);
                        stream_426.add(char_literal99);

                        pushFollow(FOLLOW_expr_in_exprAtom7023);
                        b1 = expr();
                        state._fsp--;

                        stream_expr.add(b1.getTree());
                        char_literal100 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom7025);
                        stream_CARTESIAN_PER.add(char_literal100);

                        b4 = (Token) match(input, INTEGER_CONSTANT, FOLLOW_INTEGER_CONSTANT_in_exprAtom7029);
                        stream_INTEGER_CONSTANT.add(b4);

                        char_literal101 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7031);
                        stream_427.add(char_literal101);

                        // AST REWRITE
                        // elements: b1, b4
                        // token labels: b4
                        // rule labels: retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleTokenStream stream_b4 = new RewriteRuleTokenStream(adaptor, "token b4", b4);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 762:7: -> ^( DATASET_NROOT_TAG $b1 $b4)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:763:9: ^( DATASET_NROOT_TAG $b1 $b4)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_NROOT_TAG, "DATASET_NROOT_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                adaptor.addChild(root_1, stream_b4.nextNode());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 11:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:764:5: ( LEN '(' expr ')' -> ^( DATASET_LEN_TAG expr ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:764:5: ( LEN '(' expr ')' -> ^( DATASET_LEN_TAG expr ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:764:6: LEN '(' expr ')'
                    {
                        LEN102 = (Token) match(input, LEN, FOLLOW_LEN_in_exprAtom7065);
                        stream_LEN.add(LEN102);

                        char_literal103 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7067);
                        stream_426.add(char_literal103);

                        pushFollow(FOLLOW_expr_in_exprAtom7069);
                        expr104 = expr();
                        state._fsp--;

                        stream_expr.add(expr104.getTree());
                        char_literal105 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7071);
                        stream_427.add(char_literal105);

                        // AST REWRITE
                        // elements: expr
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 765:7: -> ^( DATASET_LEN_TAG expr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:766:9: ^( DATASET_LEN_TAG expr )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_LEN_TAG, "DATASET_LEN_TAG"), root_1);
                                adaptor.addChild(root_1, stream_expr.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 12:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:767:5: ( TRIM '(' expr ')' -> ^( DATASET_TRIM_TAG expr ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:767:5: ( TRIM '(' expr ')' -> ^( DATASET_TRIM_TAG expr ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:767:6: TRIM '(' expr ')'
                    {
                        TRIM106 = (Token) match(input, TRIM, FOLLOW_TRIM_in_exprAtom7101);
                        stream_TRIM.add(TRIM106);

                        char_literal107 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7103);
                        stream_426.add(char_literal107);

                        pushFollow(FOLLOW_expr_in_exprAtom7105);
                        expr108 = expr();
                        state._fsp--;

                        stream_expr.add(expr108.getTree());
                        char_literal109 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7107);
                        stream_427.add(char_literal109);

                        // AST REWRITE
                        // elements: expr
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 768:7: -> ^( DATASET_TRIM_TAG expr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:769:9: ^( DATASET_TRIM_TAG expr )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_TRIM_TAG, "DATASET_TRIM_TAG"), root_1);
                                adaptor.addChild(root_1, stream_expr.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 13:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:770:5: ( UCASE '(' expr ')' -> ^( DATASET_UCASE_TAG expr ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:770:5: ( UCASE '(' expr ')' -> ^( DATASET_UCASE_TAG expr ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:770:6: UCASE '(' expr ')'
                    {
                        UCASE110 = (Token) match(input, UCASE, FOLLOW_UCASE_in_exprAtom7137);
                        stream_UCASE.add(UCASE110);

                        char_literal111 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7139);
                        stream_426.add(char_literal111);

                        pushFollow(FOLLOW_expr_in_exprAtom7141);
                        expr112 = expr();
                        state._fsp--;

                        stream_expr.add(expr112.getTree());
                        char_literal113 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7143);
                        stream_427.add(char_literal113);

                        // AST REWRITE
                        // elements: expr
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 771:7: -> ^( DATASET_UCASE_TAG expr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:772:9: ^( DATASET_UCASE_TAG expr )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_UCASE_TAG, "DATASET_UCASE_TAG"), root_1);
                                adaptor.addChild(root_1, stream_expr.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 14:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:773:5: ( LCASE '(' expr ')' -> ^( DATASET_LCASE_TAG expr ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:773:5: ( LCASE '(' expr ')' -> ^( DATASET_LCASE_TAG expr ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:773:6: LCASE '(' expr ')'
                    {
                        LCASE114 = (Token) match(input, LCASE, FOLLOW_LCASE_in_exprAtom7173);
                        stream_LCASE.add(LCASE114);

                        char_literal115 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7175);
                        stream_426.add(char_literal115);

                        pushFollow(FOLLOW_expr_in_exprAtom7177);
                        expr116 = expr();
                        state._fsp--;

                        stream_expr.add(expr116.getTree());
                        char_literal117 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7179);
                        stream_427.add(char_literal117);

                        // AST REWRITE
                        // elements: expr
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 774:7: -> ^( DATASET_LCASE_TAG expr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:775:9: ^( DATASET_LCASE_TAG expr )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_LCASE_TAG, "DATASET_LCASE_TAG"), root_1);
                                adaptor.addChild(root_1, stream_expr.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 15:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:777:3: ( SUBSTR '(' b1= expr ',' b2= scalarExpr ( ',' b3= scalarExpr )? ')' -> ^( DATASET_SUBSTR_TAG $b1 $b2 ( $b3)? ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:777:3: ( SUBSTR '(' b1= expr ',' b2= scalarExpr ( ',' b3= scalarExpr )? ')' -> ^( DATASET_SUBSTR_TAG $b1 $b2 ( $b3)? ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:778:5: SUBSTR '(' b1= expr ',' b2= scalarExpr ( ',' b3= scalarExpr )? ')'
                    {
                        SUBSTR118 = (Token) match(input, SUBSTR, FOLLOW_SUBSTR_in_exprAtom7216);
                        stream_SUBSTR.add(SUBSTR118);

                        char_literal119 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7218);
                        stream_426.add(char_literal119);

                        pushFollow(FOLLOW_expr_in_exprAtom7222);
                        b1 = expr();
                        state._fsp--;

                        stream_expr.add(b1.getTree());
                        char_literal120 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom7224);
                        stream_CARTESIAN_PER.add(char_literal120);

                        pushFollow(FOLLOW_scalarExpr_in_exprAtom7228);
                        b2 = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(b2.getTree());
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:778:42: ( ',' b3= scalarExpr )?
                        int alt19 = 2;
                        int LA19_0 = input.LA(1);
                        if ((LA19_0 == CARTESIAN_PER)) {
                            alt19 = 1;
                        }
                        switch (alt19) {
                            case 1:
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:778:43: ',' b3= scalarExpr
                            {
                                char_literal121 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom7231);
                                stream_CARTESIAN_PER.add(char_literal121);

                                pushFollow(FOLLOW_scalarExpr_in_exprAtom7235);
                                b3 = scalarExpr();
                                state._fsp--;

                                stream_scalarExpr.add(b3.getTree());
                            }
                            break;

                        }

                        char_literal122 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7239);
                        stream_427.add(char_literal122);

                        // AST REWRITE
                        // elements: b1, b2, b3
                        // token labels:
                        // rule labels: b2, b3, retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                        RewriteRuleSubtreeStream stream_b3 = new RewriteRuleSubtreeStream(adaptor, "rule b3", b3 != null ? b3.getTree() : null);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 779:7: -> ^( DATASET_SUBSTR_TAG $b1 $b2 ( $b3)? )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:780:9: ^( DATASET_SUBSTR_TAG $b1 $b2 ( $b3)? )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_SUBSTR_TAG, "DATASET_SUBSTR_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                adaptor.addChild(root_1, stream_b2.nextTree());
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:780:39: ( $b3)?
                                if (stream_b3.hasNext()) {
                                    adaptor.addChild(root_1, stream_b3.nextTree());
                                }
                                stream_b3.reset();

                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 16:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:782:5: ( INDEXOF '(' b1= expr ',' str= STRING_CONSTANT ')' -> ^( DATASET_INDEXOF_TAG $b1 $str) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:782:5: ( INDEXOF '(' b1= expr ',' str= STRING_CONSTANT ')' -> ^( DATASET_INDEXOF_TAG $b1 $str) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:782:6: INDEXOF '(' b1= expr ',' str= STRING_CONSTANT ')'
                    {
                        INDEXOF123 = (Token) match(input, INDEXOF, FOLLOW_INDEXOF_in_exprAtom7280);
                        stream_INDEXOF.add(INDEXOF123);

                        char_literal124 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7282);
                        stream_426.add(char_literal124);

                        pushFollow(FOLLOW_expr_in_exprAtom7286);
                        b1 = expr();
                        state._fsp--;

                        stream_expr.add(b1.getTree());
                        char_literal125 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom7288);
                        stream_CARTESIAN_PER.add(char_literal125);

                        str = (Token) match(input, STRING_CONSTANT, FOLLOW_STRING_CONSTANT_in_exprAtom7292);
                        stream_STRING_CONSTANT.add(str);

                        char_literal126 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7294);
                        stream_427.add(char_literal126);

                        // AST REWRITE
                        // elements: b1, str
                        // token labels: str
                        // rule labels: retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleTokenStream stream_str = new RewriteRuleTokenStream(adaptor, "token str", str);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 783:7: -> ^( DATASET_INDEXOF_TAG $b1 $str)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:784:9: ^( DATASET_INDEXOF_TAG $b1 $str)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_INDEXOF_TAG, "DATASET_INDEXOF_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                adaptor.addChild(root_1, stream_str.nextNode());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 17:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:785:5: ( MISSING '(' b1= expr ')' -> ^( DATASET_NODUPLICATES_TAG $b1) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:785:5: ( MISSING '(' b1= expr ')' -> ^( DATASET_NODUPLICATES_TAG $b1) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:785:6: MISSING '(' b1= expr ')'
                    {
                        MISSING127 = (Token) match(input, MISSING, FOLLOW_MISSING_in_exprAtom7328);
                        stream_MISSING.add(MISSING127);

                        char_literal128 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7330);
                        stream_426.add(char_literal128);

                        pushFollow(FOLLOW_expr_in_exprAtom7334);
                        b1 = expr();
                        state._fsp--;

                        stream_expr.add(b1.getTree());
                        char_literal129 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7336);
                        stream_427.add(char_literal129);

                        // AST REWRITE
                        // elements: b1
                        // token labels:
                        // rule labels: retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 786:7: -> ^( DATASET_NODUPLICATES_TAG $b1)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:787:9: ^( DATASET_NODUPLICATES_TAG $b1)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_NODUPLICATES_TAG, "DATASET_NODUPLICATES_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 18:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:789:3: ( CHARSET_MATCH '(' o1= expr ',' str= STRING_CONSTANT ( ',' ALL )? ')' -> ^( DATASET_CHARSET_TAG $o1 $str) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:789:3: ( CHARSET_MATCH '(' o1= expr ',' str= STRING_CONSTANT ( ',' ALL )? ')' -> ^( DATASET_CHARSET_TAG $o1 $str) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:790:5: CHARSET_MATCH '(' o1= expr ',' str= STRING_CONSTANT ( ',' ALL )? ')'
                    {
                        CHARSET_MATCH130 = (Token) match(input, CHARSET_MATCH, FOLLOW_CHARSET_MATCH_in_exprAtom7374);
                        stream_CHARSET_MATCH.add(CHARSET_MATCH130);

                        char_literal131 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7376);
                        stream_426.add(char_literal131);

                        pushFollow(FOLLOW_expr_in_exprAtom7380);
                        o1 = expr();
                        state._fsp--;

                        stream_expr.add(o1.getTree());
                        char_literal132 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom7382);
                        stream_CARTESIAN_PER.add(char_literal132);

                        str = (Token) match(input, STRING_CONSTANT, FOLLOW_STRING_CONSTANT_in_exprAtom7386);
                        stream_STRING_CONSTANT.add(str);

                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:790:55: ( ',' ALL )?
                        int alt20 = 2;
                        int LA20_0 = input.LA(1);
                        if ((LA20_0 == CARTESIAN_PER)) {
                            alt20 = 1;
                        }
                        switch (alt20) {
                            case 1:
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:790:56: ',' ALL
                            {
                                char_literal133 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom7389);
                                stream_CARTESIAN_PER.add(char_literal133);

                                ALL134 = (Token) match(input, ALL, FOLLOW_ALL_in_exprAtom7391);
                                stream_ALL.add(ALL134);

                            }
                            break;

                        }

                        char_literal135 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7395);
                        stream_427.add(char_literal135);

                        // AST REWRITE
                        // elements: str, o1
                        // token labels: str
                        // rule labels: o1, retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleTokenStream stream_str = new RewriteRuleTokenStream(adaptor, "token str", str);
                        RewriteRuleSubtreeStream stream_o1 = new RewriteRuleSubtreeStream(adaptor, "rule o1", o1 != null ? o1.getTree() : null);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 791:7: -> ^( DATASET_CHARSET_TAG $o1 $str)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:792:9: ^( DATASET_CHARSET_TAG $o1 $str)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_CHARSET_TAG, "DATASET_CHARSET_TAG"), root_1);
                                adaptor.addChild(root_1, stream_o1.nextTree());
                                adaptor.addChild(root_1, stream_str.nextNode());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 19:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:795:3: ( CODELIST_MATCH '(' o1= expr ',' s1= setExpr ( ',' ALL )? ')' -> ^( DATASET_INSET_TAG $o1 $s1) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:795:3: ( CODELIST_MATCH '(' o1= expr ',' s1= setExpr ( ',' ALL )? ')' -> ^( DATASET_INSET_TAG $o1 $s1) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:796:5: CODELIST_MATCH '(' o1= expr ',' s1= setExpr ( ',' ALL )? ')'
                    {
                        CODELIST_MATCH136 = (Token) match(input, CODELIST_MATCH, FOLLOW_CODELIST_MATCH_in_exprAtom7439);
                        stream_CODELIST_MATCH.add(CODELIST_MATCH136);

                        char_literal137 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7441);
                        stream_426.add(char_literal137);

                        pushFollow(FOLLOW_expr_in_exprAtom7445);
                        o1 = expr();
                        state._fsp--;

                        stream_expr.add(o1.getTree());
                        char_literal138 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom7447);
                        stream_CARTESIAN_PER.add(char_literal138);

                        pushFollow(FOLLOW_setExpr_in_exprAtom7451);
                        s1 = setExpr();
                        state._fsp--;

                        stream_setExpr.add(s1.getTree());
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:796:47: ( ',' ALL )?
                        int alt21 = 2;
                        int LA21_0 = input.LA(1);
                        if ((LA21_0 == CARTESIAN_PER)) {
                            alt21 = 1;
                        }
                        switch (alt21) {
                            case 1:
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:796:48: ',' ALL
                            {
                                char_literal139 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom7454);
                                stream_CARTESIAN_PER.add(char_literal139);

                                ALL140 = (Token) match(input, ALL, FOLLOW_ALL_in_exprAtom7456);
                                stream_ALL.add(ALL140);

                            }
                            break;

                        }

                        char_literal141 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7460);
                        stream_427.add(char_literal141);

                        // AST REWRITE
                        // elements: o1, s1
                        // token labels:
                        // rule labels: o1, s1, retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_o1 = new RewriteRuleSubtreeStream(adaptor, "rule o1", o1 != null ? o1.getTree() : null);
                        RewriteRuleSubtreeStream stream_s1 = new RewriteRuleSubtreeStream(adaptor, "rule s1", s1 != null ? s1.getTree() : null);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 797:7: -> ^( DATASET_INSET_TAG $o1 $s1)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:798:9: ^( DATASET_INSET_TAG $o1 $s1)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_INSET_TAG, "DATASET_INSET_TAG"), root_1);
                                adaptor.addChild(root_1, stream_o1.nextTree());
                                adaptor.addChild(root_1, stream_s1.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 20:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:800:5: ( CHARLENGTH '(' o1= expr ')' -> ^( DATASET_CHARLENGTH_TAG $o1) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:800:5: ( CHARLENGTH '(' o1= expr ')' -> ^( DATASET_CHARLENGTH_TAG $o1) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:800:6: CHARLENGTH '(' o1= expr ')'
                    {
                        CHARLENGTH142 = (Token) match(input, CHARLENGTH, FOLLOW_CHARLENGTH_in_exprAtom7497);
                        stream_CHARLENGTH.add(CHARLENGTH142);

                        char_literal143 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7499);
                        stream_426.add(char_literal143);

                        pushFollow(FOLLOW_expr_in_exprAtom7503);
                        o1 = expr();
                        state._fsp--;

                        stream_expr.add(o1.getTree());
                        char_literal144 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7505);
                        stream_427.add(char_literal144);

                        // AST REWRITE
                        // elements: o1
                        // token labels:
                        // rule labels: o1, retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_o1 = new RewriteRuleSubtreeStream(adaptor, "rule o1", o1 != null ? o1.getTree() : null);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 801:7: -> ^( DATASET_CHARLENGTH_TAG $o1)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:802:9: ^( DATASET_CHARLENGTH_TAG $o1)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_CHARLENGTH_TAG, "DATASET_CHARLENGTH_TAG"), root_1);
                                adaptor.addChild(root_1, stream_o1.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 21:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:803:5: ( TYPE '(' o1= expr ')' '=' o3= STRING_CONSTANT -> ^( DATASET_TYPE_TAG $o1 ^( STRING_CONSTANT_TAG $o3) ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:803:5: ( TYPE '(' o1= expr ')' '=' o3= STRING_CONSTANT -> ^( DATASET_TYPE_TAG $o1 ^( STRING_CONSTANT_TAG $o3) ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:803:6: TYPE '(' o1= expr ')' '=' o3= STRING_CONSTANT
                    {
                        TYPE145 = (Token) match(input, TYPE, FOLLOW_TYPE_in_exprAtom7536);
                        stream_TYPE.add(TYPE145);

                        char_literal146 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7538);
                        stream_426.add(char_literal146);

                        pushFollow(FOLLOW_expr_in_exprAtom7542);
                        o1 = expr();
                        state._fsp--;

                        stream_expr.add(o1.getTree());
                        char_literal147 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7544);
                        stream_427.add(char_literal147);

                        char_literal148 = (Token) match(input, EQ, FOLLOW_EQ_in_exprAtom7546);
                        stream_EQ.add(char_literal148);

                        o3 = (Token) match(input, STRING_CONSTANT, FOLLOW_STRING_CONSTANT_in_exprAtom7550);
                        stream_STRING_CONSTANT.add(o3);

                        // AST REWRITE
                        // elements: o1, o3
                        // token labels: o3
                        // rule labels: o1, retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleTokenStream stream_o3 = new RewriteRuleTokenStream(adaptor, "token o3", o3);
                        RewriteRuleSubtreeStream stream_o1 = new RewriteRuleSubtreeStream(adaptor, "rule o1", o1 != null ? o1.getTree() : null);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 804:7: -> ^( DATASET_TYPE_TAG $o1 ^( STRING_CONSTANT_TAG $o3) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:805:9: ^( DATASET_TYPE_TAG $o1 ^( STRING_CONSTANT_TAG $o3) )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_TYPE_TAG, "DATASET_TYPE_TAG"), root_1);
                                adaptor.addChild(root_1, stream_o1.nextTree());
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:807:11: ^( STRING_CONSTANT_TAG $o3)
                                {
                                    Object root_2 = (Object) adaptor.nil();
                                    root_2 = (Object) adaptor.becomeRoot((Object) adaptor.create(STRING_CONSTANT_TAG, "STRING_CONSTANT_TAG"), root_2);
                                    adaptor.addChild(root_2, stream_o3.nextNode());
                                    adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 22:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:809:5: INTERSECT '(' o1= expr ',' o2= expr ')'
                {
                    INTERSECT149 = (Token) match(input, INTERSECT, FOLLOW_INTERSECT_in_exprAtom7618);
                    stream_INTERSECT.add(INTERSECT149);

                    char_literal150 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7620);
                    stream_426.add(char_literal150);

                    pushFollow(FOLLOW_expr_in_exprAtom7624);
                    o1 = expr();
                    state._fsp--;

                    stream_expr.add(o1.getTree());
                    char_literal151 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom7626);
                    stream_CARTESIAN_PER.add(char_literal151);

                    pushFollow(FOLLOW_expr_in_exprAtom7630);
                    o2 = expr();
                    state._fsp--;

                    stream_expr.add(o2.getTree());
                    char_literal152 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7632);
                    stream_427.add(char_literal152);

                    // AST REWRITE
                    // elements: o2, o1
                    // token labels:
                    // rule labels: o1, o2, retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_o1 = new RewriteRuleSubtreeStream(adaptor, "rule o1", o1 != null ? o1.getTree() : null);
                    RewriteRuleSubtreeStream stream_o2 = new RewriteRuleSubtreeStream(adaptor, "rule o2", o2 != null ? o2.getTree() : null);
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 810:5: -> ^( DATASET_INTERSECT_TAG $o1 $o2)
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:811:7: ^( DATASET_INTERSECT_TAG $o1 $o2)
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_INTERSECT_TAG, "DATASET_INTERSECT_TAG"), root_1);
                            adaptor.addChild(root_1, stream_o1.nextTree());
                            adaptor.addChild(root_1, stream_o2.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;
                case 23:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:812:5: UNION '(' o1= expr ',' o2= expr ')'
                {
                    UNION153 = (Token) match(input, UNION, FOLLOW_UNION_in_exprAtom7660);
                    stream_UNION.add(UNION153);

                    char_literal154 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7662);
                    stream_426.add(char_literal154);

                    pushFollow(FOLLOW_expr_in_exprAtom7666);
                    o1 = expr();
                    state._fsp--;

                    stream_expr.add(o1.getTree());
                    char_literal155 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom7668);
                    stream_CARTESIAN_PER.add(char_literal155);

                    pushFollow(FOLLOW_expr_in_exprAtom7672);
                    o2 = expr();
                    state._fsp--;

                    stream_expr.add(o2.getTree());
                    char_literal156 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7674);
                    stream_427.add(char_literal156);

                    // AST REWRITE
                    // elements: o2, o1
                    // token labels:
                    // rule labels: o1, o2, retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_o1 = new RewriteRuleSubtreeStream(adaptor, "rule o1", o1 != null ? o1.getTree() : null);
                    RewriteRuleSubtreeStream stream_o2 = new RewriteRuleSubtreeStream(adaptor, "rule o2", o2 != null ? o2.getTree() : null);
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 813:5: -> ^( DATASET_UNION_TAG $o1 $o2)
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:814:7: ^( DATASET_UNION_TAG $o1 $o2)
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_UNION_TAG, "DATASET_UNION_TAG"), root_1);
                            adaptor.addChild(root_1, stream_o1.nextTree());
                            adaptor.addChild(root_1, stream_o2.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;
                case 24:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:815:5: DIFF '(' o1= expr ',' o2= expr ')'
                {
                    DIFF157 = (Token) match(input, DIFF, FOLLOW_DIFF_in_exprAtom7702);
                    stream_DIFF.add(DIFF157);

                    char_literal158 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7704);
                    stream_426.add(char_literal158);

                    pushFollow(FOLLOW_expr_in_exprAtom7708);
                    o1 = expr();
                    state._fsp--;

                    stream_expr.add(o1.getTree());
                    char_literal159 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom7710);
                    stream_CARTESIAN_PER.add(char_literal159);

                    pushFollow(FOLLOW_expr_in_exprAtom7714);
                    o2 = expr();
                    state._fsp--;

                    stream_expr.add(o2.getTree());
                    char_literal160 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7716);
                    stream_427.add(char_literal160);

                    // AST REWRITE
                    // elements: o1, o2
                    // token labels:
                    // rule labels: o1, o2, retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_o1 = new RewriteRuleSubtreeStream(adaptor, "rule o1", o1 != null ? o1.getTree() : null);
                    RewriteRuleSubtreeStream stream_o2 = new RewriteRuleSubtreeStream(adaptor, "rule o2", o2 != null ? o2.getTree() : null);
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 816:5: -> ^( DATASET_DIFF_TAG $o1 $o2)
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:817:7: ^( DATASET_DIFF_TAG $o1 $o2)
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_DIFF_TAG, "DATASET_DIFF_TAG"), root_1);
                            adaptor.addChild(root_1, stream_o1.nextTree());
                            adaptor.addChild(root_1, stream_o2.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;
                case 25:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:818:5: NOT_IN '(' o1= expr ',' o2= expr ')'
                {
                    NOT_IN161 = (Token) match(input, NOT_IN, FOLLOW_NOT_IN_in_exprAtom7744);
                    stream_NOT_IN.add(NOT_IN161);

                    char_literal162 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7746);
                    stream_426.add(char_literal162);

                    pushFollow(FOLLOW_expr_in_exprAtom7750);
                    o1 = expr();
                    state._fsp--;

                    stream_expr.add(o1.getTree());
                    char_literal163 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom7752);
                    stream_CARTESIAN_PER.add(char_literal163);

                    pushFollow(FOLLOW_expr_in_exprAtom7756);
                    o2 = expr();
                    state._fsp--;

                    stream_expr.add(o2.getTree());
                    char_literal164 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7758);
                    stream_427.add(char_literal164);

                    // AST REWRITE
                    // elements: o2, o1
                    // token labels:
                    // rule labels: o1, o2, retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_o1 = new RewriteRuleSubtreeStream(adaptor, "rule o1", o1 != null ? o1.getTree() : null);
                    RewriteRuleSubtreeStream stream_o2 = new RewriteRuleSubtreeStream(adaptor, "rule o2", o2 != null ? o2.getTree() : null);
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 819:5: -> ^( DATASET_NOT_IN_TAG $o1 $o2)
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:820:7: ^( DATASET_NOT_IN_TAG $o1 $o2)
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_NOT_IN_TAG, "DATASET_NOT_IN_TAG"), root_1);
                            adaptor.addChild(root_1, stream_o1.nextTree());
                            adaptor.addChild(root_1, stream_o2.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;
                case 26:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:821:5: ISNULL '(' o1= expr ')'
                {
                    ISNULL165 = (Token) match(input, ISNULL, FOLLOW_ISNULL_in_exprAtom7786);
                    stream_ISNULL.add(ISNULL165);

                    char_literal166 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7788);
                    stream_426.add(char_literal166);

                    pushFollow(FOLLOW_expr_in_exprAtom7792);
                    o1 = expr();
                    state._fsp--;

                    stream_expr.add(o1.getTree());
                    char_literal167 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7794);
                    stream_427.add(char_literal167);

                    // AST REWRITE
                    // elements: o1
                    // token labels:
                    // rule labels: o1, retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_o1 = new RewriteRuleSubtreeStream(adaptor, "rule o1", o1 != null ? o1.getTree() : null);
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 822:5: -> ^( DATASET_NOT_IN_TAG $o1)
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:823:7: ^( DATASET_NOT_IN_TAG $o1)
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_NOT_IN_TAG, "DATASET_NOT_IN_TAG"), root_1);
                            adaptor.addChild(root_1, stream_o1.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;
                case 27:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:824:5: NVL '(' b1= expr ',' c= constant ')'
                {
                    NVL168 = (Token) match(input, NVL, FOLLOW_NVL_in_exprAtom7819);
                    stream_NVL.add(NVL168);

                    char_literal169 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7821);
                    stream_426.add(char_literal169);

                    pushFollow(FOLLOW_expr_in_exprAtom7825);
                    b1 = expr();
                    state._fsp--;

                    stream_expr.add(b1.getTree());
                    char_literal170 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom7827);
                    stream_CARTESIAN_PER.add(char_literal170);

                    pushFollow(FOLLOW_constant_in_exprAtom7831);
                    c = constant();
                    state._fsp--;

                    stream_constant.add(c.getTree());
                    char_literal171 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7833);
                    stream_427.add(char_literal171);

                    // AST REWRITE
                    // elements: b1, c
                    // token labels:
                    // rule labels: c, retval, b1
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_c = new RewriteRuleSubtreeStream(adaptor, "rule c", c != null ? c.getTree() : null);
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                    RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 825:5: -> ^( DATASET_NVL_TAG $b1 $c)
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:826:7: ^( DATASET_NVL_TAG $b1 $c)
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_NVL_TAG, "DATASET_NVL_TAG"), root_1);
                            adaptor.addChild(root_1, stream_b1.nextTree());
                            adaptor.addChild(root_1, stream_c.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;
                case 28:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:827:5: ( MOD '(' b1= expr ',' b2= expr ')' -> ^( DATASET_MOD_TAG $b1 INTEGER_CONSTANT ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:827:5: ( MOD '(' b1= expr ',' b2= expr ')' -> ^( DATASET_MOD_TAG $b1 INTEGER_CONSTANT ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:827:6: MOD '(' b1= expr ',' b2= expr ')'
                    {
                        MOD172 = (Token) match(input, MOD, FOLLOW_MOD_in_exprAtom7862);
                        stream_MOD.add(MOD172);

                        char_literal173 = (Token) match(input, 426, FOLLOW_426_in_exprAtom7864);
                        stream_426.add(char_literal173);

                        pushFollow(FOLLOW_expr_in_exprAtom7868);
                        b1 = expr();
                        state._fsp--;

                        stream_expr.add(b1.getTree());
                        char_literal174 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_exprAtom7870);
                        stream_CARTESIAN_PER.add(char_literal174);

                        pushFollow(FOLLOW_expr_in_exprAtom7874);
                        b2 = expr();
                        state._fsp--;

                        stream_expr.add(b2.getTree());
                        char_literal175 = (Token) match(input, 427, FOLLOW_427_in_exprAtom7876);
                        stream_427.add(char_literal175);

                        // AST REWRITE
                        // elements: b1
                        // token labels:
                        // rule labels: retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 828:7: -> ^( DATASET_MOD_TAG $b1 INTEGER_CONSTANT )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:829:9: ^( DATASET_MOD_TAG $b1 INTEGER_CONSTANT )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_MOD_TAG, "DATASET_MOD_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                adaptor.addChild(root_1, (Object) adaptor.create(INTEGER_CONSTANT, "INTEGER_CONSTANT"));
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 29:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:830:5: validationExpr
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_validationExpr_in_exprAtom7908);
                    validationExpr176 = validationExpr();
                    state._fsp--;

                    adaptor.addChild(root_0, validationExpr176.getTree());

                }
                break;
                case 30:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:831:5: getExpr
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_getExpr_in_exprAtom7914);
                    getExpr177 = getExpr();
                    state._fsp--;

                    adaptor.addChild(root_0, getExpr177.getTree());

                }
                break;
                case 31:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:832:5: variableRef
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_variableRef_in_exprAtom7920);
                    variableRef178 = variableRef();
                    state._fsp--;

                    adaptor.addChild(root_0, variableRef178.getTree());

                }
                break;
                case 32:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:833:5: putExpr
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_putExpr_in_exprAtom7926);
                    putExpr179 = putExpr();
                    state._fsp--;

                    adaptor.addChild(root_0, putExpr179.getTree());

                }
                break;
                case 33:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:834:5: evalExpr
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_evalExpr_in_exprAtom7932);
                    evalExpr180 = evalExpr();
                    state._fsp--;

                    adaptor.addChild(root_0, evalExpr180.getTree());

                }
                break;
                case 34:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:835:5: mergeExpr
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_mergeExpr_in_exprAtom7938);
                    mergeExpr181 = mergeExpr();
                    state._fsp--;

                    adaptor.addChild(root_0, mergeExpr181.getTree());

                }
                break;
                case 35:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:836:5: hierarchyExpr
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_hierarchyExpr_in_exprAtom7944);
                    hierarchyExpr182 = hierarchyExpr();
                    state._fsp--;

                    adaptor.addChild(root_0, hierarchyExpr182.getTree());

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "variableRef"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:841:1: variableRef : ( '(' ! exprOr ')' !| varID | constant );
    public final ValidationMlParser.variableRef_return variableRef() throws Exception {
        ValidationMlParser.variableRef_return retval = new ValidationMlParser.variableRef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal183 = null;
        Token char_literal185 = null;
        ParserRuleReturnScope exprOr184 = null;
        ParserRuleReturnScope varID186 = null;
        ParserRuleReturnScope constant187 = null;

        Object char_literal183_tree = null;
        Object char_literal185_tree = null;

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:842:3: ( '(' ! exprOr ')' !| varID | constant )
            int alt23 = 3;
            switch (input.LA(1)) {
                case 426: {
                    alt23 = 1;
                }
                break;
                case IDENTIFIER: {
                    alt23 = 2;
                }
                break;
                case BOOLEAN_CONSTANT:
                case FLOAT_CONSTANT:
                case INTEGER_CONSTANT:
                case NULL_CONSTANT:
                case STRING_CONSTANT: {
                    alt23 = 3;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 23, 0, input);
                    throw nvae;
            }
            switch (alt23) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:843:3: '(' ! exprOr ')' !
                {
                    root_0 = (Object) adaptor.nil();


                    char_literal183 = (Token) match(input, 426, FOLLOW_426_in_variableRef7962);
                    pushFollow(FOLLOW_exprOr_in_variableRef7965);
                    exprOr184 = exprOr();
                    state._fsp--;

                    adaptor.addChild(root_0, exprOr184.getTree());

                    char_literal185 = (Token) match(input, 427, FOLLOW_427_in_variableRef7967);
                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:844:5: varID
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_varID_in_variableRef7974);
                    varID186 = varID();
                    state._fsp--;

                    adaptor.addChild(root_0, varID186.getTree());

                }
                break;
                case 3:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:845:5: constant
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_constant_in_variableRef7980);
                    constant187 = constant();
                    state._fsp--;

                    adaptor.addChild(root_0, constant187.getTree());

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "getExpr"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:850:1: getExpr : GET '(' persistentDatasetID ( ',' persistentDatasetID )* ( ',' keepClause )? ( ',' filterClause )? ( ',' aggregategetClause )? ')' ;
    public final ValidationMlParser.getExpr_return getExpr() throws Exception {
        ValidationMlParser.getExpr_return retval = new ValidationMlParser.getExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token GET188 = null;
        Token char_literal189 = null;
        Token char_literal191 = null;
        Token char_literal193 = null;
        Token char_literal195 = null;
        Token char_literal197 = null;
        Token char_literal199 = null;
        ParserRuleReturnScope persistentDatasetID190 = null;
        ParserRuleReturnScope persistentDatasetID192 = null;
        ParserRuleReturnScope keepClause194 = null;
        ParserRuleReturnScope filterClause196 = null;
        ParserRuleReturnScope aggregategetClause198 = null;

        Object GET188_tree = null;
        Object char_literal189_tree = null;
        Object char_literal191_tree = null;
        Object char_literal193_tree = null;
        Object char_literal195_tree = null;
        Object char_literal197_tree = null;
        Object char_literal199_tree = null;

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:851:3: ( GET '(' persistentDatasetID ( ',' persistentDatasetID )* ( ',' keepClause )? ( ',' filterClause )? ( ',' aggregategetClause )? ')' )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:852:3: GET '(' persistentDatasetID ( ',' persistentDatasetID )* ( ',' keepClause )? ( ',' filterClause )? ( ',' aggregategetClause )? ')'
            {
                root_0 = (Object) adaptor.nil();


                GET188 = (Token) match(input, GET, FOLLOW_GET_in_getExpr7999);
                GET188_tree = (Object) adaptor.create(GET188);
                adaptor.addChild(root_0, GET188_tree);

                char_literal189 = (Token) match(input, 426, FOLLOW_426_in_getExpr8001);
                char_literal189_tree = (Object) adaptor.create(char_literal189);
                adaptor.addChild(root_0, char_literal189_tree);

                pushFollow(FOLLOW_persistentDatasetID_in_getExpr8003);
                persistentDatasetID190 = persistentDatasetID();
                state._fsp--;

                adaptor.addChild(root_0, persistentDatasetID190.getTree());

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:852:31: ( ',' persistentDatasetID )*
                loop24:
                while (true) {
                    int alt24 = 2;
                    int LA24_0 = input.LA(1);
                    if ((LA24_0 == CARTESIAN_PER)) {
                        int LA24_1 = input.LA(2);
                        if ((LA24_1 == STRING_CONSTANT)) {
                            alt24 = 1;
                        }

                    }

                    switch (alt24) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:852:32: ',' persistentDatasetID
                        {
                            char_literal191 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_getExpr8006);
                            char_literal191_tree = (Object) adaptor.create(char_literal191);
                            adaptor.addChild(root_0, char_literal191_tree);

                            pushFollow(FOLLOW_persistentDatasetID_in_getExpr8008);
                            persistentDatasetID192 = persistentDatasetID();
                            state._fsp--;

                            adaptor.addChild(root_0, persistentDatasetID192.getTree());

                        }
                        break;

                        default:
                            break loop24;
                    }
                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:852:58: ( ',' keepClause )?
                int alt25 = 2;
                int LA25_0 = input.LA(1);
                if ((LA25_0 == CARTESIAN_PER)) {
                    int LA25_1 = input.LA(2);
                    if ((LA25_1 == KEEP)) {
                        alt25 = 1;
                    }
                }
                switch (alt25) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:852:59: ',' keepClause
                    {
                        char_literal193 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_getExpr8013);
                        char_literal193_tree = (Object) adaptor.create(char_literal193);
                        adaptor.addChild(root_0, char_literal193_tree);

                        pushFollow(FOLLOW_keepClause_in_getExpr8015);
                        keepClause194 = keepClause();
                        state._fsp--;

                        adaptor.addChild(root_0, keepClause194.getTree());

                    }
                    break;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:852:76: ( ',' filterClause )?
                int alt26 = 2;
                int LA26_0 = input.LA(1);
                if ((LA26_0 == CARTESIAN_PER)) {
                    int LA26_1 = input.LA(2);
                    if ((LA26_1 == ABS || LA26_1 == BOOLEAN_CONSTANT || LA26_1 == CONCAT || (LA26_1 >= EXP && LA26_1 <= FILTER) || LA26_1 == FLOAT_CONSTANT || LA26_1 == IDENTIFIER || LA26_1 == INTEGER_CONSTANT || LA26_1 == LCASE || LA26_1 == LEN || LA26_1 == LN || LA26_1 == MINUS || LA26_1 == MOD || LA26_1 == NOT || LA26_1 == NULL_CONSTANT || LA26_1 == PLUS || LA26_1 == POWER || LA26_1 == ROUND || LA26_1 == STRING_CONSTANT || LA26_1 == SUBSTR || (LA26_1 >= TRIM && LA26_1 <= TRUNC) || LA26_1 == UCASE || LA26_1 == 426 || LA26_1 == 430)) {
                        alt26 = 1;
                    }
                }
                switch (alt26) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:852:77: ',' filterClause
                    {
                        char_literal195 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_getExpr8020);
                        char_literal195_tree = (Object) adaptor.create(char_literal195);
                        adaptor.addChild(root_0, char_literal195_tree);

                        pushFollow(FOLLOW_filterClause_in_getExpr8022);
                        filterClause196 = filterClause();
                        state._fsp--;

                        adaptor.addChild(root_0, filterClause196.getTree());

                    }
                    break;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:852:96: ( ',' aggregategetClause )?
                int alt27 = 2;
                int LA27_0 = input.LA(1);
                if ((LA27_0 == CARTESIAN_PER)) {
                    alt27 = 1;
                }
                switch (alt27) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:852:97: ',' aggregategetClause
                    {
                        char_literal197 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_getExpr8027);
                        char_literal197_tree = (Object) adaptor.create(char_literal197);
                        adaptor.addChild(root_0, char_literal197_tree);

                        pushFollow(FOLLOW_aggregategetClause_in_getExpr8029);
                        aggregategetClause198 = aggregategetClause();
                        state._fsp--;

                        adaptor.addChild(root_0, aggregategetClause198.getTree());

                    }
                    break;

                }

                char_literal199 = (Token) match(input, 427, FOLLOW_427_in_getExpr8033);
                char_literal199_tree = (Object) adaptor.create(char_literal199);
                adaptor.addChild(root_0, char_literal199_tree);

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "persistentDatasetID"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:855:1: persistentDatasetID : STRING_CONSTANT ;
    public final ValidationMlParser.persistentDatasetID_return persistentDatasetID() throws Exception {
        ValidationMlParser.persistentDatasetID_return retval = new ValidationMlParser.persistentDatasetID_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STRING_CONSTANT200 = null;

        Object STRING_CONSTANT200_tree = null;

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:856:3: ( STRING_CONSTANT )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:857:3: STRING_CONSTANT
            {
                root_0 = (Object) adaptor.nil();


                STRING_CONSTANT200 = (Token) match(input, STRING_CONSTANT, FOLLOW_STRING_CONSTANT_in_persistentDatasetID8048);
                STRING_CONSTANT200_tree = (Object) adaptor.create(STRING_CONSTANT200);
                adaptor.addChild(root_0, STRING_CONSTANT200_tree);

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "putExpr"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:860:1: putExpr : PUT '(' expr ',' persistentDatasetID ')' ;
    public final ValidationMlParser.putExpr_return putExpr() throws Exception {
        ValidationMlParser.putExpr_return retval = new ValidationMlParser.putExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PUT201 = null;
        Token char_literal202 = null;
        Token char_literal204 = null;
        Token char_literal206 = null;
        ParserRuleReturnScope expr203 = null;
        ParserRuleReturnScope persistentDatasetID205 = null;

        Object PUT201_tree = null;
        Object char_literal202_tree = null;
        Object char_literal204_tree = null;
        Object char_literal206_tree = null;

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:861:3: ( PUT '(' expr ',' persistentDatasetID ')' )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:862:3: PUT '(' expr ',' persistentDatasetID ')'
            {
                root_0 = (Object) adaptor.nil();


                PUT201 = (Token) match(input, PUT, FOLLOW_PUT_in_putExpr8063);
                PUT201_tree = (Object) adaptor.create(PUT201);
                adaptor.addChild(root_0, PUT201_tree);

                char_literal202 = (Token) match(input, 426, FOLLOW_426_in_putExpr8065);
                char_literal202_tree = (Object) adaptor.create(char_literal202);
                adaptor.addChild(root_0, char_literal202_tree);

                pushFollow(FOLLOW_expr_in_putExpr8067);
                expr203 = expr();
                state._fsp--;

                adaptor.addChild(root_0, expr203.getTree());

                char_literal204 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_putExpr8069);
                char_literal204_tree = (Object) adaptor.create(char_literal204);
                adaptor.addChild(root_0, char_literal204_tree);

                pushFollow(FOLLOW_persistentDatasetID_in_putExpr8071);
                persistentDatasetID205 = persistentDatasetID();
                state._fsp--;

                adaptor.addChild(root_0, persistentDatasetID205.getTree());

                char_literal206 = (Token) match(input, 427, FOLLOW_427_in_putExpr8073);
                char_literal206_tree = (Object) adaptor.create(char_literal206);
                adaptor.addChild(root_0, char_literal206_tree);

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "evalExpr"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:865:1: evalExpr : EVAL '(' STRING_CONSTANT ( ',' variableRef )* ',' persistentDatasetID ')' ;
    public final ValidationMlParser.evalExpr_return evalExpr() throws Exception {
        ValidationMlParser.evalExpr_return retval = new ValidationMlParser.evalExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EVAL207 = null;
        Token char_literal208 = null;
        Token STRING_CONSTANT209 = null;
        Token char_literal210 = null;
        Token char_literal212 = null;
        Token char_literal214 = null;
        ParserRuleReturnScope variableRef211 = null;
        ParserRuleReturnScope persistentDatasetID213 = null;

        Object EVAL207_tree = null;
        Object char_literal208_tree = null;
        Object STRING_CONSTANT209_tree = null;
        Object char_literal210_tree = null;
        Object char_literal212_tree = null;
        Object char_literal214_tree = null;

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:866:3: ( EVAL '(' STRING_CONSTANT ( ',' variableRef )* ',' persistentDatasetID ')' )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:867:3: EVAL '(' STRING_CONSTANT ( ',' variableRef )* ',' persistentDatasetID ')'
            {
                root_0 = (Object) adaptor.nil();


                EVAL207 = (Token) match(input, EVAL, FOLLOW_EVAL_in_evalExpr8088);
                EVAL207_tree = (Object) adaptor.create(EVAL207);
                adaptor.addChild(root_0, EVAL207_tree);

                char_literal208 = (Token) match(input, 426, FOLLOW_426_in_evalExpr8090);
                char_literal208_tree = (Object) adaptor.create(char_literal208);
                adaptor.addChild(root_0, char_literal208_tree);

                STRING_CONSTANT209 = (Token) match(input, STRING_CONSTANT, FOLLOW_STRING_CONSTANT_in_evalExpr8092);
                STRING_CONSTANT209_tree = (Object) adaptor.create(STRING_CONSTANT209);
                adaptor.addChild(root_0, STRING_CONSTANT209_tree);

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:867:28: ( ',' variableRef )*
                loop28:
                while (true) {
                    int alt28 = 2;
                    int LA28_0 = input.LA(1);
                    if ((LA28_0 == CARTESIAN_PER)) {
                        int LA28_1 = input.LA(2);
                        if ((LA28_1 == STRING_CONSTANT)) {
                            int LA28_2 = input.LA(3);
                            if ((LA28_2 == CARTESIAN_PER)) {
                                alt28 = 1;
                            }

                        } else if ((LA28_1 == BOOLEAN_CONSTANT || LA28_1 == FLOAT_CONSTANT || LA28_1 == IDENTIFIER || LA28_1 == INTEGER_CONSTANT || LA28_1 == NULL_CONSTANT || LA28_1 == 426)) {
                            alt28 = 1;
                        }

                    }

                    switch (alt28) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:867:29: ',' variableRef
                        {
                            char_literal210 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_evalExpr8095);
                            char_literal210_tree = (Object) adaptor.create(char_literal210);
                            adaptor.addChild(root_0, char_literal210_tree);

                            pushFollow(FOLLOW_variableRef_in_evalExpr8097);
                            variableRef211 = variableRef();
                            state._fsp--;

                            adaptor.addChild(root_0, variableRef211.getTree());

                        }
                        break;

                        default:
                            break loop28;
                    }
                }

                char_literal212 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_evalExpr8101);
                char_literal212_tree = (Object) adaptor.create(char_literal212);
                adaptor.addChild(root_0, char_literal212_tree);

                pushFollow(FOLLOW_persistentDatasetID_in_evalExpr8103);
                persistentDatasetID213 = persistentDatasetID();
                state._fsp--;

                adaptor.addChild(root_0, persistentDatasetID213.getTree());

                char_literal214 = (Token) match(input, 427, FOLLOW_427_in_evalExpr8105);
                char_literal214_tree = (Object) adaptor.create(char_literal214);
                adaptor.addChild(root_0, char_literal214_tree);

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "validationExpr"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:870:1: validationExpr : CHECK '(' c1= exprOr ( ',' IMBALANCE '(' c2= exprOr ')' )? ( ',' ERLEVEL '(' c3= exprOr ')' )? ( ',' ERRORCODE '(' c4= constant ')' )? ( ',' THRESHOLD '(' c5= constant ')' )? ( ',' c6= ALL )? ')' -> ^( DATASET_CHECK_TAG ^( DATASET_CHECK_CONDITION_TAG $c1) ^( DATASET_CHECK_IMBALANCE_TAG ( $c2)? ) ^( DATASET_CHECK_ERLEVEL_TAG ( $c3)? ) ^( DATASET_CHECK_ERRORCODE_TAG ( $c4)? ) ^( DATASET_CHECK_THRESHOLD_TAG ( $c5)? ) ^( DATASET_CHECK_ALL_TAG ( $c6)? ) ) ;
    public final ValidationMlParser.validationExpr_return validationExpr() throws Exception {
        ValidationMlParser.validationExpr_return retval = new ValidationMlParser.validationExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token c6 = null;
        Token CHECK215 = null;
        Token char_literal216 = null;
        Token char_literal217 = null;
        Token IMBALANCE218 = null;
        Token char_literal219 = null;
        Token char_literal220 = null;
        Token char_literal221 = null;
        Token ERLEVEL222 = null;
        Token char_literal223 = null;
        Token char_literal224 = null;
        Token char_literal225 = null;
        Token ERRORCODE226 = null;
        Token char_literal227 = null;
        Token char_literal228 = null;
        Token char_literal229 = null;
        Token THRESHOLD230 = null;
        Token char_literal231 = null;
        Token char_literal232 = null;
        Token char_literal233 = null;
        Token char_literal234 = null;
        ParserRuleReturnScope c1 = null;
        ParserRuleReturnScope c2 = null;
        ParserRuleReturnScope c3 = null;
        ParserRuleReturnScope c4 = null;
        ParserRuleReturnScope c5 = null;

        Object c6_tree = null;
        Object CHECK215_tree = null;
        Object char_literal216_tree = null;
        Object char_literal217_tree = null;
        Object IMBALANCE218_tree = null;
        Object char_literal219_tree = null;
        Object char_literal220_tree = null;
        Object char_literal221_tree = null;
        Object ERLEVEL222_tree = null;
        Object char_literal223_tree = null;
        Object char_literal224_tree = null;
        Object char_literal225_tree = null;
        Object ERRORCODE226_tree = null;
        Object char_literal227_tree = null;
        Object char_literal228_tree = null;
        Object char_literal229_tree = null;
        Object THRESHOLD230_tree = null;
        Object char_literal231_tree = null;
        Object char_literal232_tree = null;
        Object char_literal233_tree = null;
        Object char_literal234_tree = null;
        RewriteRuleTokenStream stream_ALL = new RewriteRuleTokenStream(adaptor, "token ALL");
        RewriteRuleTokenStream stream_THRESHOLD = new RewriteRuleTokenStream(adaptor, "token THRESHOLD");
        RewriteRuleTokenStream stream_ERRORCODE = new RewriteRuleTokenStream(adaptor, "token ERRORCODE");
        RewriteRuleTokenStream stream_426 = new RewriteRuleTokenStream(adaptor, "token 426");
        RewriteRuleTokenStream stream_427 = new RewriteRuleTokenStream(adaptor, "token 427");
        RewriteRuleTokenStream stream_CHECK = new RewriteRuleTokenStream(adaptor, "token CHECK");
        RewriteRuleTokenStream stream_CARTESIAN_PER = new RewriteRuleTokenStream(adaptor, "token CARTESIAN_PER");
        RewriteRuleTokenStream stream_IMBALANCE = new RewriteRuleTokenStream(adaptor, "token IMBALANCE");
        RewriteRuleTokenStream stream_ERLEVEL = new RewriteRuleTokenStream(adaptor, "token ERLEVEL");
        RewriteRuleSubtreeStream stream_exprOr = new RewriteRuleSubtreeStream(adaptor, "rule exprOr");
        RewriteRuleSubtreeStream stream_constant = new RewriteRuleSubtreeStream(adaptor, "rule constant");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:871:3: ( CHECK '(' c1= exprOr ( ',' IMBALANCE '(' c2= exprOr ')' )? ( ',' ERLEVEL '(' c3= exprOr ')' )? ( ',' ERRORCODE '(' c4= constant ')' )? ( ',' THRESHOLD '(' c5= constant ')' )? ( ',' c6= ALL )? ')' -> ^( DATASET_CHECK_TAG ^( DATASET_CHECK_CONDITION_TAG $c1) ^( DATASET_CHECK_IMBALANCE_TAG ( $c2)? ) ^( DATASET_CHECK_ERLEVEL_TAG ( $c3)? ) ^( DATASET_CHECK_ERRORCODE_TAG ( $c4)? ) ^( DATASET_CHECK_THRESHOLD_TAG ( $c5)? ) ^( DATASET_CHECK_ALL_TAG ( $c6)? ) ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:872:3: CHECK '(' c1= exprOr ( ',' IMBALANCE '(' c2= exprOr ')' )? ( ',' ERLEVEL '(' c3= exprOr ')' )? ( ',' ERRORCODE '(' c4= constant ')' )? ( ',' THRESHOLD '(' c5= constant ')' )? ( ',' c6= ALL )? ')'
            {
                CHECK215 = (Token) match(input, CHECK, FOLLOW_CHECK_in_validationExpr8120);
                stream_CHECK.add(CHECK215);

                char_literal216 = (Token) match(input, 426, FOLLOW_426_in_validationExpr8122);
                stream_426.add(char_literal216);

                pushFollow(FOLLOW_exprOr_in_validationExpr8126);
                c1 = exprOr();
                state._fsp--;

                stream_exprOr.add(c1.getTree());
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:872:23: ( ',' IMBALANCE '(' c2= exprOr ')' )?
                int alt29 = 2;
                int LA29_0 = input.LA(1);
                if ((LA29_0 == CARTESIAN_PER)) {
                    int LA29_1 = input.LA(2);
                    if ((LA29_1 == IMBALANCE)) {
                        alt29 = 1;
                    }
                }
                switch (alt29) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:872:24: ',' IMBALANCE '(' c2= exprOr ')'
                    {
                        char_literal217 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_validationExpr8129);
                        stream_CARTESIAN_PER.add(char_literal217);

                        IMBALANCE218 = (Token) match(input, IMBALANCE, FOLLOW_IMBALANCE_in_validationExpr8131);
                        stream_IMBALANCE.add(IMBALANCE218);

                        char_literal219 = (Token) match(input, 426, FOLLOW_426_in_validationExpr8133);
                        stream_426.add(char_literal219);

                        pushFollow(FOLLOW_exprOr_in_validationExpr8137);
                        c2 = exprOr();
                        state._fsp--;

                        stream_exprOr.add(c2.getTree());
                        char_literal220 = (Token) match(input, 427, FOLLOW_427_in_validationExpr8139);
                        stream_427.add(char_literal220);

                    }
                    break;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:872:58: ( ',' ERLEVEL '(' c3= exprOr ')' )?
                int alt30 = 2;
                int LA30_0 = input.LA(1);
                if ((LA30_0 == CARTESIAN_PER)) {
                    int LA30_1 = input.LA(2);
                    if ((LA30_1 == ERLEVEL)) {
                        alt30 = 1;
                    }
                }
                switch (alt30) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:872:59: ',' ERLEVEL '(' c3= exprOr ')'
                    {
                        char_literal221 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_validationExpr8144);
                        stream_CARTESIAN_PER.add(char_literal221);

                        ERLEVEL222 = (Token) match(input, ERLEVEL, FOLLOW_ERLEVEL_in_validationExpr8146);
                        stream_ERLEVEL.add(ERLEVEL222);

                        char_literal223 = (Token) match(input, 426, FOLLOW_426_in_validationExpr8148);
                        stream_426.add(char_literal223);

                        pushFollow(FOLLOW_exprOr_in_validationExpr8152);
                        c3 = exprOr();
                        state._fsp--;

                        stream_exprOr.add(c3.getTree());
                        char_literal224 = (Token) match(input, 427, FOLLOW_427_in_validationExpr8154);
                        stream_427.add(char_literal224);

                    }
                    break;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:872:91: ( ',' ERRORCODE '(' c4= constant ')' )?
                int alt31 = 2;
                int LA31_0 = input.LA(1);
                if ((LA31_0 == CARTESIAN_PER)) {
                    int LA31_1 = input.LA(2);
                    if ((LA31_1 == ERRORCODE)) {
                        alt31 = 1;
                    }
                }
                switch (alt31) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:872:92: ',' ERRORCODE '(' c4= constant ')'
                    {
                        char_literal225 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_validationExpr8159);
                        stream_CARTESIAN_PER.add(char_literal225);

                        ERRORCODE226 = (Token) match(input, ERRORCODE, FOLLOW_ERRORCODE_in_validationExpr8161);
                        stream_ERRORCODE.add(ERRORCODE226);

                        char_literal227 = (Token) match(input, 426, FOLLOW_426_in_validationExpr8163);
                        stream_426.add(char_literal227);

                        pushFollow(FOLLOW_constant_in_validationExpr8167);
                        c4 = constant();
                        state._fsp--;

                        stream_constant.add(c4.getTree());
                        char_literal228 = (Token) match(input, 427, FOLLOW_427_in_validationExpr8169);
                        stream_427.add(char_literal228);

                    }
                    break;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:872:128: ( ',' THRESHOLD '(' c5= constant ')' )?
                int alt32 = 2;
                int LA32_0 = input.LA(1);
                if ((LA32_0 == CARTESIAN_PER)) {
                    int LA32_1 = input.LA(2);
                    if ((LA32_1 == THRESHOLD)) {
                        alt32 = 1;
                    }
                }
                switch (alt32) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:872:129: ',' THRESHOLD '(' c5= constant ')'
                    {
                        char_literal229 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_validationExpr8174);
                        stream_CARTESIAN_PER.add(char_literal229);

                        THRESHOLD230 = (Token) match(input, THRESHOLD, FOLLOW_THRESHOLD_in_validationExpr8176);
                        stream_THRESHOLD.add(THRESHOLD230);

                        char_literal231 = (Token) match(input, 426, FOLLOW_426_in_validationExpr8178);
                        stream_426.add(char_literal231);

                        pushFollow(FOLLOW_constant_in_validationExpr8182);
                        c5 = constant();
                        state._fsp--;

                        stream_constant.add(c5.getTree());
                        char_literal232 = (Token) match(input, 427, FOLLOW_427_in_validationExpr8184);
                        stream_427.add(char_literal232);

                    }
                    break;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:872:165: ( ',' c6= ALL )?
                int alt33 = 2;
                int LA33_0 = input.LA(1);
                if ((LA33_0 == CARTESIAN_PER)) {
                    alt33 = 1;
                }
                switch (alt33) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:872:166: ',' c6= ALL
                    {
                        char_literal233 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_validationExpr8189);
                        stream_CARTESIAN_PER.add(char_literal233);

                        c6 = (Token) match(input, ALL, FOLLOW_ALL_in_validationExpr8193);
                        stream_ALL.add(c6);

                    }
                    break;

                }

                char_literal234 = (Token) match(input, 427, FOLLOW_427_in_validationExpr8197);
                stream_427.add(char_literal234);

                // AST REWRITE
                // elements: c2, c4, c6, c3, c5, c1
                // token labels: c6
                // rule labels: c3, c4, c5, c1, retval, c2
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleTokenStream stream_c6 = new RewriteRuleTokenStream(adaptor, "token c6", c6);
                RewriteRuleSubtreeStream stream_c3 = new RewriteRuleSubtreeStream(adaptor, "rule c3", c3 != null ? c3.getTree() : null);
                RewriteRuleSubtreeStream stream_c4 = new RewriteRuleSubtreeStream(adaptor, "rule c4", c4 != null ? c4.getTree() : null);
                RewriteRuleSubtreeStream stream_c5 = new RewriteRuleSubtreeStream(adaptor, "rule c5", c5 != null ? c5.getTree() : null);
                RewriteRuleSubtreeStream stream_c1 = new RewriteRuleSubtreeStream(adaptor, "rule c1", c1 != null ? c1.getTree() : null);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                RewriteRuleSubtreeStream stream_c2 = new RewriteRuleSubtreeStream(adaptor, "rule c2", c2 != null ? c2.getTree() : null);

                root_0 = (Object) adaptor.nil();
                // 873:5: -> ^( DATASET_CHECK_TAG ^( DATASET_CHECK_CONDITION_TAG $c1) ^( DATASET_CHECK_IMBALANCE_TAG ( $c2)? ) ^( DATASET_CHECK_ERLEVEL_TAG ( $c3)? ) ^( DATASET_CHECK_ERRORCODE_TAG ( $c4)? ) ^( DATASET_CHECK_THRESHOLD_TAG ( $c5)? ) ^( DATASET_CHECK_ALL_TAG ( $c6)? ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:874:7: ^( DATASET_CHECK_TAG ^( DATASET_CHECK_CONDITION_TAG $c1) ^( DATASET_CHECK_IMBALANCE_TAG ( $c2)? ) ^( DATASET_CHECK_ERLEVEL_TAG ( $c3)? ) ^( DATASET_CHECK_ERRORCODE_TAG ( $c4)? ) ^( DATASET_CHECK_THRESHOLD_TAG ( $c5)? ) ^( DATASET_CHECK_ALL_TAG ( $c6)? ) )
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_CHECK_TAG, "DATASET_CHECK_TAG"), root_1);
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:876:9: ^( DATASET_CHECK_CONDITION_TAG $c1)
                        {
                            Object root_2 = (Object) adaptor.nil();
                            root_2 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_CHECK_CONDITION_TAG, "DATASET_CHECK_CONDITION_TAG"), root_2);
                            adaptor.addChild(root_2, stream_c1.nextTree());
                            adaptor.addChild(root_1, root_2);
                        }

                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:877:9: ^( DATASET_CHECK_IMBALANCE_TAG ( $c2)? )
                        {
                            Object root_2 = (Object) adaptor.nil();
                            root_2 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_CHECK_IMBALANCE_TAG, "DATASET_CHECK_IMBALANCE_TAG"), root_2);
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:877:40: ( $c2)?
                            if (stream_c2.hasNext()) {
                                adaptor.addChild(root_2, stream_c2.nextTree());
                            }
                            stream_c2.reset();

                            adaptor.addChild(root_1, root_2);
                        }

                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:878:9: ^( DATASET_CHECK_ERLEVEL_TAG ( $c3)? )
                        {
                            Object root_2 = (Object) adaptor.nil();
                            root_2 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_CHECK_ERLEVEL_TAG, "DATASET_CHECK_ERLEVEL_TAG"), root_2);
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:878:38: ( $c3)?
                            if (stream_c3.hasNext()) {
                                adaptor.addChild(root_2, stream_c3.nextTree());
                            }
                            stream_c3.reset();

                            adaptor.addChild(root_1, root_2);
                        }

                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:879:9: ^( DATASET_CHECK_ERRORCODE_TAG ( $c4)? )
                        {
                            Object root_2 = (Object) adaptor.nil();
                            root_2 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_CHECK_ERRORCODE_TAG, "DATASET_CHECK_ERRORCODE_TAG"), root_2);
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:879:40: ( $c4)?
                            if (stream_c4.hasNext()) {
                                adaptor.addChild(root_2, stream_c4.nextTree());
                            }
                            stream_c4.reset();

                            adaptor.addChild(root_1, root_2);
                        }

                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:880:9: ^( DATASET_CHECK_THRESHOLD_TAG ( $c5)? )
                        {
                            Object root_2 = (Object) adaptor.nil();
                            root_2 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_CHECK_THRESHOLD_TAG, "DATASET_CHECK_THRESHOLD_TAG"), root_2);
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:880:40: ( $c5)?
                            if (stream_c5.hasNext()) {
                                adaptor.addChild(root_2, stream_c5.nextTree());
                            }
                            stream_c5.reset();

                            adaptor.addChild(root_1, root_2);
                        }

                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:881:9: ^( DATASET_CHECK_ALL_TAG ( $c6)? )
                        {
                            Object root_2 = (Object) adaptor.nil();
                            root_2 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_CHECK_ALL_TAG, "DATASET_CHECK_ALL_TAG"), root_2);
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:881:34: ( $c6)?
                            if (stream_c6.hasNext()) {
                                adaptor.addChild(root_2, stream_c6.nextNode());
                            }
                            stream_c6.reset();

                            adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                    }

                }


                retval.tree = root_0;

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "mergeExpr"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:885:1: mergeExpr : MERGE '(' expr ( AS )? STRING_CONSTANT ( ',' expr ( AS )? STRING_CONSTANT )+ ',' ON '(' expr ')' ',' RETURN '(' ( expr ( AS )? STRING_CONSTANT ) ( ',' expr ( AS )? STRING_CONSTANT )+ ')' ')' ;
    public final ValidationMlParser.mergeExpr_return mergeExpr() throws Exception {
        ValidationMlParser.mergeExpr_return retval = new ValidationMlParser.mergeExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MERGE235 = null;
        Token char_literal236 = null;
        Token AS238 = null;
        Token STRING_CONSTANT239 = null;
        Token char_literal240 = null;
        Token AS242 = null;
        Token STRING_CONSTANT243 = null;
        Token char_literal244 = null;
        Token ON245 = null;
        Token char_literal246 = null;
        Token char_literal248 = null;
        Token char_literal249 = null;
        Token RETURN250 = null;
        Token char_literal251 = null;
        Token AS253 = null;
        Token STRING_CONSTANT254 = null;
        Token char_literal255 = null;
        Token AS257 = null;
        Token STRING_CONSTANT258 = null;
        Token char_literal259 = null;
        Token char_literal260 = null;
        ParserRuleReturnScope expr237 = null;
        ParserRuleReturnScope expr241 = null;
        ParserRuleReturnScope expr247 = null;
        ParserRuleReturnScope expr252 = null;
        ParserRuleReturnScope expr256 = null;

        Object MERGE235_tree = null;
        Object char_literal236_tree = null;
        Object AS238_tree = null;
        Object STRING_CONSTANT239_tree = null;
        Object char_literal240_tree = null;
        Object AS242_tree = null;
        Object STRING_CONSTANT243_tree = null;
        Object char_literal244_tree = null;
        Object ON245_tree = null;
        Object char_literal246_tree = null;
        Object char_literal248_tree = null;
        Object char_literal249_tree = null;
        Object RETURN250_tree = null;
        Object char_literal251_tree = null;
        Object AS253_tree = null;
        Object STRING_CONSTANT254_tree = null;
        Object char_literal255_tree = null;
        Object AS257_tree = null;
        Object STRING_CONSTANT258_tree = null;
        Object char_literal259_tree = null;
        Object char_literal260_tree = null;

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:886:3: ( MERGE '(' expr ( AS )? STRING_CONSTANT ( ',' expr ( AS )? STRING_CONSTANT )+ ',' ON '(' expr ')' ',' RETURN '(' ( expr ( AS )? STRING_CONSTANT ) ( ',' expr ( AS )? STRING_CONSTANT )+ ')' ')' )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:887:3: MERGE '(' expr ( AS )? STRING_CONSTANT ( ',' expr ( AS )? STRING_CONSTANT )+ ',' ON '(' expr ')' ',' RETURN '(' ( expr ( AS )? STRING_CONSTANT ) ( ',' expr ( AS )? STRING_CONSTANT )+ ')' ')'
            {
                root_0 = (Object) adaptor.nil();


                MERGE235 = (Token) match(input, MERGE, FOLLOW_MERGE_in_mergeExpr8340);
                MERGE235_tree = (Object) adaptor.create(MERGE235);
                adaptor.addChild(root_0, MERGE235_tree);

                char_literal236 = (Token) match(input, 426, FOLLOW_426_in_mergeExpr8342);
                char_literal236_tree = (Object) adaptor.create(char_literal236);
                adaptor.addChild(root_0, char_literal236_tree);

                pushFollow(FOLLOW_expr_in_mergeExpr8344);
                expr237 = expr();
                state._fsp--;

                adaptor.addChild(root_0, expr237.getTree());

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:887:18: ( AS )?
                int alt34 = 2;
                int LA34_0 = input.LA(1);
                if ((LA34_0 == AS)) {
                    alt34 = 1;
                }
                switch (alt34) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:887:18: AS
                    {
                        AS238 = (Token) match(input, AS, FOLLOW_AS_in_mergeExpr8346);
                        AS238_tree = (Object) adaptor.create(AS238);
                        adaptor.addChild(root_0, AS238_tree);

                    }
                    break;

                }

                STRING_CONSTANT239 = (Token) match(input, STRING_CONSTANT, FOLLOW_STRING_CONSTANT_in_mergeExpr8349);
                STRING_CONSTANT239_tree = (Object) adaptor.create(STRING_CONSTANT239);
                adaptor.addChild(root_0, STRING_CONSTANT239_tree);

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:887:38: ( ',' expr ( AS )? STRING_CONSTANT )+
                int cnt36 = 0;
                loop36:
                while (true) {
                    int alt36 = 2;
                    int LA36_0 = input.LA(1);
                    if ((LA36_0 == CARTESIAN_PER)) {
                        int LA36_1 = input.LA(2);
                        if ((LA36_1 == ABS || LA36_1 == BOOLEAN_CONSTANT || (LA36_1 >= CHARLENGTH && LA36_1 <= CODELIST_MATCH) || LA36_1 == DIFF || LA36_1 == EVAL || LA36_1 == EXP || LA36_1 == FLOAT_CONSTANT || LA36_1 == GET || LA36_1 == HIERARCHY || (LA36_1 >= IDENTIFIER && LA36_1 <= IF) || LA36_1 == INDEXOF || (LA36_1 >= INTEGER_CONSTANT && LA36_1 <= INTERSECT) || LA36_1 == ISNULL || LA36_1 == LCASE || LA36_1 == LEN || (LA36_1 >= LN && LA36_1 <= LOG) || LA36_1 == MAX || LA36_1 == MERGE || (LA36_1 >= MIN && LA36_1 <= MINUS) || LA36_1 == MISSING || LA36_1 == MOD || LA36_1 == NOT || (LA36_1 >= NOT_IN && LA36_1 <= NULL_CONSTANT) || LA36_1 == NVL || LA36_1 == PLUS || LA36_1 == POWER || LA36_1 == PUT || LA36_1 == ROUND || LA36_1 == STRING_CONSTANT || LA36_1 == SUBSTR || (LA36_1 >= TRIM && LA36_1 <= TRUNC) || (LA36_1 >= TYPE && LA36_1 <= UCASE) || LA36_1 == UNION || LA36_1 == 426)) {
                            alt36 = 1;
                        }

                    }

                    switch (alt36) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:887:39: ',' expr ( AS )? STRING_CONSTANT
                        {
                            char_literal240 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_mergeExpr8352);
                            char_literal240_tree = (Object) adaptor.create(char_literal240);
                            adaptor.addChild(root_0, char_literal240_tree);

                            pushFollow(FOLLOW_expr_in_mergeExpr8354);
                            expr241 = expr();
                            state._fsp--;

                            adaptor.addChild(root_0, expr241.getTree());

                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:887:48: ( AS )?
                            int alt35 = 2;
                            int LA35_0 = input.LA(1);
                            if ((LA35_0 == AS)) {
                                alt35 = 1;
                            }
                            switch (alt35) {
                                case 1:
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:887:48: AS
                                {
                                    AS242 = (Token) match(input, AS, FOLLOW_AS_in_mergeExpr8356);
                                    AS242_tree = (Object) adaptor.create(AS242);
                                    adaptor.addChild(root_0, AS242_tree);

                                }
                                break;

                            }

                            STRING_CONSTANT243 = (Token) match(input, STRING_CONSTANT, FOLLOW_STRING_CONSTANT_in_mergeExpr8359);
                            STRING_CONSTANT243_tree = (Object) adaptor.create(STRING_CONSTANT243);
                            adaptor.addChild(root_0, STRING_CONSTANT243_tree);

                        }
                        break;

                        default:
                            if (cnt36 >= 1) break loop36;
                            EarlyExitException eee = new EarlyExitException(36, input);
                            throw eee;
                    }
                    cnt36++;
                }

                char_literal244 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_mergeExpr8363);
                char_literal244_tree = (Object) adaptor.create(char_literal244);
                adaptor.addChild(root_0, char_literal244_tree);

                ON245 = (Token) match(input, ON, FOLLOW_ON_in_mergeExpr8365);
                ON245_tree = (Object) adaptor.create(ON245);
                adaptor.addChild(root_0, ON245_tree);

                char_literal246 = (Token) match(input, 426, FOLLOW_426_in_mergeExpr8367);
                char_literal246_tree = (Object) adaptor.create(char_literal246);
                adaptor.addChild(root_0, char_literal246_tree);

                pushFollow(FOLLOW_expr_in_mergeExpr8369);
                expr247 = expr();
                state._fsp--;

                adaptor.addChild(root_0, expr247.getTree());

                char_literal248 = (Token) match(input, 427, FOLLOW_427_in_mergeExpr8371);
                char_literal248_tree = (Object) adaptor.create(char_literal248);
                adaptor.addChild(root_0, char_literal248_tree);

                char_literal249 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_mergeExpr8373);
                char_literal249_tree = (Object) adaptor.create(char_literal249);
                adaptor.addChild(root_0, char_literal249_tree);

                RETURN250 = (Token) match(input, RETURN, FOLLOW_RETURN_in_mergeExpr8375);
                RETURN250_tree = (Object) adaptor.create(RETURN250);
                adaptor.addChild(root_0, RETURN250_tree);

                char_literal251 = (Token) match(input, 426, FOLLOW_426_in_mergeExpr8377);
                char_literal251_tree = (Object) adaptor.create(char_literal251);
                adaptor.addChild(root_0, char_literal251_tree);

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:887:105: ( expr ( AS )? STRING_CONSTANT )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:887:106: expr ( AS )? STRING_CONSTANT
                {
                    pushFollow(FOLLOW_expr_in_mergeExpr8380);
                    expr252 = expr();
                    state._fsp--;

                    adaptor.addChild(root_0, expr252.getTree());

                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:887:111: ( AS )?
                    int alt37 = 2;
                    int LA37_0 = input.LA(1);
                    if ((LA37_0 == AS)) {
                        alt37 = 1;
                    }
                    switch (alt37) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:887:111: AS
                        {
                            AS253 = (Token) match(input, AS, FOLLOW_AS_in_mergeExpr8382);
                            AS253_tree = (Object) adaptor.create(AS253);
                            adaptor.addChild(root_0, AS253_tree);

                        }
                        break;

                    }

                    STRING_CONSTANT254 = (Token) match(input, STRING_CONSTANT, FOLLOW_STRING_CONSTANT_in_mergeExpr8385);
                    STRING_CONSTANT254_tree = (Object) adaptor.create(STRING_CONSTANT254);
                    adaptor.addChild(root_0, STRING_CONSTANT254_tree);

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:887:132: ( ',' expr ( AS )? STRING_CONSTANT )+
                int cnt39 = 0;
                loop39:
                while (true) {
                    int alt39 = 2;
                    int LA39_0 = input.LA(1);
                    if ((LA39_0 == CARTESIAN_PER)) {
                        alt39 = 1;
                    }

                    switch (alt39) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:887:133: ',' expr ( AS )? STRING_CONSTANT
                        {
                            char_literal255 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_mergeExpr8389);
                            char_literal255_tree = (Object) adaptor.create(char_literal255);
                            adaptor.addChild(root_0, char_literal255_tree);

                            pushFollow(FOLLOW_expr_in_mergeExpr8391);
                            expr256 = expr();
                            state._fsp--;

                            adaptor.addChild(root_0, expr256.getTree());

                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:887:142: ( AS )?
                            int alt38 = 2;
                            int LA38_0 = input.LA(1);
                            if ((LA38_0 == AS)) {
                                alt38 = 1;
                            }
                            switch (alt38) {
                                case 1:
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:887:142: AS
                                {
                                    AS257 = (Token) match(input, AS, FOLLOW_AS_in_mergeExpr8393);
                                    AS257_tree = (Object) adaptor.create(AS257);
                                    adaptor.addChild(root_0, AS257_tree);

                                }
                                break;

                            }

                            STRING_CONSTANT258 = (Token) match(input, STRING_CONSTANT, FOLLOW_STRING_CONSTANT_in_mergeExpr8396);
                            STRING_CONSTANT258_tree = (Object) adaptor.create(STRING_CONSTANT258);
                            adaptor.addChild(root_0, STRING_CONSTANT258_tree);

                        }
                        break;

                        default:
                            if (cnt39 >= 1) break loop39;
                            EarlyExitException eee = new EarlyExitException(39, input);
                            throw eee;
                    }
                    cnt39++;
                }

                char_literal259 = (Token) match(input, 427, FOLLOW_427_in_mergeExpr8400);
                char_literal259_tree = (Object) adaptor.create(char_literal259);
                adaptor.addChild(root_0, char_literal259_tree);

                char_literal260 = (Token) match(input, 427, FOLLOW_427_in_mergeExpr8402);
                char_literal260_tree = (Object) adaptor.create(char_literal260);
                adaptor.addChild(root_0, char_literal260_tree);

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "hierarchyExpr"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:890:1: hierarchyExpr : HIERARCHY '(' expr ',' IDENTIFIER ',' ( STRING_CONSTANT | ( mappingExpr ( ',' mappingExpr )* AS STRING_CONSTANT ) ) ',' BOOLEAN_CONSTANT ( ',' aggrParam )? ')' ;
    public final ValidationMlParser.hierarchyExpr_return hierarchyExpr() throws Exception {
        ValidationMlParser.hierarchyExpr_return retval = new ValidationMlParser.hierarchyExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token HIERARCHY261 = null;
        Token char_literal262 = null;
        Token char_literal264 = null;
        Token IDENTIFIER265 = null;
        Token char_literal266 = null;
        Token STRING_CONSTANT267 = null;
        Token char_literal269 = null;
        Token AS271 = null;
        Token STRING_CONSTANT272 = null;
        Token char_literal273 = null;
        Token BOOLEAN_CONSTANT274 = null;
        Token char_literal275 = null;
        Token char_literal277 = null;
        ParserRuleReturnScope expr263 = null;
        ParserRuleReturnScope mappingExpr268 = null;
        ParserRuleReturnScope mappingExpr270 = null;
        ParserRuleReturnScope aggrParam276 = null;

        Object HIERARCHY261_tree = null;
        Object char_literal262_tree = null;
        Object char_literal264_tree = null;
        Object IDENTIFIER265_tree = null;
        Object char_literal266_tree = null;
        Object STRING_CONSTANT267_tree = null;
        Object char_literal269_tree = null;
        Object AS271_tree = null;
        Object STRING_CONSTANT272_tree = null;
        Object char_literal273_tree = null;
        Object BOOLEAN_CONSTANT274_tree = null;
        Object char_literal275_tree = null;
        Object char_literal277_tree = null;

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:891:3: ( HIERARCHY '(' expr ',' IDENTIFIER ',' ( STRING_CONSTANT | ( mappingExpr ( ',' mappingExpr )* AS STRING_CONSTANT ) ) ',' BOOLEAN_CONSTANT ( ',' aggrParam )? ')' )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:892:3: HIERARCHY '(' expr ',' IDENTIFIER ',' ( STRING_CONSTANT | ( mappingExpr ( ',' mappingExpr )* AS STRING_CONSTANT ) ) ',' BOOLEAN_CONSTANT ( ',' aggrParam )? ')'
            {
                root_0 = (Object) adaptor.nil();


                HIERARCHY261 = (Token) match(input, HIERARCHY, FOLLOW_HIERARCHY_in_hierarchyExpr8417);
                HIERARCHY261_tree = (Object) adaptor.create(HIERARCHY261);
                adaptor.addChild(root_0, HIERARCHY261_tree);

                char_literal262 = (Token) match(input, 426, FOLLOW_426_in_hierarchyExpr8419);
                char_literal262_tree = (Object) adaptor.create(char_literal262);
                adaptor.addChild(root_0, char_literal262_tree);

                pushFollow(FOLLOW_expr_in_hierarchyExpr8421);
                expr263 = expr();
                state._fsp--;

                adaptor.addChild(root_0, expr263.getTree());

                char_literal264 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_hierarchyExpr8423);
                char_literal264_tree = (Object) adaptor.create(char_literal264);
                adaptor.addChild(root_0, char_literal264_tree);

                IDENTIFIER265 = (Token) match(input, IDENTIFIER, FOLLOW_IDENTIFIER_in_hierarchyExpr8425);
                IDENTIFIER265_tree = (Object) adaptor.create(IDENTIFIER265);
                adaptor.addChild(root_0, IDENTIFIER265_tree);

                char_literal266 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_hierarchyExpr8427);
                char_literal266_tree = (Object) adaptor.create(char_literal266);
                adaptor.addChild(root_0, char_literal266_tree);

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:893:3: ( STRING_CONSTANT | ( mappingExpr ( ',' mappingExpr )* AS STRING_CONSTANT ) )
                int alt41 = 2;
                int LA41_0 = input.LA(1);
                if ((LA41_0 == STRING_CONSTANT)) {
                    alt41 = 1;
                } else if ((LA41_0 == 426)) {
                    alt41 = 2;
                } else {
                    NoViableAltException nvae =
                            new NoViableAltException("", 41, 0, input);
                    throw nvae;
                }

                switch (alt41) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:894:5: STRING_CONSTANT
                    {
                        STRING_CONSTANT267 = (Token) match(input, STRING_CONSTANT, FOLLOW_STRING_CONSTANT_in_hierarchyExpr8437);
                        STRING_CONSTANT267_tree = (Object) adaptor.create(STRING_CONSTANT267);
                        adaptor.addChild(root_0, STRING_CONSTANT267_tree);

                    }
                    break;
                    case 2:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:895:7: ( mappingExpr ( ',' mappingExpr )* AS STRING_CONSTANT )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:895:7: ( mappingExpr ( ',' mappingExpr )* AS STRING_CONSTANT )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:895:8: mappingExpr ( ',' mappingExpr )* AS STRING_CONSTANT
                        {
                            pushFollow(FOLLOW_mappingExpr_in_hierarchyExpr8446);
                            mappingExpr268 = mappingExpr();
                            state._fsp--;

                            adaptor.addChild(root_0, mappingExpr268.getTree());

                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:895:20: ( ',' mappingExpr )*
                            loop40:
                            while (true) {
                                int alt40 = 2;
                                int LA40_0 = input.LA(1);
                                if ((LA40_0 == CARTESIAN_PER)) {
                                    alt40 = 1;
                                }

                                switch (alt40) {
                                    case 1:
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:895:21: ',' mappingExpr
                                    {
                                        char_literal269 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_hierarchyExpr8449);
                                        char_literal269_tree = (Object) adaptor.create(char_literal269);
                                        adaptor.addChild(root_0, char_literal269_tree);

                                        pushFollow(FOLLOW_mappingExpr_in_hierarchyExpr8451);
                                        mappingExpr270 = mappingExpr();
                                        state._fsp--;

                                        adaptor.addChild(root_0, mappingExpr270.getTree());

                                    }
                                    break;

                                    default:
                                        break loop40;
                                }
                            }

                            AS271 = (Token) match(input, AS, FOLLOW_AS_in_hierarchyExpr8455);
                            AS271_tree = (Object) adaptor.create(AS271);
                            adaptor.addChild(root_0, AS271_tree);

                            STRING_CONSTANT272 = (Token) match(input, STRING_CONSTANT, FOLLOW_STRING_CONSTANT_in_hierarchyExpr8457);
                            STRING_CONSTANT272_tree = (Object) adaptor.create(STRING_CONSTANT272);
                            adaptor.addChild(root_0, STRING_CONSTANT272_tree);

                        }

                    }
                    break;

                }

                char_literal273 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_hierarchyExpr8466);
                char_literal273_tree = (Object) adaptor.create(char_literal273);
                adaptor.addChild(root_0, char_literal273_tree);

                BOOLEAN_CONSTANT274 = (Token) match(input, BOOLEAN_CONSTANT, FOLLOW_BOOLEAN_CONSTANT_in_hierarchyExpr8468);
                BOOLEAN_CONSTANT274_tree = (Object) adaptor.create(BOOLEAN_CONSTANT274);
                adaptor.addChild(root_0, BOOLEAN_CONSTANT274_tree);

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:897:24: ( ',' aggrParam )?
                int alt42 = 2;
                int LA42_0 = input.LA(1);
                if ((LA42_0 == CARTESIAN_PER)) {
                    alt42 = 1;
                }
                switch (alt42) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:897:25: ',' aggrParam
                    {
                        char_literal275 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_hierarchyExpr8471);
                        char_literal275_tree = (Object) adaptor.create(char_literal275);
                        adaptor.addChild(root_0, char_literal275_tree);

                        pushFollow(FOLLOW_aggrParam_in_hierarchyExpr8473);
                        aggrParam276 = aggrParam();
                        state._fsp--;

                        adaptor.addChild(root_0, aggrParam276.getTree());

                    }
                    break;

                }

                char_literal277 = (Token) match(input, 427, FOLLOW_427_in_hierarchyExpr8477);
                char_literal277_tree = (Object) adaptor.create(char_literal277);
                adaptor.addChild(root_0, char_literal277_tree);

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "mappingExpr"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:900:1: mappingExpr : '(' constant ',' INTEGER_CONSTANT ',' ( PLUS | MINUS ) ')' TO constant ;
    public final ValidationMlParser.mappingExpr_return mappingExpr() throws Exception {
        ValidationMlParser.mappingExpr_return retval = new ValidationMlParser.mappingExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal278 = null;
        Token char_literal280 = null;
        Token INTEGER_CONSTANT281 = null;
        Token char_literal282 = null;
        Token set283 = null;
        Token char_literal284 = null;
        Token TO285 = null;
        ParserRuleReturnScope constant279 = null;
        ParserRuleReturnScope constant286 = null;

        Object char_literal278_tree = null;
        Object char_literal280_tree = null;
        Object INTEGER_CONSTANT281_tree = null;
        Object char_literal282_tree = null;
        Object set283_tree = null;
        Object char_literal284_tree = null;
        Object TO285_tree = null;

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:901:3: ( '(' constant ',' INTEGER_CONSTANT ',' ( PLUS | MINUS ) ')' TO constant )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:902:3: '(' constant ',' INTEGER_CONSTANT ',' ( PLUS | MINUS ) ')' TO constant
            {
                root_0 = (Object) adaptor.nil();


                char_literal278 = (Token) match(input, 426, FOLLOW_426_in_mappingExpr8492);
                char_literal278_tree = (Object) adaptor.create(char_literal278);
                adaptor.addChild(root_0, char_literal278_tree);

                pushFollow(FOLLOW_constant_in_mappingExpr8494);
                constant279 = constant();
                state._fsp--;

                adaptor.addChild(root_0, constant279.getTree());

                char_literal280 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_mappingExpr8496);
                char_literal280_tree = (Object) adaptor.create(char_literal280);
                adaptor.addChild(root_0, char_literal280_tree);

                INTEGER_CONSTANT281 = (Token) match(input, INTEGER_CONSTANT, FOLLOW_INTEGER_CONSTANT_in_mappingExpr8498);
                INTEGER_CONSTANT281_tree = (Object) adaptor.create(INTEGER_CONSTANT281);
                adaptor.addChild(root_0, INTEGER_CONSTANT281_tree);

                char_literal282 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_mappingExpr8500);
                char_literal282_tree = (Object) adaptor.create(char_literal282);
                adaptor.addChild(root_0, char_literal282_tree);

                set283 = input.LT(1);
                if (input.LA(1) == MINUS || input.LA(1) == PLUS) {
                    input.consume();
                    adaptor.addChild(root_0, (Object) adaptor.create(set283));
                    state.errorRecovery = false;
                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                }
                char_literal284 = (Token) match(input, 427, FOLLOW_427_in_mappingExpr8526);
                char_literal284_tree = (Object) adaptor.create(char_literal284);
                adaptor.addChild(root_0, char_literal284_tree);

                TO285 = (Token) match(input, TO, FOLLOW_TO_in_mappingExpr8528);
                TO285_tree = (Object) adaptor.create(TO285);
                adaptor.addChild(root_0, TO285_tree);

                pushFollow(FOLLOW_constant_in_mappingExpr8530);
                constant286 = constant();
                state._fsp--;

                adaptor.addChild(root_0, constant286.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "aggrParam"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:910:1: aggrParam : ( 'sum' | 'prod' );
    public final ValidationMlParser.aggrParam_return aggrParam() throws Exception {
        ValidationMlParser.aggrParam_return retval = new ValidationMlParser.aggrParam_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set287 = null;

        Object set287_tree = null;

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:911:3: ( 'sum' | 'prod' )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:
            {
                root_0 = (Object) adaptor.nil();


                set287 = input.LT(1);
                if (input.LA(1) == SUM || input.LA(1) == 434) {
                    input.consume();
                    adaptor.addChild(root_0, (Object) adaptor.create(set287));
                    state.errorRecovery = false;
                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                }
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "aggregategetClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:916:1: aggregategetClause : AGGREGATE '(' aggrFunction '(' scalarExpr ')' ( ',' aggrFunction '(' scalarExpr ')' )* ')' -> ( ^( aggrFunction scalarExpr ) )+ ;
    public final ValidationMlParser.aggregategetClause_return aggregategetClause() throws Exception {
        ValidationMlParser.aggregategetClause_return retval = new ValidationMlParser.aggregategetClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AGGREGATE288 = null;
        Token char_literal289 = null;
        Token char_literal291 = null;
        Token char_literal293 = null;
        Token char_literal294 = null;
        Token char_literal296 = null;
        Token char_literal298 = null;
        Token char_literal299 = null;
        ParserRuleReturnScope aggrFunction290 = null;
        ParserRuleReturnScope scalarExpr292 = null;
        ParserRuleReturnScope aggrFunction295 = null;
        ParserRuleReturnScope scalarExpr297 = null;

        Object AGGREGATE288_tree = null;
        Object char_literal289_tree = null;
        Object char_literal291_tree = null;
        Object char_literal293_tree = null;
        Object char_literal294_tree = null;
        Object char_literal296_tree = null;
        Object char_literal298_tree = null;
        Object char_literal299_tree = null;
        RewriteRuleTokenStream stream_AGGREGATE = new RewriteRuleTokenStream(adaptor, "token AGGREGATE");
        RewriteRuleTokenStream stream_426 = new RewriteRuleTokenStream(adaptor, "token 426");
        RewriteRuleTokenStream stream_427 = new RewriteRuleTokenStream(adaptor, "token 427");
        RewriteRuleTokenStream stream_CARTESIAN_PER = new RewriteRuleTokenStream(adaptor, "token CARTESIAN_PER");
        RewriteRuleSubtreeStream stream_scalarExpr = new RewriteRuleSubtreeStream(adaptor, "rule scalarExpr");
        RewriteRuleSubtreeStream stream_aggrFunction = new RewriteRuleSubtreeStream(adaptor, "rule aggrFunction");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:917:3: ( AGGREGATE '(' aggrFunction '(' scalarExpr ')' ( ',' aggrFunction '(' scalarExpr ')' )* ')' -> ( ^( aggrFunction scalarExpr ) )+ )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:918:3: AGGREGATE '(' aggrFunction '(' scalarExpr ')' ( ',' aggrFunction '(' scalarExpr ')' )* ')'
            {
                AGGREGATE288 = (Token) match(input, AGGREGATE, FOLLOW_AGGREGATE_in_aggregategetClause8566);
                stream_AGGREGATE.add(AGGREGATE288);

                char_literal289 = (Token) match(input, 426, FOLLOW_426_in_aggregategetClause8568);
                stream_426.add(char_literal289);

                pushFollow(FOLLOW_aggrFunction_in_aggregategetClause8570);
                aggrFunction290 = aggrFunction();
                state._fsp--;

                stream_aggrFunction.add(aggrFunction290.getTree());
                char_literal291 = (Token) match(input, 426, FOLLOW_426_in_aggregategetClause8572);
                stream_426.add(char_literal291);

                pushFollow(FOLLOW_scalarExpr_in_aggregategetClause8574);
                scalarExpr292 = scalarExpr();
                state._fsp--;

                stream_scalarExpr.add(scalarExpr292.getTree());
                char_literal293 = (Token) match(input, 427, FOLLOW_427_in_aggregategetClause8576);
                stream_427.add(char_literal293);

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:918:49: ( ',' aggrFunction '(' scalarExpr ')' )*
                loop43:
                while (true) {
                    int alt43 = 2;
                    int LA43_0 = input.LA(1);
                    if ((LA43_0 == CARTESIAN_PER)) {
                        alt43 = 1;
                    }

                    switch (alt43) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:918:50: ',' aggrFunction '(' scalarExpr ')'
                        {
                            char_literal294 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_aggregategetClause8579);
                            stream_CARTESIAN_PER.add(char_literal294);

                            pushFollow(FOLLOW_aggrFunction_in_aggregategetClause8581);
                            aggrFunction295 = aggrFunction();
                            state._fsp--;

                            stream_aggrFunction.add(aggrFunction295.getTree());
                            char_literal296 = (Token) match(input, 426, FOLLOW_426_in_aggregategetClause8583);
                            stream_426.add(char_literal296);

                            pushFollow(FOLLOW_scalarExpr_in_aggregategetClause8585);
                            scalarExpr297 = scalarExpr();
                            state._fsp--;

                            stream_scalarExpr.add(scalarExpr297.getTree());
                            char_literal298 = (Token) match(input, 427, FOLLOW_427_in_aggregategetClause8587);
                            stream_427.add(char_literal298);

                        }
                        break;

                        default:
                            break loop43;
                    }
                }

                char_literal299 = (Token) match(input, 427, FOLLOW_427_in_aggregategetClause8591);
                stream_427.add(char_literal299);

                // AST REWRITE
                // elements: scalarExpr, aggrFunction
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                root_0 = (Object) adaptor.nil();
                // 919:5: -> ( ^( aggrFunction scalarExpr ) )+
                {
                    if (!(stream_scalarExpr.hasNext() || stream_aggrFunction.hasNext())) {
                        throw new RewriteEarlyExitException();
                    }
                    while (stream_scalarExpr.hasNext() || stream_aggrFunction.hasNext()) {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:920:7: ^( aggrFunction scalarExpr )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot(stream_aggrFunction.nextNode(), root_1);
                            adaptor.addChild(root_1, stream_scalarExpr.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }
                    stream_scalarExpr.reset();
                    stream_aggrFunction.reset();

                }


                retval.tree = root_0;

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "aggregateClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:923:1: aggregateClause : aggrFunctionClause ( ',' aggrFunctionClause )* -> ( ^( aggrFunctionClause ) )+ ;
    public final ValidationMlParser.aggregateClause_return aggregateClause() throws Exception {
        ValidationMlParser.aggregateClause_return retval = new ValidationMlParser.aggregateClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal301 = null;
        ParserRuleReturnScope aggrFunctionClause300 = null;
        ParserRuleReturnScope aggrFunctionClause302 = null;

        Object char_literal301_tree = null;
        RewriteRuleTokenStream stream_CARTESIAN_PER = new RewriteRuleTokenStream(adaptor, "token CARTESIAN_PER");
        RewriteRuleSubtreeStream stream_aggrFunctionClause = new RewriteRuleSubtreeStream(adaptor, "rule aggrFunctionClause");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:924:3: ( aggrFunctionClause ( ',' aggrFunctionClause )* -> ( ^( aggrFunctionClause ) )+ )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:925:3: aggrFunctionClause ( ',' aggrFunctionClause )*
            {
                pushFollow(FOLLOW_aggrFunctionClause_in_aggregateClause8625);
                aggrFunctionClause300 = aggrFunctionClause();
                state._fsp--;

                stream_aggrFunctionClause.add(aggrFunctionClause300.getTree());
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:925:22: ( ',' aggrFunctionClause )*
                loop44:
                while (true) {
                    int alt44 = 2;
                    int LA44_0 = input.LA(1);
                    if ((LA44_0 == CARTESIAN_PER)) {
                        alt44 = 1;
                    }

                    switch (alt44) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:925:23: ',' aggrFunctionClause
                        {
                            char_literal301 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_aggregateClause8628);
                            stream_CARTESIAN_PER.add(char_literal301);

                            pushFollow(FOLLOW_aggrFunctionClause_in_aggregateClause8630);
                            aggrFunctionClause302 = aggrFunctionClause();
                            state._fsp--;

                            stream_aggrFunctionClause.add(aggrFunctionClause302.getTree());
                        }
                        break;

                        default:
                            break loop44;
                    }
                }

                // AST REWRITE
                // elements: aggrFunctionClause
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                root_0 = (Object) adaptor.nil();
                // 926:5: -> ( ^( aggrFunctionClause ) )+
                {
                    if (!(stream_aggrFunctionClause.hasNext())) {
                        throw new RewriteEarlyExitException();
                    }
                    while (stream_aggrFunctionClause.hasNext()) {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:927:7: ^( aggrFunctionClause )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot(stream_aggrFunctionClause.nextNode(), root_1);
                            adaptor.addChild(root_0, root_1);
                        }

                    }
                    stream_aggrFunctionClause.reset();

                }


                retval.tree = root_0;

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "aggrFunctionClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:930:1: aggrFunctionClause : ( aggrFunction '(' scalarExpr ')' -> ^( aggrFunction scalarExpr ) | percentileFunction );
    public final ValidationMlParser.aggrFunctionClause_return aggrFunctionClause() throws Exception {
        ValidationMlParser.aggrFunctionClause_return retval = new ValidationMlParser.aggrFunctionClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal304 = null;
        Token char_literal306 = null;
        ParserRuleReturnScope aggrFunction303 = null;
        ParserRuleReturnScope scalarExpr305 = null;
        ParserRuleReturnScope percentileFunction307 = null;

        Object char_literal304_tree = null;
        Object char_literal306_tree = null;
        RewriteRuleTokenStream stream_426 = new RewriteRuleTokenStream(adaptor, "token 426");
        RewriteRuleTokenStream stream_427 = new RewriteRuleTokenStream(adaptor, "token 427");
        RewriteRuleSubtreeStream stream_scalarExpr = new RewriteRuleSubtreeStream(adaptor, "rule scalarExpr");
        RewriteRuleSubtreeStream stream_aggrFunction = new RewriteRuleSubtreeStream(adaptor, "rule aggrFunction");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:931:3: ( aggrFunction '(' scalarExpr ')' -> ^( aggrFunction scalarExpr ) | percentileFunction )
            int alt45 = 2;
            int LA45_0 = input.LA(1);
            if ((LA45_0 == AVG || (LA45_0 >= COUNT && LA45_0 <= COUNT_DISTINCT) || LA45_0 == MAX || LA45_0 == MEDIAN || LA45_0 == MIN || LA45_0 == STD || LA45_0 == SUM)) {
                alt45 = 1;
            } else if ((LA45_0 == PERCENTILE)) {
                alt45 = 2;
            } else {
                NoViableAltException nvae =
                        new NoViableAltException("", 45, 0, input);
                throw nvae;
            }

            switch (alt45) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:932:3: aggrFunction '(' scalarExpr ')'
                {
                    pushFollow(FOLLOW_aggrFunction_in_aggrFunctionClause8664);
                    aggrFunction303 = aggrFunction();
                    state._fsp--;

                    stream_aggrFunction.add(aggrFunction303.getTree());
                    char_literal304 = (Token) match(input, 426, FOLLOW_426_in_aggrFunctionClause8666);
                    stream_426.add(char_literal304);

                    pushFollow(FOLLOW_scalarExpr_in_aggrFunctionClause8668);
                    scalarExpr305 = scalarExpr();
                    state._fsp--;

                    stream_scalarExpr.add(scalarExpr305.getTree());
                    char_literal306 = (Token) match(input, 427, FOLLOW_427_in_aggrFunctionClause8670);
                    stream_427.add(char_literal306);

                    // AST REWRITE
                    // elements: aggrFunction, scalarExpr
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 933:5: -> ^( aggrFunction scalarExpr )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:934:7: ^( aggrFunction scalarExpr )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot(stream_aggrFunction.nextNode(), root_1);
                            adaptor.addChild(root_1, stream_scalarExpr.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:935:5: percentileFunction
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_percentileFunction_in_aggrFunctionClause8694);
                    percentileFunction307 = percentileFunction();
                    state._fsp--;

                    adaptor.addChild(root_0, percentileFunction307.getTree());

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "datasetIDGroup"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:938:1: datasetIDGroup : varID ( ',' varID )* -> ( ^( varID ) )+ ;
    public final ValidationMlParser.datasetIDGroup_return datasetIDGroup() throws Exception {
        ValidationMlParser.datasetIDGroup_return retval = new ValidationMlParser.datasetIDGroup_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal309 = null;
        ParserRuleReturnScope varID308 = null;
        ParserRuleReturnScope varID310 = null;

        Object char_literal309_tree = null;
        RewriteRuleTokenStream stream_CARTESIAN_PER = new RewriteRuleTokenStream(adaptor, "token CARTESIAN_PER");
        RewriteRuleSubtreeStream stream_varID = new RewriteRuleSubtreeStream(adaptor, "rule varID");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:939:3: ( varID ( ',' varID )* -> ( ^( varID ) )+ )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:940:3: varID ( ',' varID )*
            {
                pushFollow(FOLLOW_varID_in_datasetIDGroup8709);
                varID308 = varID();
                state._fsp--;

                stream_varID.add(varID308.getTree());
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:940:9: ( ',' varID )*
                loop46:
                while (true) {
                    int alt46 = 2;
                    int LA46_0 = input.LA(1);
                    if ((LA46_0 == CARTESIAN_PER)) {
                        alt46 = 1;
                    }

                    switch (alt46) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:940:10: ',' varID
                        {
                            char_literal309 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_datasetIDGroup8712);
                            stream_CARTESIAN_PER.add(char_literal309);

                            pushFollow(FOLLOW_varID_in_datasetIDGroup8714);
                            varID310 = varID();
                            state._fsp--;

                            stream_varID.add(varID310.getTree());
                        }
                        break;

                        default:
                            break loop46;
                    }
                }

                // AST REWRITE
                // elements: varID
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                root_0 = (Object) adaptor.nil();
                // 941:5: -> ( ^( varID ) )+
                {
                    if (!(stream_varID.hasNext())) {
                        throw new RewriteEarlyExitException();
                    }
                    while (stream_varID.hasNext()) {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:942:7: ^( varID )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot(stream_varID.nextNode(), root_1);
                            adaptor.addChild(root_0, root_1);
                        }

                    }
                    stream_varID.reset();

                }


                retval.tree = root_0;

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "caseElseClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:945:1: caseElseClause : ( ( ',' ELSE exprAdd ) -> ^( DATASET_CASE_ELSE_TAG exprAdd ) ) ;
    public final ValidationMlParser.caseElseClause_return caseElseClause() throws Exception {
        ValidationMlParser.caseElseClause_return retval = new ValidationMlParser.caseElseClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal311 = null;
        Token ELSE312 = null;
        ParserRuleReturnScope exprAdd313 = null;

        Object char_literal311_tree = null;
        Object ELSE312_tree = null;
        RewriteRuleTokenStream stream_ELSE = new RewriteRuleTokenStream(adaptor, "token ELSE");
        RewriteRuleTokenStream stream_CARTESIAN_PER = new RewriteRuleTokenStream(adaptor, "token CARTESIAN_PER");
        RewriteRuleSubtreeStream stream_exprAdd = new RewriteRuleSubtreeStream(adaptor, "rule exprAdd");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:946:3: ( ( ( ',' ELSE exprAdd ) -> ^( DATASET_CASE_ELSE_TAG exprAdd ) ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:947:3: ( ( ',' ELSE exprAdd ) -> ^( DATASET_CASE_ELSE_TAG exprAdd ) )
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:947:3: ( ( ',' ELSE exprAdd ) -> ^( DATASET_CASE_ELSE_TAG exprAdd ) )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:948:5: ( ',' ELSE exprAdd )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:948:5: ( ',' ELSE exprAdd )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:948:6: ',' ELSE exprAdd
                    {
                        char_literal311 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_caseElseClause8755);
                        stream_CARTESIAN_PER.add(char_literal311);

                        ELSE312 = (Token) match(input, ELSE, FOLLOW_ELSE_in_caseElseClause8757);
                        stream_ELSE.add(ELSE312);

                        pushFollow(FOLLOW_exprAdd_in_caseElseClause8759);
                        exprAdd313 = exprAdd();
                        state._fsp--;

                        stream_exprAdd.add(exprAdd313.getTree());
                    }

                    // AST REWRITE
                    // elements: exprAdd
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 949:7: -> ^( DATASET_CASE_ELSE_TAG exprAdd )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:950:9: ^( DATASET_CASE_ELSE_TAG exprAdd )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_CASE_ELSE_TAG, "DATASET_CASE_ELSE_TAG"), root_1);
                            adaptor.addChild(root_1, stream_exprAdd.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "caseCaseClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:954:1: caseCaseClause : ( ( exprOr ',' exprOr ( ',' exprOr ',' exprOr )* ) -> ( ^( DATASET_CASE_IF_TAG exprOr exprOr ) )+ ) ;
    public final ValidationMlParser.caseCaseClause_return caseCaseClause() throws Exception {
        ValidationMlParser.caseCaseClause_return retval = new ValidationMlParser.caseCaseClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal315 = null;
        Token char_literal317 = null;
        Token char_literal319 = null;
        ParserRuleReturnScope exprOr314 = null;
        ParserRuleReturnScope exprOr316 = null;
        ParserRuleReturnScope exprOr318 = null;
        ParserRuleReturnScope exprOr320 = null;

        Object char_literal315_tree = null;
        Object char_literal317_tree = null;
        Object char_literal319_tree = null;
        RewriteRuleTokenStream stream_CARTESIAN_PER = new RewriteRuleTokenStream(adaptor, "token CARTESIAN_PER");
        RewriteRuleSubtreeStream stream_exprOr = new RewriteRuleSubtreeStream(adaptor, "rule exprOr");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:955:3: ( ( ( exprOr ',' exprOr ( ',' exprOr ',' exprOr )* ) -> ( ^( DATASET_CASE_IF_TAG exprOr exprOr ) )+ ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:956:3: ( ( exprOr ',' exprOr ( ',' exprOr ',' exprOr )* ) -> ( ^( DATASET_CASE_IF_TAG exprOr exprOr ) )+ )
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:956:3: ( ( exprOr ',' exprOr ( ',' exprOr ',' exprOr )* ) -> ( ^( DATASET_CASE_IF_TAG exprOr exprOr ) )+ )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:957:5: ( exprOr ',' exprOr ( ',' exprOr ',' exprOr )* )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:957:5: ( exprOr ',' exprOr ( ',' exprOr ',' exprOr )* )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:957:6: exprOr ',' exprOr ( ',' exprOr ',' exprOr )*
                    {
                        pushFollow(FOLLOW_exprOr_in_caseCaseClause8808);
                        exprOr314 = exprOr();
                        state._fsp--;

                        stream_exprOr.add(exprOr314.getTree());
                        char_literal315 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_caseCaseClause8810);
                        stream_CARTESIAN_PER.add(char_literal315);

                        pushFollow(FOLLOW_exprOr_in_caseCaseClause8812);
                        exprOr316 = exprOr();
                        state._fsp--;

                        stream_exprOr.add(exprOr316.getTree());
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:957:24: ( ',' exprOr ',' exprOr )*
                        loop47:
                        while (true) {
                            int alt47 = 2;
                            int LA47_0 = input.LA(1);
                            if ((LA47_0 == CARTESIAN_PER)) {
                                alt47 = 1;
                            }

                            switch (alt47) {
                                case 1:
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:957:25: ',' exprOr ',' exprOr
                                {
                                    char_literal317 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_caseCaseClause8815);
                                    stream_CARTESIAN_PER.add(char_literal317);

                                    pushFollow(FOLLOW_exprOr_in_caseCaseClause8817);
                                    exprOr318 = exprOr();
                                    state._fsp--;

                                    stream_exprOr.add(exprOr318.getTree());
                                    char_literal319 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_caseCaseClause8819);
                                    stream_CARTESIAN_PER.add(char_literal319);

                                    pushFollow(FOLLOW_exprOr_in_caseCaseClause8821);
                                    exprOr320 = exprOr();
                                    state._fsp--;

                                    stream_exprOr.add(exprOr320.getTree());
                                }
                                break;

                                default:
                                    break loop47;
                            }
                        }

                    }

                    // AST REWRITE
                    // elements: exprOr, exprOr
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 958:7: -> ( ^( DATASET_CASE_IF_TAG exprOr exprOr ) )+
                    {
                        if (!(stream_exprOr.hasNext() || stream_exprOr.hasNext())) {
                            throw new RewriteEarlyExitException();
                        }
                        while (stream_exprOr.hasNext() || stream_exprOr.hasNext()) {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:959:9: ^( DATASET_CASE_IF_TAG exprOr exprOr )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_CASE_IF_TAG, "DATASET_CASE_IF_TAG"), root_1);
                                adaptor.addChild(root_1, stream_exprOr.nextTree());
                                adaptor.addChild(root_1, stream_exprOr.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }
                        stream_exprOr.reset();
                        stream_exprOr.reset();

                    }


                    retval.tree = root_0;

                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "getFiltersClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:963:1: getFiltersClause : ( getFilterClause ( ',' getFilterClause )* -> ( ^( getFilterClause ) )+ ) ;
    public final ValidationMlParser.getFiltersClause_return getFiltersClause() throws Exception {
        ValidationMlParser.getFiltersClause_return retval = new ValidationMlParser.getFiltersClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal322 = null;
        ParserRuleReturnScope getFilterClause321 = null;
        ParserRuleReturnScope getFilterClause323 = null;

        Object char_literal322_tree = null;
        RewriteRuleTokenStream stream_CARTESIAN_PER = new RewriteRuleTokenStream(adaptor, "token CARTESIAN_PER");
        RewriteRuleSubtreeStream stream_getFilterClause = new RewriteRuleSubtreeStream(adaptor, "rule getFilterClause");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:964:3: ( ( getFilterClause ( ',' getFilterClause )* -> ( ^( getFilterClause ) )+ ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:965:3: ( getFilterClause ( ',' getFilterClause )* -> ( ^( getFilterClause ) )+ )
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:965:3: ( getFilterClause ( ',' getFilterClause )* -> ( ^( getFilterClause ) )+ )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:966:5: getFilterClause ( ',' getFilterClause )*
                {
                    pushFollow(FOLLOW_getFilterClause_in_getFiltersClause8874);
                    getFilterClause321 = getFilterClause();
                    state._fsp--;

                    stream_getFilterClause.add(getFilterClause321.getTree());
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:966:21: ( ',' getFilterClause )*
                    loop48:
                    while (true) {
                        int alt48 = 2;
                        int LA48_0 = input.LA(1);
                        if ((LA48_0 == CARTESIAN_PER)) {
                            alt48 = 1;
                        }

                        switch (alt48) {
                            case 1:
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:966:22: ',' getFilterClause
                            {
                                char_literal322 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_getFiltersClause8877);
                                stream_CARTESIAN_PER.add(char_literal322);

                                pushFollow(FOLLOW_getFilterClause_in_getFiltersClause8879);
                                getFilterClause323 = getFilterClause();
                                state._fsp--;

                                stream_getFilterClause.add(getFilterClause323.getTree());
                            }
                            break;

                            default:
                                break loop48;
                        }
                    }

                    // AST REWRITE
                    // elements: getFilterClause
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 967:7: -> ( ^( getFilterClause ) )+
                    {
                        if (!(stream_getFilterClause.hasNext())) {
                            throw new RewriteEarlyExitException();
                        }
                        while (stream_getFilterClause.hasNext()) {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:968:9: ^( getFilterClause )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot(stream_getFilterClause.nextNode(), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }
                        stream_getFilterClause.reset();

                    }


                    retval.tree = root_0;

                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "getFilterClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:972:1: getFilterClause : ( ( ( FILTER )? b1= bScalarExpr ) -> ^( DATASET_GET_FILTER_CLAUSE_TAG $b1) ) ;
    public final ValidationMlParser.getFilterClause_return getFilterClause() throws Exception {
        ValidationMlParser.getFilterClause_return retval = new ValidationMlParser.getFilterClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token FILTER324 = null;
        ParserRuleReturnScope b1 = null;

        Object FILTER324_tree = null;
        RewriteRuleTokenStream stream_FILTER = new RewriteRuleTokenStream(adaptor, "token FILTER");
        RewriteRuleSubtreeStream stream_bScalarExpr = new RewriteRuleSubtreeStream(adaptor, "rule bScalarExpr");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:973:3: ( ( ( ( FILTER )? b1= bScalarExpr ) -> ^( DATASET_GET_FILTER_CLAUSE_TAG $b1) ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:974:3: ( ( ( FILTER )? b1= bScalarExpr ) -> ^( DATASET_GET_FILTER_CLAUSE_TAG $b1) )
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:974:3: ( ( ( FILTER )? b1= bScalarExpr ) -> ^( DATASET_GET_FILTER_CLAUSE_TAG $b1) )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:975:5: ( ( FILTER )? b1= bScalarExpr )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:975:5: ( ( FILTER )? b1= bScalarExpr )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:975:6: ( FILTER )? b1= bScalarExpr
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:975:6: ( FILTER )?
                        int alt49 = 2;
                        int LA49_0 = input.LA(1);
                        if ((LA49_0 == FILTER)) {
                            alt49 = 1;
                        }
                        switch (alt49) {
                            case 1:
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:975:6: FILTER
                            {
                                FILTER324 = (Token) match(input, FILTER, FOLLOW_FILTER_in_getFilterClause8928);
                                stream_FILTER.add(FILTER324);

                            }
                            break;

                        }

                        pushFollow(FOLLOW_bScalarExpr_in_getFilterClause8933);
                        b1 = bScalarExpr();
                        state._fsp--;

                        stream_bScalarExpr.add(b1.getTree());
                    }

                    // AST REWRITE
                    // elements: b1
                    // token labels:
                    // rule labels: retval, b1
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                    RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 976:7: -> ^( DATASET_GET_FILTER_CLAUSE_TAG $b1)
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:977:9: ^( DATASET_GET_FILTER_CLAUSE_TAG $b1)
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_GET_FILTER_CLAUSE_TAG, "DATASET_GET_FILTER_CLAUSE_TAG"), root_1);
                            adaptor.addChild(root_1, stream_b1.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "datasetClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:981:1: datasetClause : ( ( RENAME renameClause -> ^( DATASET_RENAME_CLAUSE_TAG renameClause ) ) | aggrFilterClause | ( calcClause -> ^( DATASET_CALC_CLAUSE_TAG calcClause ) ) | ( attrCalcClause -> ^( DATASET_CALC_CLAUSE_TAG attrCalcClause ) ) | ( keepClause -> ^( DATASET_KEEP_CLAUSE_TAG keepClause ) ) | ( dropClause -> ^( DATASET_DROP_CLAUSE_TAG dropClause ) ) | ( compareClause -> ^( DATASET_COMPARE_TAG compareClause ) ) );
    public final ValidationMlParser.datasetClause_return datasetClause() throws Exception {
        ValidationMlParser.datasetClause_return retval = new ValidationMlParser.datasetClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token RENAME325 = null;
        ParserRuleReturnScope renameClause326 = null;
        ParserRuleReturnScope aggrFilterClause327 = null;
        ParserRuleReturnScope calcClause328 = null;
        ParserRuleReturnScope attrCalcClause329 = null;
        ParserRuleReturnScope keepClause330 = null;
        ParserRuleReturnScope dropClause331 = null;
        ParserRuleReturnScope compareClause332 = null;

        Object RENAME325_tree = null;
        RewriteRuleTokenStream stream_RENAME = new RewriteRuleTokenStream(adaptor, "token RENAME");
        RewriteRuleSubtreeStream stream_renameClause = new RewriteRuleSubtreeStream(adaptor, "rule renameClause");
        RewriteRuleSubtreeStream stream_keepClause = new RewriteRuleSubtreeStream(adaptor, "rule keepClause");
        RewriteRuleSubtreeStream stream_attrCalcClause = new RewriteRuleSubtreeStream(adaptor, "rule attrCalcClause");
        RewriteRuleSubtreeStream stream_dropClause = new RewriteRuleSubtreeStream(adaptor, "rule dropClause");
        RewriteRuleSubtreeStream stream_calcClause = new RewriteRuleSubtreeStream(adaptor, "rule calcClause");
        RewriteRuleSubtreeStream stream_compareClause = new RewriteRuleSubtreeStream(adaptor, "rule compareClause");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:982:3: ( ( RENAME renameClause -> ^( DATASET_RENAME_CLAUSE_TAG renameClause ) ) | aggrFilterClause | ( calcClause -> ^( DATASET_CALC_CLAUSE_TAG calcClause ) ) | ( attrCalcClause -> ^( DATASET_CALC_CLAUSE_TAG attrCalcClause ) ) | ( keepClause -> ^( DATASET_KEEP_CLAUSE_TAG keepClause ) ) | ( dropClause -> ^( DATASET_DROP_CLAUSE_TAG dropClause ) ) | ( compareClause -> ^( DATASET_COMPARE_TAG compareClause ) ) )
            int alt50 = 7;
            alt50 = dfa50.predict(input);
            switch (alt50) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:983:3: ( RENAME renameClause -> ^( DATASET_RENAME_CLAUSE_TAG renameClause ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:983:3: ( RENAME renameClause -> ^( DATASET_RENAME_CLAUSE_TAG renameClause ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:983:4: RENAME renameClause
                    {
                        RENAME325 = (Token) match(input, RENAME, FOLLOW_RENAME_in_datasetClause8977);
                        stream_RENAME.add(RENAME325);

                        pushFollow(FOLLOW_renameClause_in_datasetClause8979);
                        renameClause326 = renameClause();
                        state._fsp--;

                        stream_renameClause.add(renameClause326.getTree());
                        // AST REWRITE
                        // elements: renameClause
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 984:7: -> ^( DATASET_RENAME_CLAUSE_TAG renameClause )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:985:9: ^( DATASET_RENAME_CLAUSE_TAG renameClause )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_RENAME_CLAUSE_TAG, "DATASET_RENAME_CLAUSE_TAG"), root_1);
                                adaptor.addChild(root_1, stream_renameClause.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:986:5: aggrFilterClause
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_aggrFilterClause_in_datasetClause9008);
                    aggrFilterClause327 = aggrFilterClause();
                    state._fsp--;

                    adaptor.addChild(root_0, aggrFilterClause327.getTree());

                }
                break;
                case 3:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:987:5: ( calcClause -> ^( DATASET_CALC_CLAUSE_TAG calcClause ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:987:5: ( calcClause -> ^( DATASET_CALC_CLAUSE_TAG calcClause ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:987:6: calcClause
                    {
                        pushFollow(FOLLOW_calcClause_in_datasetClause9015);
                        calcClause328 = calcClause();
                        state._fsp--;

                        stream_calcClause.add(calcClause328.getTree());
                        // AST REWRITE
                        // elements: calcClause
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 988:7: -> ^( DATASET_CALC_CLAUSE_TAG calcClause )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:989:9: ^( DATASET_CALC_CLAUSE_TAG calcClause )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_CALC_CLAUSE_TAG, "DATASET_CALC_CLAUSE_TAG"), root_1);
                                adaptor.addChild(root_1, stream_calcClause.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 4:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:990:5: ( attrCalcClause -> ^( DATASET_CALC_CLAUSE_TAG attrCalcClause ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:990:5: ( attrCalcClause -> ^( DATASET_CALC_CLAUSE_TAG attrCalcClause ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:990:6: attrCalcClause
                    {
                        pushFollow(FOLLOW_attrCalcClause_in_datasetClause9045);
                        attrCalcClause329 = attrCalcClause();
                        state._fsp--;

                        stream_attrCalcClause.add(attrCalcClause329.getTree());
                        // AST REWRITE
                        // elements: attrCalcClause
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 991:7: -> ^( DATASET_CALC_CLAUSE_TAG attrCalcClause )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:992:9: ^( DATASET_CALC_CLAUSE_TAG attrCalcClause )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_CALC_CLAUSE_TAG, "DATASET_CALC_CLAUSE_TAG"), root_1);
                                adaptor.addChild(root_1, stream_attrCalcClause.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 5:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:993:5: ( keepClause -> ^( DATASET_KEEP_CLAUSE_TAG keepClause ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:993:5: ( keepClause -> ^( DATASET_KEEP_CLAUSE_TAG keepClause ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:993:6: keepClause
                    {
                        pushFollow(FOLLOW_keepClause_in_datasetClause9075);
                        keepClause330 = keepClause();
                        state._fsp--;

                        stream_keepClause.add(keepClause330.getTree());
                        // AST REWRITE
                        // elements: keepClause
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 994:7: -> ^( DATASET_KEEP_CLAUSE_TAG keepClause )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:995:9: ^( DATASET_KEEP_CLAUSE_TAG keepClause )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_KEEP_CLAUSE_TAG, "DATASET_KEEP_CLAUSE_TAG"), root_1);
                                adaptor.addChild(root_1, stream_keepClause.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 6:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:996:5: ( dropClause -> ^( DATASET_DROP_CLAUSE_TAG dropClause ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:996:5: ( dropClause -> ^( DATASET_DROP_CLAUSE_TAG dropClause ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:996:6: dropClause
                    {
                        pushFollow(FOLLOW_dropClause_in_datasetClause9105);
                        dropClause331 = dropClause();
                        state._fsp--;

                        stream_dropClause.add(dropClause331.getTree());
                        // AST REWRITE
                        // elements: dropClause
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 997:7: -> ^( DATASET_DROP_CLAUSE_TAG dropClause )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:998:9: ^( DATASET_DROP_CLAUSE_TAG dropClause )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_DROP_CLAUSE_TAG, "DATASET_DROP_CLAUSE_TAG"), root_1);
                                adaptor.addChild(root_1, stream_dropClause.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 7:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:999:5: ( compareClause -> ^( DATASET_COMPARE_TAG compareClause ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:999:5: ( compareClause -> ^( DATASET_COMPARE_TAG compareClause ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:999:6: compareClause
                    {
                        pushFollow(FOLLOW_compareClause_in_datasetClause9135);
                        compareClause332 = compareClause();
                        state._fsp--;

                        stream_compareClause.add(compareClause332.getTree());
                        // AST REWRITE
                        // elements: compareClause
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1000:7: -> ^( DATASET_COMPARE_TAG compareClause )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1001:9: ^( DATASET_COMPARE_TAG compareClause )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_COMPARE_TAG, "DATASET_COMPARE_TAG"), root_1);
                                adaptor.addChild(root_1, stream_compareClause.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "aggrFilterClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1004:1: aggrFilterClause : (| ( filterClause ( ',' keepClause ',' AGGREGATE aggregateClause )? -> ^( DATASET_KEEP_CLAUSE_TAG filterClause ( keepClause )? ( aggregateClause )? ) ) | ( ( keepClause | dropClause ) ',' AGGREGATE aggregateClause -> ^( DATASET_KEEP_CLAUSE_TAG ( keepClause )? ( dropClause )? aggregateClause ) ) );
    public final ValidationMlParser.aggrFilterClause_return aggrFilterClause() throws Exception {
        ValidationMlParser.aggrFilterClause_return retval = new ValidationMlParser.aggrFilterClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal334 = null;
        Token char_literal336 = null;
        Token AGGREGATE337 = null;
        Token char_literal341 = null;
        Token AGGREGATE342 = null;
        ParserRuleReturnScope filterClause333 = null;
        ParserRuleReturnScope keepClause335 = null;
        ParserRuleReturnScope aggregateClause338 = null;
        ParserRuleReturnScope keepClause339 = null;
        ParserRuleReturnScope dropClause340 = null;
        ParserRuleReturnScope aggregateClause343 = null;

        Object char_literal334_tree = null;
        Object char_literal336_tree = null;
        Object AGGREGATE337_tree = null;
        Object char_literal341_tree = null;
        Object AGGREGATE342_tree = null;
        RewriteRuleTokenStream stream_AGGREGATE = new RewriteRuleTokenStream(adaptor, "token AGGREGATE");
        RewriteRuleTokenStream stream_CARTESIAN_PER = new RewriteRuleTokenStream(adaptor, "token CARTESIAN_PER");
        RewriteRuleSubtreeStream stream_filterClause = new RewriteRuleSubtreeStream(adaptor, "rule filterClause");
        RewriteRuleSubtreeStream stream_keepClause = new RewriteRuleSubtreeStream(adaptor, "rule keepClause");
        RewriteRuleSubtreeStream stream_dropClause = new RewriteRuleSubtreeStream(adaptor, "rule dropClause");
        RewriteRuleSubtreeStream stream_aggregateClause = new RewriteRuleSubtreeStream(adaptor, "rule aggregateClause");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1005:3: (| ( filterClause ( ',' keepClause ',' AGGREGATE aggregateClause )? -> ^( DATASET_KEEP_CLAUSE_TAG filterClause ( keepClause )? ( aggregateClause )? ) ) | ( ( keepClause | dropClause ) ',' AGGREGATE aggregateClause -> ^( DATASET_KEEP_CLAUSE_TAG ( keepClause )? ( dropClause )? aggregateClause ) ) )
            int alt53 = 3;
            switch (input.LA(1)) {
                case 433: {
                    alt53 = 1;
                }
                break;
                case ABS:
                case BOOLEAN_CONSTANT:
                case CONCAT:
                case EXP:
                case FILTER:
                case FLOAT_CONSTANT:
                case IDENTIFIER:
                case INTEGER_CONSTANT:
                case LCASE:
                case LEN:
                case LN:
                case MINUS:
                case MOD:
                case NOT:
                case NULL_CONSTANT:
                case PLUS:
                case POWER:
                case ROUND:
                case STRING_CONSTANT:
                case SUBSTR:
                case TRIM:
                case TRUNC:
                case UCASE:
                case 426:
                case 430: {
                    alt53 = 2;
                }
                break;
                case DROP:
                case KEEP: {
                    alt53 = 3;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 53, 0, input);
                    throw nvae;
            }
            switch (alt53) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1006:3:
                {
                    root_0 = (Object) adaptor.nil();


                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1007:3: ( filterClause ( ',' keepClause ',' AGGREGATE aggregateClause )? -> ^( DATASET_KEEP_CLAUSE_TAG filterClause ( keepClause )? ( aggregateClause )? ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1007:3: ( filterClause ( ',' keepClause ',' AGGREGATE aggregateClause )? -> ^( DATASET_KEEP_CLAUSE_TAG filterClause ( keepClause )? ( aggregateClause )? ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1008:5: filterClause ( ',' keepClause ',' AGGREGATE aggregateClause )?
                    {
                        pushFollow(FOLLOW_filterClause_in_aggrFilterClause9183);
                        filterClause333 = filterClause();
                        state._fsp--;

                        stream_filterClause.add(filterClause333.getTree());
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1008:18: ( ',' keepClause ',' AGGREGATE aggregateClause )?
                        int alt51 = 2;
                        int LA51_0 = input.LA(1);
                        if ((LA51_0 == CARTESIAN_PER)) {
                            alt51 = 1;
                        }
                        switch (alt51) {
                            case 1:
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1008:19: ',' keepClause ',' AGGREGATE aggregateClause
                            {
                                char_literal334 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_aggrFilterClause9186);
                                stream_CARTESIAN_PER.add(char_literal334);

                                pushFollow(FOLLOW_keepClause_in_aggrFilterClause9188);
                                keepClause335 = keepClause();
                                state._fsp--;

                                stream_keepClause.add(keepClause335.getTree());
                                char_literal336 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_aggrFilterClause9190);
                                stream_CARTESIAN_PER.add(char_literal336);

                                AGGREGATE337 = (Token) match(input, AGGREGATE, FOLLOW_AGGREGATE_in_aggrFilterClause9192);
                                stream_AGGREGATE.add(AGGREGATE337);

                                pushFollow(FOLLOW_aggregateClause_in_aggrFilterClause9194);
                                aggregateClause338 = aggregateClause();
                                state._fsp--;

                                stream_aggregateClause.add(aggregateClause338.getTree());
                            }
                            break;

                        }

                        // AST REWRITE
                        // elements: keepClause, aggregateClause, filterClause
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1009:7: -> ^( DATASET_KEEP_CLAUSE_TAG filterClause ( keepClause )? ( aggregateClause )? )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1010:9: ^( DATASET_KEEP_CLAUSE_TAG filterClause ( keepClause )? ( aggregateClause )? )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_KEEP_CLAUSE_TAG, "DATASET_KEEP_CLAUSE_TAG"), root_1);
                                adaptor.addChild(root_1, stream_filterClause.nextTree());
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1010:48: ( keepClause )?
                                if (stream_keepClause.hasNext()) {
                                    adaptor.addChild(root_1, stream_keepClause.nextTree());
                                }
                                stream_keepClause.reset();

                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1010:60: ( aggregateClause )?
                                if (stream_aggregateClause.hasNext()) {
                                    adaptor.addChild(root_1, stream_aggregateClause.nextTree());
                                }
                                stream_aggregateClause.reset();

                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 3:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1013:3: ( ( keepClause | dropClause ) ',' AGGREGATE aggregateClause -> ^( DATASET_KEEP_CLAUSE_TAG ( keepClause )? ( dropClause )? aggregateClause ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1013:3: ( ( keepClause | dropClause ) ',' AGGREGATE aggregateClause -> ^( DATASET_KEEP_CLAUSE_TAG ( keepClause )? ( dropClause )? aggregateClause ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1014:5: ( keepClause | dropClause ) ',' AGGREGATE aggregateClause
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1014:5: ( keepClause | dropClause )
                        int alt52 = 2;
                        int LA52_0 = input.LA(1);
                        if ((LA52_0 == KEEP)) {
                            alt52 = 1;
                        } else if ((LA52_0 == DROP)) {
                            alt52 = 2;
                        } else {
                            NoViableAltException nvae =
                                    new NoViableAltException("", 52, 0, input);
                            throw nvae;
                        }

                        switch (alt52) {
                            case 1:
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1015:7: keepClause
                            {
                                pushFollow(FOLLOW_keepClause_in_aggrFilterClause9250);
                                keepClause339 = keepClause();
                                state._fsp--;

                                stream_keepClause.add(keepClause339.getTree());
                            }
                            break;
                            case 2:
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1016:9: dropClause
                            {
                                pushFollow(FOLLOW_dropClause_in_aggrFilterClause9260);
                                dropClause340 = dropClause();
                                state._fsp--;

                                stream_dropClause.add(dropClause340.getTree());
                            }
                            break;

                        }

                        char_literal341 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_aggrFilterClause9272);
                        stream_CARTESIAN_PER.add(char_literal341);

                        AGGREGATE342 = (Token) match(input, AGGREGATE, FOLLOW_AGGREGATE_in_aggrFilterClause9274);
                        stream_AGGREGATE.add(AGGREGATE342);

                        pushFollow(FOLLOW_aggregateClause_in_aggrFilterClause9276);
                        aggregateClause343 = aggregateClause();
                        state._fsp--;

                        stream_aggregateClause.add(aggregateClause343.getTree());
                        // AST REWRITE
                        // elements: aggregateClause, keepClause, dropClause
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1019:7: -> ^( DATASET_KEEP_CLAUSE_TAG ( keepClause )? ( dropClause )? aggregateClause )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1020:9: ^( DATASET_KEEP_CLAUSE_TAG ( keepClause )? ( dropClause )? aggregateClause )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_KEEP_CLAUSE_TAG, "DATASET_KEEP_CLAUSE_TAG"), root_1);
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1020:35: ( keepClause )?
                                if (stream_keepClause.hasNext()) {
                                    adaptor.addChild(root_1, stream_keepClause.nextTree());
                                }
                                stream_keepClause.reset();

                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1020:47: ( dropClause )?
                                if (stream_dropClause.hasNext()) {
                                    adaptor.addChild(root_1, stream_dropClause.nextTree());
                                }
                                stream_dropClause.reset();

                                adaptor.addChild(root_1, stream_aggregateClause.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "filterClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1024:1: filterClause : ( ( ( FILTER )? b1= bScalarExpr ) -> ^( DATASET_FILTER_CLAUSE_TAG $b1) ) ;
    public final ValidationMlParser.filterClause_return filterClause() throws Exception {
        ValidationMlParser.filterClause_return retval = new ValidationMlParser.filterClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token FILTER344 = null;
        ParserRuleReturnScope b1 = null;

        Object FILTER344_tree = null;
        RewriteRuleTokenStream stream_FILTER = new RewriteRuleTokenStream(adaptor, "token FILTER");
        RewriteRuleSubtreeStream stream_bScalarExpr = new RewriteRuleSubtreeStream(adaptor, "rule bScalarExpr");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1025:3: ( ( ( ( FILTER )? b1= bScalarExpr ) -> ^( DATASET_FILTER_CLAUSE_TAG $b1) ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1026:3: ( ( ( FILTER )? b1= bScalarExpr ) -> ^( DATASET_FILTER_CLAUSE_TAG $b1) )
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1026:3: ( ( ( FILTER )? b1= bScalarExpr ) -> ^( DATASET_FILTER_CLAUSE_TAG $b1) )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1027:5: ( ( FILTER )? b1= bScalarExpr )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1027:5: ( ( FILTER )? b1= bScalarExpr )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1027:6: ( FILTER )? b1= bScalarExpr
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1027:6: ( FILTER )?
                        int alt54 = 2;
                        int LA54_0 = input.LA(1);
                        if ((LA54_0 == FILTER)) {
                            alt54 = 1;
                        }
                        switch (alt54) {
                            case 1:
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1027:6: FILTER
                            {
                                FILTER344 = (Token) match(input, FILTER, FOLLOW_FILTER_in_filterClause9330);
                                stream_FILTER.add(FILTER344);

                            }
                            break;

                        }

                        pushFollow(FOLLOW_bScalarExpr_in_filterClause9335);
                        b1 = bScalarExpr();
                        state._fsp--;

                        stream_bScalarExpr.add(b1.getTree());
                    }

                    // AST REWRITE
                    // elements: b1
                    // token labels:
                    // rule labels: retval, b1
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                    RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1028:7: -> ^( DATASET_FILTER_CLAUSE_TAG $b1)
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1029:9: ^( DATASET_FILTER_CLAUSE_TAG $b1)
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_FILTER_CLAUSE_TAG, "DATASET_FILTER_CLAUSE_TAG"), root_1);
                            adaptor.addChild(root_1, stream_b1.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "ascdescClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1033:1: ascdescClause : ( ( ASC -> ORDERBY_ASC_TYPE_TAG ) | ( DESC -> ORDERBY_DESC_TYPE_TAG ) );
    public final ValidationMlParser.ascdescClause_return ascdescClause() throws Exception {
        ValidationMlParser.ascdescClause_return retval = new ValidationMlParser.ascdescClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ASC345 = null;
        Token DESC346 = null;

        Object ASC345_tree = null;
        Object DESC346_tree = null;
        RewriteRuleTokenStream stream_ASC = new RewriteRuleTokenStream(adaptor, "token ASC");
        RewriteRuleTokenStream stream_DESC = new RewriteRuleTokenStream(adaptor, "token DESC");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1034:3: ( ( ASC -> ORDERBY_ASC_TYPE_TAG ) | ( DESC -> ORDERBY_DESC_TYPE_TAG ) )
            int alt55 = 2;
            int LA55_0 = input.LA(1);
            if ((LA55_0 == ASC)) {
                alt55 = 1;
            } else if ((LA55_0 == DESC)) {
                alt55 = 2;
            } else {
                NoViableAltException nvae =
                        new NoViableAltException("", 55, 0, input);
                throw nvae;
            }

            switch (alt55) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1035:3: ( ASC -> ORDERBY_ASC_TYPE_TAG )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1035:3: ( ASC -> ORDERBY_ASC_TYPE_TAG )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1035:4: ASC
                    {
                        ASC345 = (Token) match(input, ASC, FOLLOW_ASC_in_ascdescClause9379);
                        stream_ASC.add(ASC345);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1036:7: -> ORDERBY_ASC_TYPE_TAG
                        {
                            adaptor.addChild(root_0, (Object) adaptor.create(ORDERBY_ASC_TYPE_TAG, "ORDERBY_ASC_TYPE_TAG"));
                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1037:5: ( DESC -> ORDERBY_DESC_TYPE_TAG )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1037:5: ( DESC -> ORDERBY_DESC_TYPE_TAG )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1037:6: DESC
                    {
                        DESC346 = (Token) match(input, DESC, FOLLOW_DESC_in_ascdescClause9397);
                        stream_DESC.add(DESC346);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1038:7: -> ORDERBY_DESC_TYPE_TAG
                        {
                            adaptor.addChild(root_0, (Object) adaptor.create(ORDERBY_DESC_TYPE_TAG, "ORDERBY_DESC_TYPE_TAG"));
                        }


                        retval.tree = root_0;

                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "renameClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1041:1: renameClause : ( varID AS varID ( ROLE varRole )? ( ',' varID AS varID ( ROLE varRole )? )* -> ( ^( VAR_RENAME_AS_TAG varID varID ) )+ ) ;
    public final ValidationMlParser.renameClause_return renameClause() throws Exception {
        ValidationMlParser.renameClause_return retval = new ValidationMlParser.renameClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AS348 = null;
        Token ROLE350 = null;
        Token char_literal352 = null;
        Token AS354 = null;
        Token ROLE356 = null;
        ParserRuleReturnScope varID347 = null;
        ParserRuleReturnScope varID349 = null;
        ParserRuleReturnScope varRole351 = null;
        ParserRuleReturnScope varID353 = null;
        ParserRuleReturnScope varID355 = null;
        ParserRuleReturnScope varRole357 = null;

        Object AS348_tree = null;
        Object ROLE350_tree = null;
        Object char_literal352_tree = null;
        Object AS354_tree = null;
        Object ROLE356_tree = null;
        RewriteRuleTokenStream stream_ROLE = new RewriteRuleTokenStream(adaptor, "token ROLE");
        RewriteRuleTokenStream stream_AS = new RewriteRuleTokenStream(adaptor, "token AS");
        RewriteRuleTokenStream stream_CARTESIAN_PER = new RewriteRuleTokenStream(adaptor, "token CARTESIAN_PER");
        RewriteRuleSubtreeStream stream_varID = new RewriteRuleSubtreeStream(adaptor, "rule varID");
        RewriteRuleSubtreeStream stream_varRole = new RewriteRuleSubtreeStream(adaptor, "rule varRole");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1042:3: ( ( varID AS varID ( ROLE varRole )? ( ',' varID AS varID ( ROLE varRole )? )* -> ( ^( VAR_RENAME_AS_TAG varID varID ) )+ ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1043:3: ( varID AS varID ( ROLE varRole )? ( ',' varID AS varID ( ROLE varRole )? )* -> ( ^( VAR_RENAME_AS_TAG varID varID ) )+ )
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1043:3: ( varID AS varID ( ROLE varRole )? ( ',' varID AS varID ( ROLE varRole )? )* -> ( ^( VAR_RENAME_AS_TAG varID varID ) )+ )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1044:5: varID AS varID ( ROLE varRole )? ( ',' varID AS varID ( ROLE varRole )? )*
                {
                    pushFollow(FOLLOW_varID_in_renameClause9429);
                    varID347 = varID();
                    state._fsp--;

                    stream_varID.add(varID347.getTree());
                    AS348 = (Token) match(input, AS, FOLLOW_AS_in_renameClause9431);
                    stream_AS.add(AS348);

                    pushFollow(FOLLOW_varID_in_renameClause9433);
                    varID349 = varID();
                    state._fsp--;

                    stream_varID.add(varID349.getTree());
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1044:20: ( ROLE varRole )?
                    int alt56 = 2;
                    int LA56_0 = input.LA(1);
                    if ((LA56_0 == ROLE)) {
                        alt56 = 1;
                    }
                    switch (alt56) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1044:21: ROLE varRole
                        {
                            ROLE350 = (Token) match(input, ROLE, FOLLOW_ROLE_in_renameClause9436);
                            stream_ROLE.add(ROLE350);

                            pushFollow(FOLLOW_varRole_in_renameClause9438);
                            varRole351 = varRole();
                            state._fsp--;

                            stream_varRole.add(varRole351.getTree());
                        }
                        break;

                    }

                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1044:36: ( ',' varID AS varID ( ROLE varRole )? )*
                    loop58:
                    while (true) {
                        int alt58 = 2;
                        int LA58_0 = input.LA(1);
                        if ((LA58_0 == CARTESIAN_PER)) {
                            alt58 = 1;
                        }

                        switch (alt58) {
                            case 1:
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1044:37: ',' varID AS varID ( ROLE varRole )?
                            {
                                char_literal352 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_renameClause9443);
                                stream_CARTESIAN_PER.add(char_literal352);

                                pushFollow(FOLLOW_varID_in_renameClause9445);
                                varID353 = varID();
                                state._fsp--;

                                stream_varID.add(varID353.getTree());
                                AS354 = (Token) match(input, AS, FOLLOW_AS_in_renameClause9447);
                                stream_AS.add(AS354);

                                pushFollow(FOLLOW_varID_in_renameClause9449);
                                varID355 = varID();
                                state._fsp--;

                                stream_varID.add(varID355.getTree());
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1044:56: ( ROLE varRole )?
                                int alt57 = 2;
                                int LA57_0 = input.LA(1);
                                if ((LA57_0 == ROLE)) {
                                    alt57 = 1;
                                }
                                switch (alt57) {
                                    case 1:
                                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1044:57: ROLE varRole
                                    {
                                        ROLE356 = (Token) match(input, ROLE, FOLLOW_ROLE_in_renameClause9452);
                                        stream_ROLE.add(ROLE356);

                                        pushFollow(FOLLOW_varRole_in_renameClause9454);
                                        varRole357 = varRole();
                                        state._fsp--;

                                        stream_varRole.add(varRole357.getTree());
                                    }
                                    break;

                                }

                            }
                            break;

                            default:
                                break loop58;
                        }
                    }

                    // AST REWRITE
                    // elements: varID, varID
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1045:7: -> ( ^( VAR_RENAME_AS_TAG varID varID ) )+
                    {
                        if (!(stream_varID.hasNext() || stream_varID.hasNext())) {
                            throw new RewriteEarlyExitException();
                        }
                        while (stream_varID.hasNext() || stream_varID.hasNext()) {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1046:9: ^( VAR_RENAME_AS_TAG varID varID )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(VAR_RENAME_AS_TAG, "VAR_RENAME_AS_TAG"), root_1);
                                adaptor.addChild(root_1, stream_varID.nextTree());
                                adaptor.addChild(root_1, stream_varID.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }
                        stream_varID.reset();
                        stream_varID.reset();

                    }


                    retval.tree = root_0;

                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "aggrFunction"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1050:1: aggrFunction : ( SUM -> AGGREGATE_SUM_TAG | AVG -> AGGREGATE_AVG_TAG | MIN -> AGGREGATE_MIN_TAG | MAX -> AGGREGATE_MAX_TAG | STD -> AGGREGATE_STD_TAG | COUNT -> AGGREGATE_COUNT_TAG | COUNT_DISTINCT -> AGGREGATE_COUNT_TAG | MEDIAN -> AGGREGATE_MEDIAN_TAG );
    public final ValidationMlParser.aggrFunction_return aggrFunction() throws Exception {
        ValidationMlParser.aggrFunction_return retval = new ValidationMlParser.aggrFunction_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SUM358 = null;
        Token AVG359 = null;
        Token MIN360 = null;
        Token MAX361 = null;
        Token STD362 = null;
        Token COUNT363 = null;
        Token COUNT_DISTINCT364 = null;
        Token MEDIAN365 = null;

        Object SUM358_tree = null;
        Object AVG359_tree = null;
        Object MIN360_tree = null;
        Object MAX361_tree = null;
        Object STD362_tree = null;
        Object COUNT363_tree = null;
        Object COUNT_DISTINCT364_tree = null;
        Object MEDIAN365_tree = null;
        RewriteRuleTokenStream stream_AVG = new RewriteRuleTokenStream(adaptor, "token AVG");
        RewriteRuleTokenStream stream_STD = new RewriteRuleTokenStream(adaptor, "token STD");
        RewriteRuleTokenStream stream_COUNT_DISTINCT = new RewriteRuleTokenStream(adaptor, "token COUNT_DISTINCT");
        RewriteRuleTokenStream stream_MIN = new RewriteRuleTokenStream(adaptor, "token MIN");
        RewriteRuleTokenStream stream_MAX = new RewriteRuleTokenStream(adaptor, "token MAX");
        RewriteRuleTokenStream stream_SUM = new RewriteRuleTokenStream(adaptor, "token SUM");
        RewriteRuleTokenStream stream_COUNT = new RewriteRuleTokenStream(adaptor, "token COUNT");
        RewriteRuleTokenStream stream_MEDIAN = new RewriteRuleTokenStream(adaptor, "token MEDIAN");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1051:3: ( SUM -> AGGREGATE_SUM_TAG | AVG -> AGGREGATE_AVG_TAG | MIN -> AGGREGATE_MIN_TAG | MAX -> AGGREGATE_MAX_TAG | STD -> AGGREGATE_STD_TAG | COUNT -> AGGREGATE_COUNT_TAG | COUNT_DISTINCT -> AGGREGATE_COUNT_TAG | MEDIAN -> AGGREGATE_MEDIAN_TAG )
            int alt59 = 8;
            switch (input.LA(1)) {
                case SUM: {
                    alt59 = 1;
                }
                break;
                case AVG: {
                    alt59 = 2;
                }
                break;
                case MIN: {
                    alt59 = 3;
                }
                break;
                case MAX: {
                    alt59 = 4;
                }
                break;
                case STD: {
                    alt59 = 5;
                }
                break;
                case COUNT: {
                    alt59 = 6;
                }
                break;
                case COUNT_DISTINCT: {
                    alt59 = 7;
                }
                break;
                case MEDIAN: {
                    alt59 = 8;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 59, 0, input);
                    throw nvae;
            }
            switch (alt59) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1052:3: SUM
                {
                    SUM358 = (Token) match(input, SUM, FOLLOW_SUM_in_aggrFunction9502);
                    stream_SUM.add(SUM358);

                    // AST REWRITE
                    // elements:
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1053:5: -> AGGREGATE_SUM_TAG
                    {
                        adaptor.addChild(root_0, (Object) adaptor.create(AGGREGATE_SUM_TAG, "AGGREGATE_SUM_TAG"));
                    }


                    retval.tree = root_0;

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1054:5: AVG
                {
                    AVG359 = (Token) match(input, AVG, FOLLOW_AVG_in_aggrFunction9516);
                    stream_AVG.add(AVG359);

                    // AST REWRITE
                    // elements:
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1055:5: -> AGGREGATE_AVG_TAG
                    {
                        adaptor.addChild(root_0, (Object) adaptor.create(AGGREGATE_AVG_TAG, "AGGREGATE_AVG_TAG"));
                    }


                    retval.tree = root_0;

                }
                break;
                case 3:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1056:5: MIN
                {
                    MIN360 = (Token) match(input, MIN, FOLLOW_MIN_in_aggrFunction9530);
                    stream_MIN.add(MIN360);

                    // AST REWRITE
                    // elements:
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1057:5: -> AGGREGATE_MIN_TAG
                    {
                        adaptor.addChild(root_0, (Object) adaptor.create(AGGREGATE_MIN_TAG, "AGGREGATE_MIN_TAG"));
                    }


                    retval.tree = root_0;

                }
                break;
                case 4:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1058:5: MAX
                {
                    MAX361 = (Token) match(input, MAX, FOLLOW_MAX_in_aggrFunction9544);
                    stream_MAX.add(MAX361);

                    // AST REWRITE
                    // elements:
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1059:5: -> AGGREGATE_MAX_TAG
                    {
                        adaptor.addChild(root_0, (Object) adaptor.create(AGGREGATE_MAX_TAG, "AGGREGATE_MAX_TAG"));
                    }


                    retval.tree = root_0;

                }
                break;
                case 5:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1060:5: STD
                {
                    STD362 = (Token) match(input, STD, FOLLOW_STD_in_aggrFunction9558);
                    stream_STD.add(STD362);

                    // AST REWRITE
                    // elements:
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1061:5: -> AGGREGATE_STD_TAG
                    {
                        adaptor.addChild(root_0, (Object) adaptor.create(AGGREGATE_STD_TAG, "AGGREGATE_STD_TAG"));
                    }


                    retval.tree = root_0;

                }
                break;
                case 6:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1062:5: COUNT
                {
                    COUNT363 = (Token) match(input, COUNT, FOLLOW_COUNT_in_aggrFunction9572);
                    stream_COUNT.add(COUNT363);

                    // AST REWRITE
                    // elements:
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1063:5: -> AGGREGATE_COUNT_TAG
                    {
                        adaptor.addChild(root_0, (Object) adaptor.create(AGGREGATE_COUNT_TAG, "AGGREGATE_COUNT_TAG"));
                    }


                    retval.tree = root_0;

                }
                break;
                case 7:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1064:5: COUNT_DISTINCT
                {
                    COUNT_DISTINCT364 = (Token) match(input, COUNT_DISTINCT, FOLLOW_COUNT_DISTINCT_in_aggrFunction9586);
                    stream_COUNT_DISTINCT.add(COUNT_DISTINCT364);

                    // AST REWRITE
                    // elements:
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1065:5: -> AGGREGATE_COUNT_TAG
                    {
                        adaptor.addChild(root_0, (Object) adaptor.create(AGGREGATE_COUNT_TAG, "AGGREGATE_COUNT_TAG"));
                    }


                    retval.tree = root_0;

                }
                break;
                case 8:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1066:5: MEDIAN
                {
                    MEDIAN365 = (Token) match(input, MEDIAN, FOLLOW_MEDIAN_in_aggrFunction9600);
                    stream_MEDIAN.add(MEDIAN365);

                    // AST REWRITE
                    // elements:
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1067:5: -> AGGREGATE_MEDIAN_TAG
                    {
                        adaptor.addChild(root_0, (Object) adaptor.create(AGGREGATE_MEDIAN_TAG, "AGGREGATE_MEDIAN_TAG"));
                    }


                    retval.tree = root_0;

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "percentileFunction"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1070:1: percentileFunction : ( PERCENTILE '(' a1= scalarExpr ',' a2= constant ')' -> ^( AGGREGATE_PERCENTILE_TAG $a1 $a2) ) ;
    public final ValidationMlParser.percentileFunction_return percentileFunction() throws Exception {
        ValidationMlParser.percentileFunction_return retval = new ValidationMlParser.percentileFunction_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PERCENTILE366 = null;
        Token char_literal367 = null;
        Token char_literal368 = null;
        Token char_literal369 = null;
        ParserRuleReturnScope a1 = null;
        ParserRuleReturnScope a2 = null;

        Object PERCENTILE366_tree = null;
        Object char_literal367_tree = null;
        Object char_literal368_tree = null;
        Object char_literal369_tree = null;
        RewriteRuleTokenStream stream_426 = new RewriteRuleTokenStream(adaptor, "token 426");
        RewriteRuleTokenStream stream_427 = new RewriteRuleTokenStream(adaptor, "token 427");
        RewriteRuleTokenStream stream_CARTESIAN_PER = new RewriteRuleTokenStream(adaptor, "token CARTESIAN_PER");
        RewriteRuleTokenStream stream_PERCENTILE = new RewriteRuleTokenStream(adaptor, "token PERCENTILE");
        RewriteRuleSubtreeStream stream_constant = new RewriteRuleSubtreeStream(adaptor, "rule constant");
        RewriteRuleSubtreeStream stream_scalarExpr = new RewriteRuleSubtreeStream(adaptor, "rule scalarExpr");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1071:3: ( ( PERCENTILE '(' a1= scalarExpr ',' a2= constant ')' -> ^( AGGREGATE_PERCENTILE_TAG $a1 $a2) ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1072:3: ( PERCENTILE '(' a1= scalarExpr ',' a2= constant ')' -> ^( AGGREGATE_PERCENTILE_TAG $a1 $a2) )
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1072:3: ( PERCENTILE '(' a1= scalarExpr ',' a2= constant ')' -> ^( AGGREGATE_PERCENTILE_TAG $a1 $a2) )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1072:4: PERCENTILE '(' a1= scalarExpr ',' a2= constant ')'
                {
                    PERCENTILE366 = (Token) match(input, PERCENTILE, FOLLOW_PERCENTILE_in_percentileFunction9624);
                    stream_PERCENTILE.add(PERCENTILE366);

                    char_literal367 = (Token) match(input, 426, FOLLOW_426_in_percentileFunction9626);
                    stream_426.add(char_literal367);

                    pushFollow(FOLLOW_scalarExpr_in_percentileFunction9630);
                    a1 = scalarExpr();
                    state._fsp--;

                    stream_scalarExpr.add(a1.getTree());
                    char_literal368 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_percentileFunction9632);
                    stream_CARTESIAN_PER.add(char_literal368);

                    pushFollow(FOLLOW_constant_in_percentileFunction9636);
                    a2 = constant();
                    state._fsp--;

                    stream_constant.add(a2.getTree());
                    char_literal369 = (Token) match(input, 427, FOLLOW_427_in_percentileFunction9638);
                    stream_427.add(char_literal369);

                    // AST REWRITE
                    // elements: a1, a2
                    // token labels:
                    // rule labels: a1, a2, retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_a1 = new RewriteRuleSubtreeStream(adaptor, "rule a1", a1 != null ? a1.getTree() : null);
                    RewriteRuleSubtreeStream stream_a2 = new RewriteRuleSubtreeStream(adaptor, "rule a2", a2 != null ? a2.getTree() : null);
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1073:7: -> ^( AGGREGATE_PERCENTILE_TAG $a1 $a2)
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1074:9: ^( AGGREGATE_PERCENTILE_TAG $a1 $a2)
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(AGGREGATE_PERCENTILE_TAG, "AGGREGATE_PERCENTILE_TAG"), root_1);
                            adaptor.addChild(root_1, stream_a1.nextTree());
                            adaptor.addChild(root_1, stream_a2.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "calcClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1077:1: calcClause : ( ( CALC ) calcClauseItem ( ',' calcClauseItem )* -> ( ^( calcClauseItem ) )+ ) ;
    public final ValidationMlParser.calcClause_return calcClause() throws Exception {
        ValidationMlParser.calcClause_return retval = new ValidationMlParser.calcClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CALC370 = null;
        Token char_literal372 = null;
        ParserRuleReturnScope calcClauseItem371 = null;
        ParserRuleReturnScope calcClauseItem373 = null;

        Object CALC370_tree = null;
        Object char_literal372_tree = null;
        RewriteRuleTokenStream stream_CARTESIAN_PER = new RewriteRuleTokenStream(adaptor, "token CARTESIAN_PER");
        RewriteRuleTokenStream stream_CALC = new RewriteRuleTokenStream(adaptor, "token CALC");
        RewriteRuleSubtreeStream stream_calcClauseItem = new RewriteRuleSubtreeStream(adaptor, "rule calcClauseItem");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1078:3: ( ( ( CALC ) calcClauseItem ( ',' calcClauseItem )* -> ( ^( calcClauseItem ) )+ ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1079:3: ( ( CALC ) calcClauseItem ( ',' calcClauseItem )* -> ( ^( calcClauseItem ) )+ )
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1079:3: ( ( CALC ) calcClauseItem ( ',' calcClauseItem )* -> ( ^( calcClauseItem ) )+ )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1080:5: ( CALC ) calcClauseItem ( ',' calcClauseItem )*
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1080:5: ( CALC )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1080:6: CALC
                    {
                        CALC370 = (Token) match(input, CALC, FOLLOW_CALC_in_calcClause9687);
                        stream_CALC.add(CALC370);

                    }

                    pushFollow(FOLLOW_calcClauseItem_in_calcClause9690);
                    calcClauseItem371 = calcClauseItem();
                    state._fsp--;

                    stream_calcClauseItem.add(calcClauseItem371.getTree());
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1080:27: ( ',' calcClauseItem )*
                    loop60:
                    while (true) {
                        int alt60 = 2;
                        int LA60_0 = input.LA(1);
                        if ((LA60_0 == CARTESIAN_PER)) {
                            alt60 = 1;
                        }

                        switch (alt60) {
                            case 1:
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1080:28: ',' calcClauseItem
                            {
                                char_literal372 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_calcClause9693);
                                stream_CARTESIAN_PER.add(char_literal372);

                                pushFollow(FOLLOW_calcClauseItem_in_calcClause9695);
                                calcClauseItem373 = calcClauseItem();
                                state._fsp--;

                                stream_calcClauseItem.add(calcClauseItem373.getTree());
                            }
                            break;

                            default:
                                break loop60;
                        }
                    }

                    // AST REWRITE
                    // elements: calcClauseItem
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1081:7: -> ( ^( calcClauseItem ) )+
                    {
                        if (!(stream_calcClauseItem.hasNext())) {
                            throw new RewriteEarlyExitException();
                        }
                        while (stream_calcClauseItem.hasNext()) {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1082:9: ^( calcClauseItem )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot(stream_calcClauseItem.nextNode(), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }
                        stream_calcClauseItem.reset();

                    }


                    retval.tree = root_0;

                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "attrCalcClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1086:1: attrCalcClause : ATTRCALC scalarExpr AS STRING_CONSTANT ( VIRAL )? ( ',' IDENTIFIER AS STRING_CONSTANT ( VIRAL )? )* ;
    public final ValidationMlParser.attrCalcClause_return attrCalcClause() throws Exception {
        ValidationMlParser.attrCalcClause_return retval = new ValidationMlParser.attrCalcClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ATTRCALC374 = null;
        Token AS376 = null;
        Token STRING_CONSTANT377 = null;
        Token VIRAL378 = null;
        Token char_literal379 = null;
        Token IDENTIFIER380 = null;
        Token AS381 = null;
        Token STRING_CONSTANT382 = null;
        Token VIRAL383 = null;
        ParserRuleReturnScope scalarExpr375 = null;

        Object ATTRCALC374_tree = null;
        Object AS376_tree = null;
        Object STRING_CONSTANT377_tree = null;
        Object VIRAL378_tree = null;
        Object char_literal379_tree = null;
        Object IDENTIFIER380_tree = null;
        Object AS381_tree = null;
        Object STRING_CONSTANT382_tree = null;
        Object VIRAL383_tree = null;

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1087:3: ( ATTRCALC scalarExpr AS STRING_CONSTANT ( VIRAL )? ( ',' IDENTIFIER AS STRING_CONSTANT ( VIRAL )? )* )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1088:3: ATTRCALC scalarExpr AS STRING_CONSTANT ( VIRAL )? ( ',' IDENTIFIER AS STRING_CONSTANT ( VIRAL )? )*
            {
                root_0 = (Object) adaptor.nil();


                ATTRCALC374 = (Token) match(input, ATTRCALC, FOLLOW_ATTRCALC_in_attrCalcClause9737);
                ATTRCALC374_tree = (Object) adaptor.create(ATTRCALC374);
                adaptor.addChild(root_0, ATTRCALC374_tree);

                pushFollow(FOLLOW_scalarExpr_in_attrCalcClause9739);
                scalarExpr375 = scalarExpr();
                state._fsp--;

                adaptor.addChild(root_0, scalarExpr375.getTree());

                AS376 = (Token) match(input, AS, FOLLOW_AS_in_attrCalcClause9741);
                AS376_tree = (Object) adaptor.create(AS376);
                adaptor.addChild(root_0, AS376_tree);

                STRING_CONSTANT377 = (Token) match(input, STRING_CONSTANT, FOLLOW_STRING_CONSTANT_in_attrCalcClause9743);
                STRING_CONSTANT377_tree = (Object) adaptor.create(STRING_CONSTANT377);
                adaptor.addChild(root_0, STRING_CONSTANT377_tree);

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1088:42: ( VIRAL )?
                int alt61 = 2;
                int LA61_0 = input.LA(1);
                if ((LA61_0 == VIRAL)) {
                    alt61 = 1;
                }
                switch (alt61) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1088:43: VIRAL
                    {
                        VIRAL378 = (Token) match(input, VIRAL, FOLLOW_VIRAL_in_attrCalcClause9746);
                        VIRAL378_tree = (Object) adaptor.create(VIRAL378);
                        adaptor.addChild(root_0, VIRAL378_tree);

                    }
                    break;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1088:51: ( ',' IDENTIFIER AS STRING_CONSTANT ( VIRAL )? )*
                loop63:
                while (true) {
                    int alt63 = 2;
                    int LA63_0 = input.LA(1);
                    if ((LA63_0 == CARTESIAN_PER)) {
                        alt63 = 1;
                    }

                    switch (alt63) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1088:52: ',' IDENTIFIER AS STRING_CONSTANT ( VIRAL )?
                        {
                            char_literal379 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_attrCalcClause9751);
                            char_literal379_tree = (Object) adaptor.create(char_literal379);
                            adaptor.addChild(root_0, char_literal379_tree);

                            IDENTIFIER380 = (Token) match(input, IDENTIFIER, FOLLOW_IDENTIFIER_in_attrCalcClause9753);
                            IDENTIFIER380_tree = (Object) adaptor.create(IDENTIFIER380);
                            adaptor.addChild(root_0, IDENTIFIER380_tree);

                            AS381 = (Token) match(input, AS, FOLLOW_AS_in_attrCalcClause9755);
                            AS381_tree = (Object) adaptor.create(AS381);
                            adaptor.addChild(root_0, AS381_tree);

                            STRING_CONSTANT382 = (Token) match(input, STRING_CONSTANT, FOLLOW_STRING_CONSTANT_in_attrCalcClause9757);
                            STRING_CONSTANT382_tree = (Object) adaptor.create(STRING_CONSTANT382);
                            adaptor.addChild(root_0, STRING_CONSTANT382_tree);

                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1088:86: ( VIRAL )?
                            int alt62 = 2;
                            int LA62_0 = input.LA(1);
                            if ((LA62_0 == VIRAL)) {
                                alt62 = 1;
                            }
                            switch (alt62) {
                                case 1:
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1088:87: VIRAL
                                {
                                    VIRAL383 = (Token) match(input, VIRAL, FOLLOW_VIRAL_in_attrCalcClause9760);
                                    VIRAL383_tree = (Object) adaptor.create(VIRAL383);
                                    adaptor.addChild(root_0, VIRAL383_tree);

                                }
                                break;

                            }

                        }
                        break;

                        default:
                            break loop63;
                    }
                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "calcClauseItem"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1091:1: calcClauseItem : ( calcExpr AS STRING_CONSTANT ( ROLE varRole ( VIRAL )? )? -> ^( CALC_CLAUSE_TAG calcExpr ( varRole )? ) ) ;
    public final ValidationMlParser.calcClauseItem_return calcClauseItem() throws Exception {
        ValidationMlParser.calcClauseItem_return retval = new ValidationMlParser.calcClauseItem_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AS385 = null;
        Token STRING_CONSTANT386 = null;
        Token ROLE387 = null;
        Token VIRAL389 = null;
        ParserRuleReturnScope calcExpr384 = null;
        ParserRuleReturnScope varRole388 = null;

        Object AS385_tree = null;
        Object STRING_CONSTANT386_tree = null;
        Object ROLE387_tree = null;
        Object VIRAL389_tree = null;
        RewriteRuleTokenStream stream_ROLE = new RewriteRuleTokenStream(adaptor, "token ROLE");
        RewriteRuleTokenStream stream_AS = new RewriteRuleTokenStream(adaptor, "token AS");
        RewriteRuleTokenStream stream_VIRAL = new RewriteRuleTokenStream(adaptor, "token VIRAL");
        RewriteRuleTokenStream stream_STRING_CONSTANT = new RewriteRuleTokenStream(adaptor, "token STRING_CONSTANT");
        RewriteRuleSubtreeStream stream_calcExpr = new RewriteRuleSubtreeStream(adaptor, "rule calcExpr");
        RewriteRuleSubtreeStream stream_varRole = new RewriteRuleSubtreeStream(adaptor, "rule varRole");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1092:3: ( ( calcExpr AS STRING_CONSTANT ( ROLE varRole ( VIRAL )? )? -> ^( CALC_CLAUSE_TAG calcExpr ( varRole )? ) ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1093:3: ( calcExpr AS STRING_CONSTANT ( ROLE varRole ( VIRAL )? )? -> ^( CALC_CLAUSE_TAG calcExpr ( varRole )? ) )
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1093:3: ( calcExpr AS STRING_CONSTANT ( ROLE varRole ( VIRAL )? )? -> ^( CALC_CLAUSE_TAG calcExpr ( varRole )? ) )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1094:5: calcExpr AS STRING_CONSTANT ( ROLE varRole ( VIRAL )? )?
                {
                    pushFollow(FOLLOW_calcExpr_in_calcClauseItem9785);
                    calcExpr384 = calcExpr();
                    state._fsp--;

                    stream_calcExpr.add(calcExpr384.getTree());
                    AS385 = (Token) match(input, AS, FOLLOW_AS_in_calcClauseItem9787);
                    stream_AS.add(AS385);

                    STRING_CONSTANT386 = (Token) match(input, STRING_CONSTANT, FOLLOW_STRING_CONSTANT_in_calcClauseItem9789);
                    stream_STRING_CONSTANT.add(STRING_CONSTANT386);

                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1094:33: ( ROLE varRole ( VIRAL )? )?
                    int alt65 = 2;
                    int LA65_0 = input.LA(1);
                    if ((LA65_0 == ROLE)) {
                        alt65 = 1;
                    }
                    switch (alt65) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1094:34: ROLE varRole ( VIRAL )?
                        {
                            ROLE387 = (Token) match(input, ROLE, FOLLOW_ROLE_in_calcClauseItem9792);
                            stream_ROLE.add(ROLE387);

                            pushFollow(FOLLOW_varRole_in_calcClauseItem9794);
                            varRole388 = varRole();
                            state._fsp--;

                            stream_varRole.add(varRole388.getTree());
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1094:47: ( VIRAL )?
                            int alt64 = 2;
                            int LA64_0 = input.LA(1);
                            if ((LA64_0 == VIRAL)) {
                                alt64 = 1;
                            }
                            switch (alt64) {
                                case 1:
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1094:48: VIRAL
                                {
                                    VIRAL389 = (Token) match(input, VIRAL, FOLLOW_VIRAL_in_calcClauseItem9797);
                                    stream_VIRAL.add(VIRAL389);

                                }
                                break;

                            }

                        }
                        break;

                    }

                    // AST REWRITE
                    // elements: calcExpr, varRole
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1095:7: -> ^( CALC_CLAUSE_TAG calcExpr ( varRole )? )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1096:9: ^( CALC_CLAUSE_TAG calcExpr ( varRole )? )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(CALC_CLAUSE_TAG, "CALC_CLAUSE_TAG"), root_1);
                            adaptor.addChild(root_1, stream_calcExpr.nextTree());
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1096:36: ( varRole )?
                            if (stream_varRole.hasNext()) {
                                adaptor.addChild(root_1, stream_varRole.nextTree());
                            }
                            stream_varRole.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "calcExpr"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1100:1: calcExpr : ( (t1= aggrFunction '(' s= scalarExpr ')' -> ^( $s) ) | scalarExpr );
    public final ValidationMlParser.calcExpr_return calcExpr() throws Exception {
        ValidationMlParser.calcExpr_return retval = new ValidationMlParser.calcExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal390 = null;
        Token char_literal391 = null;
        ParserRuleReturnScope t1 = null;
        ParserRuleReturnScope s = null;
        ParserRuleReturnScope scalarExpr392 = null;

        Object char_literal390_tree = null;
        Object char_literal391_tree = null;
        RewriteRuleTokenStream stream_426 = new RewriteRuleTokenStream(adaptor, "token 426");
        RewriteRuleTokenStream stream_427 = new RewriteRuleTokenStream(adaptor, "token 427");
        RewriteRuleSubtreeStream stream_scalarExpr = new RewriteRuleSubtreeStream(adaptor, "rule scalarExpr");
        RewriteRuleSubtreeStream stream_aggrFunction = new RewriteRuleSubtreeStream(adaptor, "rule aggrFunction");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1101:3: ( (t1= aggrFunction '(' s= scalarExpr ')' -> ^( $s) ) | scalarExpr )
            int alt66 = 2;
            int LA66_0 = input.LA(1);
            if ((LA66_0 == AVG || (LA66_0 >= COUNT && LA66_0 <= COUNT_DISTINCT) || LA66_0 == MAX || LA66_0 == MEDIAN || LA66_0 == MIN || LA66_0 == STD || LA66_0 == SUM)) {
                alt66 = 1;
            } else if ((LA66_0 == ABS || LA66_0 == BOOLEAN_CONSTANT || LA66_0 == CONCAT || LA66_0 == EXP || LA66_0 == FLOAT_CONSTANT || LA66_0 == IDENTIFIER || LA66_0 == INTEGER_CONSTANT || LA66_0 == LCASE || LA66_0 == LEN || LA66_0 == LN || LA66_0 == MINUS || LA66_0 == MOD || LA66_0 == NULL_CONSTANT || LA66_0 == PLUS || LA66_0 == POWER || LA66_0 == ROUND || LA66_0 == STRING_CONSTANT || LA66_0 == SUBSTR || (LA66_0 >= TRIM && LA66_0 <= TRUNC) || LA66_0 == UCASE || LA66_0 == 426 || LA66_0 == 430)) {
                alt66 = 2;
            } else {
                NoViableAltException nvae =
                        new NoViableAltException("", 66, 0, input);
                throw nvae;
            }

            switch (alt66) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1102:3: (t1= aggrFunction '(' s= scalarExpr ')' -> ^( $s) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1102:3: (t1= aggrFunction '(' s= scalarExpr ')' -> ^( $s) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1102:4: t1= aggrFunction '(' s= scalarExpr ')'
                    {
                        pushFollow(FOLLOW_aggrFunction_in_calcExpr9848);
                        t1 = aggrFunction();
                        state._fsp--;

                        stream_aggrFunction.add(t1.getTree());
                        char_literal390 = (Token) match(input, 426, FOLLOW_426_in_calcExpr9850);
                        stream_426.add(char_literal390);

                        pushFollow(FOLLOW_scalarExpr_in_calcExpr9854);
                        s = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(s.getTree());
                        char_literal391 = (Token) match(input, 427, FOLLOW_427_in_calcExpr9856);
                        stream_427.add(char_literal391);

                        // AST REWRITE
                        // elements: s
                        // token labels:
                        // rule labels: s, retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_s = new RewriteRuleSubtreeStream(adaptor, "rule s", s != null ? s.getTree() : null);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1103:7: -> ^( $s)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1104:9: ^( $s)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot(stream_s.nextNode(), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1105:5: scalarExpr
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_scalarExpr_in_calcExpr9884);
                    scalarExpr392 = scalarExpr();
                    state._fsp--;

                    adaptor.addChild(root_0, scalarExpr392.getTree());

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "dropClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1108:1: dropClause : DROP '(' dropClauseItem ( ',' dropClauseItem )* ')' -> ( ^( dropClauseItem ) )+ ;
    public final ValidationMlParser.dropClause_return dropClause() throws Exception {
        ValidationMlParser.dropClause_return retval = new ValidationMlParser.dropClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DROP393 = null;
        Token char_literal394 = null;
        Token char_literal396 = null;
        Token char_literal398 = null;
        ParserRuleReturnScope dropClauseItem395 = null;
        ParserRuleReturnScope dropClauseItem397 = null;

        Object DROP393_tree = null;
        Object char_literal394_tree = null;
        Object char_literal396_tree = null;
        Object char_literal398_tree = null;
        RewriteRuleTokenStream stream_426 = new RewriteRuleTokenStream(adaptor, "token 426");
        RewriteRuleTokenStream stream_427 = new RewriteRuleTokenStream(adaptor, "token 427");
        RewriteRuleTokenStream stream_CARTESIAN_PER = new RewriteRuleTokenStream(adaptor, "token CARTESIAN_PER");
        RewriteRuleTokenStream stream_DROP = new RewriteRuleTokenStream(adaptor, "token DROP");
        RewriteRuleSubtreeStream stream_dropClauseItem = new RewriteRuleSubtreeStream(adaptor, "rule dropClauseItem");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1109:3: ( DROP '(' dropClauseItem ( ',' dropClauseItem )* ')' -> ( ^( dropClauseItem ) )+ )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1110:3: DROP '(' dropClauseItem ( ',' dropClauseItem )* ')'
            {
                DROP393 = (Token) match(input, DROP, FOLLOW_DROP_in_dropClause9899);
                stream_DROP.add(DROP393);

                char_literal394 = (Token) match(input, 426, FOLLOW_426_in_dropClause9901);
                stream_426.add(char_literal394);

                pushFollow(FOLLOW_dropClauseItem_in_dropClause9903);
                dropClauseItem395 = dropClauseItem();
                state._fsp--;

                stream_dropClauseItem.add(dropClauseItem395.getTree());
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1110:27: ( ',' dropClauseItem )*
                loop67:
                while (true) {
                    int alt67 = 2;
                    int LA67_0 = input.LA(1);
                    if ((LA67_0 == CARTESIAN_PER)) {
                        alt67 = 1;
                    }

                    switch (alt67) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1110:28: ',' dropClauseItem
                        {
                            char_literal396 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_dropClause9906);
                            stream_CARTESIAN_PER.add(char_literal396);

                            pushFollow(FOLLOW_dropClauseItem_in_dropClause9908);
                            dropClauseItem397 = dropClauseItem();
                            state._fsp--;

                            stream_dropClauseItem.add(dropClauseItem397.getTree());
                        }
                        break;

                        default:
                            break loop67;
                    }
                }

                char_literal398 = (Token) match(input, 427, FOLLOW_427_in_dropClause9912);
                stream_427.add(char_literal398);

                // AST REWRITE
                // elements: dropClauseItem
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                root_0 = (Object) adaptor.nil();
                // 1111:5: -> ( ^( dropClauseItem ) )+
                {
                    if (!(stream_dropClauseItem.hasNext())) {
                        throw new RewriteEarlyExitException();
                    }
                    while (stream_dropClauseItem.hasNext()) {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1112:7: ^( dropClauseItem )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot(stream_dropClauseItem.nextNode(), root_1);
                            adaptor.addChild(root_0, root_1);
                        }

                    }
                    stream_dropClauseItem.reset();

                }


                retval.tree = root_0;

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "dropClauseItem"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1115:1: dropClauseItem : ( varID -> ^( VAR_DROP_TAG varID ) ) ;
    public final ValidationMlParser.dropClauseItem_return dropClauseItem() throws Exception {
        ValidationMlParser.dropClauseItem_return retval = new ValidationMlParser.dropClauseItem_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        ParserRuleReturnScope varID399 = null;

        RewriteRuleSubtreeStream stream_varID = new RewriteRuleSubtreeStream(adaptor, "rule varID");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1116:3: ( ( varID -> ^( VAR_DROP_TAG varID ) ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1117:3: ( varID -> ^( VAR_DROP_TAG varID ) )
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1117:3: ( varID -> ^( VAR_DROP_TAG varID ) )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1117:4: varID
                {
                    pushFollow(FOLLOW_varID_in_dropClauseItem9945);
                    varID399 = varID();
                    state._fsp--;

                    stream_varID.add(varID399.getTree());
                    // AST REWRITE
                    // elements: varID
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1118:7: -> ^( VAR_DROP_TAG varID )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1119:9: ^( VAR_DROP_TAG varID )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(VAR_DROP_TAG, "VAR_DROP_TAG"), root_1);
                            adaptor.addChild(root_1, stream_varID.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "keepClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1122:1: keepClause : KEEP '(' keepClauseItem ( ',' keepClauseItem )* ')' -> ( ^( keepClauseItem ) )+ ;
    public final ValidationMlParser.keepClause_return keepClause() throws Exception {
        ValidationMlParser.keepClause_return retval = new ValidationMlParser.keepClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token KEEP400 = null;
        Token char_literal401 = null;
        Token char_literal403 = null;
        Token char_literal405 = null;
        ParserRuleReturnScope keepClauseItem402 = null;
        ParserRuleReturnScope keepClauseItem404 = null;

        Object KEEP400_tree = null;
        Object char_literal401_tree = null;
        Object char_literal403_tree = null;
        Object char_literal405_tree = null;
        RewriteRuleTokenStream stream_KEEP = new RewriteRuleTokenStream(adaptor, "token KEEP");
        RewriteRuleTokenStream stream_426 = new RewriteRuleTokenStream(adaptor, "token 426");
        RewriteRuleTokenStream stream_427 = new RewriteRuleTokenStream(adaptor, "token 427");
        RewriteRuleTokenStream stream_CARTESIAN_PER = new RewriteRuleTokenStream(adaptor, "token CARTESIAN_PER");
        RewriteRuleSubtreeStream stream_keepClauseItem = new RewriteRuleSubtreeStream(adaptor, "rule keepClauseItem");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1123:3: ( KEEP '(' keepClauseItem ( ',' keepClauseItem )* ')' -> ( ^( keepClauseItem ) )+ )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1124:3: KEEP '(' keepClauseItem ( ',' keepClauseItem )* ')'
            {
                KEEP400 = (Token) match(input, KEEP, FOLLOW_KEEP_in_keepClause9983);
                stream_KEEP.add(KEEP400);

                char_literal401 = (Token) match(input, 426, FOLLOW_426_in_keepClause9985);
                stream_426.add(char_literal401);

                pushFollow(FOLLOW_keepClauseItem_in_keepClause9987);
                keepClauseItem402 = keepClauseItem();
                state._fsp--;

                stream_keepClauseItem.add(keepClauseItem402.getTree());
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1124:27: ( ',' keepClauseItem )*
                loop68:
                while (true) {
                    int alt68 = 2;
                    int LA68_0 = input.LA(1);
                    if ((LA68_0 == CARTESIAN_PER)) {
                        alt68 = 1;
                    }

                    switch (alt68) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1124:28: ',' keepClauseItem
                        {
                            char_literal403 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_keepClause9990);
                            stream_CARTESIAN_PER.add(char_literal403);

                            pushFollow(FOLLOW_keepClauseItem_in_keepClause9992);
                            keepClauseItem404 = keepClauseItem();
                            state._fsp--;

                            stream_keepClauseItem.add(keepClauseItem404.getTree());
                        }
                        break;

                        default:
                            break loop68;
                    }
                }

                char_literal405 = (Token) match(input, 427, FOLLOW_427_in_keepClause9996);
                stream_427.add(char_literal405);

                // AST REWRITE
                // elements: keepClauseItem
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                root_0 = (Object) adaptor.nil();
                // 1125:5: -> ( ^( keepClauseItem ) )+
                {
                    if (!(stream_keepClauseItem.hasNext())) {
                        throw new RewriteEarlyExitException();
                    }
                    while (stream_keepClauseItem.hasNext()) {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1126:7: ^( keepClauseItem )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot(stream_keepClauseItem.nextNode(), root_1);
                            adaptor.addChild(root_0, root_1);
                        }

                    }
                    stream_keepClauseItem.reset();

                }


                retval.tree = root_0;

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "keepClauseItem"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1129:1: keepClauseItem : ( varID -> ^( VAR_KEEP_TAG varID ) ) ;
    public final ValidationMlParser.keepClauseItem_return keepClauseItem() throws Exception {
        ValidationMlParser.keepClauseItem_return retval = new ValidationMlParser.keepClauseItem_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        ParserRuleReturnScope varID406 = null;

        RewriteRuleSubtreeStream stream_varID = new RewriteRuleSubtreeStream(adaptor, "rule varID");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1130:3: ( ( varID -> ^( VAR_KEEP_TAG varID ) ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1131:3: ( varID -> ^( VAR_KEEP_TAG varID ) )
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1131:3: ( varID -> ^( VAR_KEEP_TAG varID ) )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1131:4: varID
                {
                    pushFollow(FOLLOW_varID_in_keepClauseItem10029);
                    varID406 = varID();
                    state._fsp--;

                    stream_varID.add(varID406.getTree());
                    // AST REWRITE
                    // elements: varID
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1132:7: -> ^( VAR_KEEP_TAG varID )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1133:9: ^( VAR_KEEP_TAG varID )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(VAR_KEEP_TAG, "VAR_KEEP_TAG"), root_1);
                            adaptor.addChild(root_1, stream_varID.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "compareClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1136:1: compareClause : (b0= compOpScalarClause b1= constant -> ^( $b0 $b1) ) ;
    public final ValidationMlParser.compareClause_return compareClause() throws Exception {
        ValidationMlParser.compareClause_return retval = new ValidationMlParser.compareClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        ParserRuleReturnScope b0 = null;
        ParserRuleReturnScope b1 = null;

        RewriteRuleSubtreeStream stream_compOpScalarClause = new RewriteRuleSubtreeStream(adaptor, "rule compOpScalarClause");
        RewriteRuleSubtreeStream stream_constant = new RewriteRuleSubtreeStream(adaptor, "rule constant");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1137:3: ( (b0= compOpScalarClause b1= constant -> ^( $b0 $b1) ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1138:3: (b0= compOpScalarClause b1= constant -> ^( $b0 $b1) )
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1138:3: (b0= compOpScalarClause b1= constant -> ^( $b0 $b1) )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1138:4: b0= compOpScalarClause b1= constant
                {
                    pushFollow(FOLLOW_compOpScalarClause_in_compareClause10070);
                    b0 = compOpScalarClause();
                    state._fsp--;

                    stream_compOpScalarClause.add(b0.getTree());
                    pushFollow(FOLLOW_constant_in_compareClause10074);
                    b1 = constant();
                    state._fsp--;

                    stream_constant.add(b1.getTree());
                    // AST REWRITE
                    // elements: b1, b0
                    // token labels:
                    // rule labels: b0, retval, b1
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_b0 = new RewriteRuleSubtreeStream(adaptor, "rule b0", b0 != null ? b0.getTree() : null);
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                    RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1139:7: -> ^( $b0 $b1)
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1140:9: ^( $b0 $b1)
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot(stream_b0.nextNode(), root_1);
                            adaptor.addChild(root_1, stream_b1.nextTree());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "inBetweenClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1143:1: inBetweenClause : ( ( ( IN c2= setExpr ) -> ^( SCALAR_IN_SET_CLAUSE_TAG $c2) ) | ( ( BETWEEN a1= constant AND a2= constant ) -> ^( SCALAR_BETWEEN_CLAUSE_TAG $a1 $a2) ) | ( ( NOT IN c2= setExpr ) -> ^( SCALAR_NOT_IN_SET_CLAUSE_TAG $c2) ) | ( ( NOT BETWEEN a1= constant AND a2= constant ) -> ^( SCALAR_NOT_BETWEEN_CLAUSE_TAG $a1 $a2) ) );
    public final ValidationMlParser.inBetweenClause_return inBetweenClause() throws Exception {
        ValidationMlParser.inBetweenClause_return retval = new ValidationMlParser.inBetweenClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IN407 = null;
        Token BETWEEN408 = null;
        Token AND409 = null;
        Token NOT410 = null;
        Token IN411 = null;
        Token NOT412 = null;
        Token BETWEEN413 = null;
        Token AND414 = null;
        ParserRuleReturnScope c2 = null;
        ParserRuleReturnScope a1 = null;
        ParserRuleReturnScope a2 = null;

        Object IN407_tree = null;
        Object BETWEEN408_tree = null;
        Object AND409_tree = null;
        Object NOT410_tree = null;
        Object IN411_tree = null;
        Object NOT412_tree = null;
        Object BETWEEN413_tree = null;
        Object AND414_tree = null;
        RewriteRuleTokenStream stream_NOT = new RewriteRuleTokenStream(adaptor, "token NOT");
        RewriteRuleTokenStream stream_IN = new RewriteRuleTokenStream(adaptor, "token IN");
        RewriteRuleTokenStream stream_AND = new RewriteRuleTokenStream(adaptor, "token AND");
        RewriteRuleTokenStream stream_BETWEEN = new RewriteRuleTokenStream(adaptor, "token BETWEEN");
        RewriteRuleSubtreeStream stream_constant = new RewriteRuleSubtreeStream(adaptor, "rule constant");
        RewriteRuleSubtreeStream stream_setExpr = new RewriteRuleSubtreeStream(adaptor, "rule setExpr");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1144:3: ( ( ( IN c2= setExpr ) -> ^( SCALAR_IN_SET_CLAUSE_TAG $c2) ) | ( ( BETWEEN a1= constant AND a2= constant ) -> ^( SCALAR_BETWEEN_CLAUSE_TAG $a1 $a2) ) | ( ( NOT IN c2= setExpr ) -> ^( SCALAR_NOT_IN_SET_CLAUSE_TAG $c2) ) | ( ( NOT BETWEEN a1= constant AND a2= constant ) -> ^( SCALAR_NOT_BETWEEN_CLAUSE_TAG $a1 $a2) ) )
            int alt69 = 4;
            switch (input.LA(1)) {
                case IN: {
                    alt69 = 1;
                }
                break;
                case BETWEEN: {
                    alt69 = 2;
                }
                break;
                case NOT: {
                    int LA69_3 = input.LA(2);
                    if ((LA69_3 == IN)) {
                        alt69 = 3;
                    } else if ((LA69_3 == BETWEEN)) {
                        alt69 = 4;
                    } else {
                        int nvaeMark = input.mark();
                        try {
                            input.consume();
                            NoViableAltException nvae =
                                    new NoViableAltException("", 69, 3, input);
                            throw nvae;
                        } finally {
                            input.rewind(nvaeMark);
                        }
                    }

                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 69, 0, input);
                    throw nvae;
            }
            switch (alt69) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1145:3: ( ( IN c2= setExpr ) -> ^( SCALAR_IN_SET_CLAUSE_TAG $c2) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1145:3: ( ( IN c2= setExpr ) -> ^( SCALAR_IN_SET_CLAUSE_TAG $c2) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1146:5: ( IN c2= setExpr )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1146:5: ( IN c2= setExpr )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1146:6: IN c2= setExpr
                        {
                            IN407 = (Token) match(input, IN, FOLLOW_IN_in_inBetweenClause10121);
                            stream_IN.add(IN407);

                            pushFollow(FOLLOW_setExpr_in_inBetweenClause10125);
                            c2 = setExpr();
                            state._fsp--;

                            stream_setExpr.add(c2.getTree());
                        }

                        // AST REWRITE
                        // elements: c2
                        // token labels:
                        // rule labels: retval, c2
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_c2 = new RewriteRuleSubtreeStream(adaptor, "rule c2", c2 != null ? c2.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1147:7: -> ^( SCALAR_IN_SET_CLAUSE_TAG $c2)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1148:9: ^( SCALAR_IN_SET_CLAUSE_TAG $c2)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_IN_SET_CLAUSE_TAG, "SCALAR_IN_SET_CLAUSE_TAG"), root_1);
                                adaptor.addChild(root_1, stream_c2.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1151:3: ( ( BETWEEN a1= constant AND a2= constant ) -> ^( SCALAR_BETWEEN_CLAUSE_TAG $a1 $a2) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1151:3: ( ( BETWEEN a1= constant AND a2= constant ) -> ^( SCALAR_BETWEEN_CLAUSE_TAG $a1 $a2) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1152:5: ( BETWEEN a1= constant AND a2= constant )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1152:5: ( BETWEEN a1= constant AND a2= constant )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1152:6: BETWEEN a1= constant AND a2= constant
                        {
                            BETWEEN408 = (Token) match(input, BETWEEN, FOLLOW_BETWEEN_in_inBetweenClause10168);
                            stream_BETWEEN.add(BETWEEN408);

                            pushFollow(FOLLOW_constant_in_inBetweenClause10172);
                            a1 = constant();
                            state._fsp--;

                            stream_constant.add(a1.getTree());
                            AND409 = (Token) match(input, AND, FOLLOW_AND_in_inBetweenClause10174);
                            stream_AND.add(AND409);

                            pushFollow(FOLLOW_constant_in_inBetweenClause10178);
                            a2 = constant();
                            state._fsp--;

                            stream_constant.add(a2.getTree());
                        }

                        // AST REWRITE
                        // elements: a1, a2
                        // token labels:
                        // rule labels: a1, a2, retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_a1 = new RewriteRuleSubtreeStream(adaptor, "rule a1", a1 != null ? a1.getTree() : null);
                        RewriteRuleSubtreeStream stream_a2 = new RewriteRuleSubtreeStream(adaptor, "rule a2", a2 != null ? a2.getTree() : null);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1153:7: -> ^( SCALAR_BETWEEN_CLAUSE_TAG $a1 $a2)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1154:9: ^( SCALAR_BETWEEN_CLAUSE_TAG $a1 $a2)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_BETWEEN_CLAUSE_TAG, "SCALAR_BETWEEN_CLAUSE_TAG"), root_1);
                                adaptor.addChild(root_1, stream_a1.nextTree());
                                adaptor.addChild(root_1, stream_a2.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 3:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1157:3: ( ( NOT IN c2= setExpr ) -> ^( SCALAR_NOT_IN_SET_CLAUSE_TAG $c2) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1157:3: ( ( NOT IN c2= setExpr ) -> ^( SCALAR_NOT_IN_SET_CLAUSE_TAG $c2) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1158:5: ( NOT IN c2= setExpr )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1158:5: ( NOT IN c2= setExpr )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1158:6: NOT IN c2= setExpr
                        {
                            NOT410 = (Token) match(input, NOT, FOLLOW_NOT_in_inBetweenClause10224);
                            stream_NOT.add(NOT410);

                            IN411 = (Token) match(input, IN, FOLLOW_IN_in_inBetweenClause10226);
                            stream_IN.add(IN411);

                            pushFollow(FOLLOW_setExpr_in_inBetweenClause10230);
                            c2 = setExpr();
                            state._fsp--;

                            stream_setExpr.add(c2.getTree());
                        }

                        // AST REWRITE
                        // elements: c2
                        // token labels:
                        // rule labels: retval, c2
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_c2 = new RewriteRuleSubtreeStream(adaptor, "rule c2", c2 != null ? c2.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1159:7: -> ^( SCALAR_NOT_IN_SET_CLAUSE_TAG $c2)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1160:9: ^( SCALAR_NOT_IN_SET_CLAUSE_TAG $c2)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_NOT_IN_SET_CLAUSE_TAG, "SCALAR_NOT_IN_SET_CLAUSE_TAG"), root_1);
                                adaptor.addChild(root_1, stream_c2.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 4:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1163:3: ( ( NOT BETWEEN a1= constant AND a2= constant ) -> ^( SCALAR_NOT_BETWEEN_CLAUSE_TAG $a1 $a2) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1163:3: ( ( NOT BETWEEN a1= constant AND a2= constant ) -> ^( SCALAR_NOT_BETWEEN_CLAUSE_TAG $a1 $a2) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1164:5: ( NOT BETWEEN a1= constant AND a2= constant )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1164:5: ( NOT BETWEEN a1= constant AND a2= constant )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1164:6: NOT BETWEEN a1= constant AND a2= constant
                        {
                            NOT412 = (Token) match(input, NOT, FOLLOW_NOT_in_inBetweenClause10273);
                            stream_NOT.add(NOT412);

                            BETWEEN413 = (Token) match(input, BETWEEN, FOLLOW_BETWEEN_in_inBetweenClause10275);
                            stream_BETWEEN.add(BETWEEN413);

                            pushFollow(FOLLOW_constant_in_inBetweenClause10279);
                            a1 = constant();
                            state._fsp--;

                            stream_constant.add(a1.getTree());
                            AND414 = (Token) match(input, AND, FOLLOW_AND_in_inBetweenClause10281);
                            stream_AND.add(AND414);

                            pushFollow(FOLLOW_constant_in_inBetweenClause10285);
                            a2 = constant();
                            state._fsp--;

                            stream_constant.add(a2.getTree());
                        }

                        // AST REWRITE
                        // elements: a2, a1
                        // token labels:
                        // rule labels: a1, a2, retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_a1 = new RewriteRuleSubtreeStream(adaptor, "rule a1", a1 != null ? a1.getTree() : null);
                        RewriteRuleSubtreeStream stream_a2 = new RewriteRuleSubtreeStream(adaptor, "rule a2", a2 != null ? a2.getTree() : null);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1165:7: -> ^( SCALAR_NOT_BETWEEN_CLAUSE_TAG $a1 $a2)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1166:9: ^( SCALAR_NOT_BETWEEN_CLAUSE_TAG $a1 $a2)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_NOT_BETWEEN_CLAUSE_TAG, "SCALAR_NOT_BETWEEN_CLAUSE_TAG"), root_1);
                                adaptor.addChild(root_1, stream_a1.nextTree());
                                adaptor.addChild(root_1, stream_a2.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "dimClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1170:1: dimClause : (| compareClause | inBetweenClause );
    public final ValidationMlParser.dimClause_return dimClause() throws Exception {
        ValidationMlParser.dimClause_return retval = new ValidationMlParser.dimClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        ParserRuleReturnScope compareClause415 = null;
        ParserRuleReturnScope inBetweenClause416 = null;


        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1171:3: (| compareClause | inBetweenClause )
            int alt70 = 3;
            switch (input.LA(1)) {
                case EOF: {
                    alt70 = 1;
                }
                break;
                case EQ:
                case GE:
                case GT:
                case LE:
                case LT:
                case NE: {
                    alt70 = 2;
                }
                break;
                case BETWEEN:
                case IN:
                case NOT: {
                    alt70 = 3;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 70, 0, input);
                    throw nvae;
            }
            switch (alt70) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1172:3:
                {
                    root_0 = (Object) adaptor.nil();


                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1172:5: compareClause
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_compareClause_in_dimClause10333);
                    compareClause415 = compareClause();
                    state._fsp--;

                    adaptor.addChild(root_0, compareClause415.getTree());

                }
                break;
                case 3:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1173:5: inBetweenClause
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_inBetweenClause_in_dimClause10339);
                    inBetweenClause416 = inBetweenClause();
                    state._fsp--;

                    adaptor.addChild(root_0, inBetweenClause416.getTree());

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "varRole"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1176:1: varRole : ( ( DIMENSION -> ROLE_DIMENSION_TAG ) | ( MEASURE -> ROLE_MEASURE_TAG ) | ( ATTRIBUTE -> ROLE_ATTRIBUTE_TAG ) );
    public final ValidationMlParser.varRole_return varRole() throws Exception {
        ValidationMlParser.varRole_return retval = new ValidationMlParser.varRole_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DIMENSION417 = null;
        Token MEASURE418 = null;
        Token ATTRIBUTE419 = null;

        Object DIMENSION417_tree = null;
        Object MEASURE418_tree = null;
        Object ATTRIBUTE419_tree = null;
        RewriteRuleTokenStream stream_ATTRIBUTE = new RewriteRuleTokenStream(adaptor, "token ATTRIBUTE");
        RewriteRuleTokenStream stream_DIMENSION = new RewriteRuleTokenStream(adaptor, "token DIMENSION");
        RewriteRuleTokenStream stream_MEASURE = new RewriteRuleTokenStream(adaptor, "token MEASURE");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1177:3: ( ( DIMENSION -> ROLE_DIMENSION_TAG ) | ( MEASURE -> ROLE_MEASURE_TAG ) | ( ATTRIBUTE -> ROLE_ATTRIBUTE_TAG ) )
            int alt71 = 3;
            switch (input.LA(1)) {
                case DIMENSION: {
                    alt71 = 1;
                }
                break;
                case MEASURE: {
                    alt71 = 2;
                }
                break;
                case ATTRIBUTE: {
                    alt71 = 3;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 71, 0, input);
                    throw nvae;
            }
            switch (alt71) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1178:3: ( DIMENSION -> ROLE_DIMENSION_TAG )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1178:3: ( DIMENSION -> ROLE_DIMENSION_TAG )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1178:4: DIMENSION
                    {
                        DIMENSION417 = (Token) match(input, DIMENSION, FOLLOW_DIMENSION_in_varRole10355);
                        stream_DIMENSION.add(DIMENSION417);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1179:7: -> ROLE_DIMENSION_TAG
                        {
                            adaptor.addChild(root_0, (Object) adaptor.create(ROLE_DIMENSION_TAG, "ROLE_DIMENSION_TAG"));
                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1180:5: ( MEASURE -> ROLE_MEASURE_TAG )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1180:5: ( MEASURE -> ROLE_MEASURE_TAG )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1180:6: MEASURE
                    {
                        MEASURE418 = (Token) match(input, MEASURE, FOLLOW_MEASURE_in_varRole10373);
                        stream_MEASURE.add(MEASURE418);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1181:7: -> ROLE_MEASURE_TAG
                        {
                            adaptor.addChild(root_0, (Object) adaptor.create(ROLE_MEASURE_TAG, "ROLE_MEASURE_TAG"));
                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 3:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1182:5: ( ATTRIBUTE -> ROLE_ATTRIBUTE_TAG )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1182:5: ( ATTRIBUTE -> ROLE_ATTRIBUTE_TAG )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1182:6: ATTRIBUTE
                    {
                        ATTRIBUTE419 = (Token) match(input, ATTRIBUTE, FOLLOW_ATTRIBUTE_in_varRole10391);
                        stream_ATTRIBUTE.add(ATTRIBUTE419);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1183:7: -> ROLE_ATTRIBUTE_TAG
                        {
                            adaptor.addChild(root_0, (Object) adaptor.create(ROLE_ATTRIBUTE_TAG, "ROLE_ATTRIBUTE_TAG"));
                        }


                        retval.tree = root_0;

                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "bScalarExpr"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1186:1: bScalarExpr : ( sExprOr -> sExprOr ) ( ( ( OR b1= sExprOr ) -> ^( SCALAR_OR_TAG $bScalarExpr $b1) ) | ( ( XOR b1= sExprOr ) -> ^( SCALAR_XOR_TAG $bScalarExpr $b1) ) )* ;
    public final ValidationMlParser.bScalarExpr_return bScalarExpr() throws Exception {
        ValidationMlParser.bScalarExpr_return retval = new ValidationMlParser.bScalarExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token OR421 = null;
        Token XOR422 = null;
        ParserRuleReturnScope b1 = null;
        ParserRuleReturnScope sExprOr420 = null;

        Object OR421_tree = null;
        Object XOR422_tree = null;
        RewriteRuleTokenStream stream_OR = new RewriteRuleTokenStream(adaptor, "token OR");
        RewriteRuleTokenStream stream_XOR = new RewriteRuleTokenStream(adaptor, "token XOR");
        RewriteRuleSubtreeStream stream_sExprOr = new RewriteRuleSubtreeStream(adaptor, "rule sExprOr");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1187:3: ( ( sExprOr -> sExprOr ) ( ( ( OR b1= sExprOr ) -> ^( SCALAR_OR_TAG $bScalarExpr $b1) ) | ( ( XOR b1= sExprOr ) -> ^( SCALAR_XOR_TAG $bScalarExpr $b1) ) )* )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1188:3: ( sExprOr -> sExprOr ) ( ( ( OR b1= sExprOr ) -> ^( SCALAR_OR_TAG $bScalarExpr $b1) ) | ( ( XOR b1= sExprOr ) -> ^( SCALAR_XOR_TAG $bScalarExpr $b1) ) )*
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1188:3: ( sExprOr -> sExprOr )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1188:4: sExprOr
                {
                    pushFollow(FOLLOW_sExprOr_in_bScalarExpr10418);
                    sExprOr420 = sExprOr();
                    state._fsp--;

                    stream_sExprOr.add(sExprOr420.getTree());
                    // AST REWRITE
                    // elements: sExprOr
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1189:7: -> sExprOr
                    {
                        adaptor.addChild(root_0, stream_sExprOr.nextTree());
                    }


                    retval.tree = root_0;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1190:3: ( ( ( OR b1= sExprOr ) -> ^( SCALAR_OR_TAG $bScalarExpr $b1) ) | ( ( XOR b1= sExprOr ) -> ^( SCALAR_XOR_TAG $bScalarExpr $b1) ) )*
                loop72:
                while (true) {
                    int alt72 = 3;
                    int LA72_0 = input.LA(1);
                    if ((LA72_0 == OR)) {
                        alt72 = 1;
                    } else if ((LA72_0 == XOR)) {
                        alt72 = 2;
                    }

                    switch (alt72) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1191:5: ( ( OR b1= sExprOr ) -> ^( SCALAR_OR_TAG $bScalarExpr $b1) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1191:5: ( ( OR b1= sExprOr ) -> ^( SCALAR_OR_TAG $bScalarExpr $b1) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1192:7: ( OR b1= sExprOr )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1192:7: ( OR b1= sExprOr )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1192:8: OR b1= sExprOr
                                {
                                    OR421 = (Token) match(input, OR, FOLLOW_OR_in_bScalarExpr10448);
                                    stream_OR.add(OR421);

                                    pushFollow(FOLLOW_sExprOr_in_bScalarExpr10452);
                                    b1 = sExprOr();
                                    state._fsp--;

                                    stream_sExprOr.add(b1.getTree());
                                }

                                // AST REWRITE
                                // elements: bScalarExpr, b1
                                // token labels:
                                // rule labels: retval, b1
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                                RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 1193:9: -> ^( SCALAR_OR_TAG $bScalarExpr $b1)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1194:11: ^( SCALAR_OR_TAG $bScalarExpr $b1)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_OR_TAG, "SCALAR_OR_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_b1.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;
                        case 2:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1197:5: ( ( XOR b1= sExprOr ) -> ^( SCALAR_XOR_TAG $bScalarExpr $b1) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1197:5: ( ( XOR b1= sExprOr ) -> ^( SCALAR_XOR_TAG $bScalarExpr $b1) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1198:7: ( XOR b1= sExprOr )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1198:7: ( XOR b1= sExprOr )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1198:8: XOR b1= sExprOr
                                {
                                    XOR422 = (Token) match(input, XOR, FOLLOW_XOR_in_bScalarExpr10510);
                                    stream_XOR.add(XOR422);

                                    pushFollow(FOLLOW_sExprOr_in_bScalarExpr10514);
                                    b1 = sExprOr();
                                    state._fsp--;

                                    stream_sExprOr.add(b1.getTree());
                                }

                                // AST REWRITE
                                // elements: b1, bScalarExpr
                                // token labels:
                                // rule labels: retval, b1
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                                RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 1199:9: -> ^( SCALAR_XOR_TAG $bScalarExpr $b1)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1200:11: ^( SCALAR_XOR_TAG $bScalarExpr $b1)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_XOR_TAG, "SCALAR_XOR_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_b1.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;

                        default:
                            break loop72;
                    }
                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "sExprOr"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1205:1: sExprOr : ( sExprAnd -> sExprAnd ) ( ( AND b1= sExprAnd ) -> ^( SCALAR_AND_TAG $sExprOr $b1) )* ;
    public final ValidationMlParser.sExprOr_return sExprOr() throws Exception {
        ValidationMlParser.sExprOr_return retval = new ValidationMlParser.sExprOr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AND424 = null;
        ParserRuleReturnScope b1 = null;
        ParserRuleReturnScope sExprAnd423 = null;

        Object AND424_tree = null;
        RewriteRuleTokenStream stream_AND = new RewriteRuleTokenStream(adaptor, "token AND");
        RewriteRuleSubtreeStream stream_sExprAnd = new RewriteRuleSubtreeStream(adaptor, "rule sExprAnd");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1206:3: ( ( sExprAnd -> sExprAnd ) ( ( AND b1= sExprAnd ) -> ^( SCALAR_AND_TAG $sExprOr $b1) )* )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1207:3: ( sExprAnd -> sExprAnd ) ( ( AND b1= sExprAnd ) -> ^( SCALAR_AND_TAG $sExprOr $b1) )*
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1207:3: ( sExprAnd -> sExprAnd )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1207:4: sExprAnd
                {
                    pushFollow(FOLLOW_sExprAnd_in_sExprOr10572);
                    sExprAnd423 = sExprAnd();
                    state._fsp--;

                    stream_sExprAnd.add(sExprAnd423.getTree());
                    // AST REWRITE
                    // elements: sExprAnd
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1208:7: -> sExprAnd
                    {
                        adaptor.addChild(root_0, stream_sExprAnd.nextTree());
                    }


                    retval.tree = root_0;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1209:3: ( ( AND b1= sExprAnd ) -> ^( SCALAR_AND_TAG $sExprOr $b1) )*
                loop73:
                while (true) {
                    int alt73 = 2;
                    int LA73_0 = input.LA(1);
                    if ((LA73_0 == AND)) {
                        alt73 = 1;
                    }

                    switch (alt73) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1210:5: ( AND b1= sExprAnd )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1210:5: ( AND b1= sExprAnd )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1210:6: AND b1= sExprAnd
                            {
                                AND424 = (Token) match(input, AND, FOLLOW_AND_in_sExprOr10594);
                                stream_AND.add(AND424);

                                pushFollow(FOLLOW_sExprAnd_in_sExprOr10598);
                                b1 = sExprAnd();
                                state._fsp--;

                                stream_sExprAnd.add(b1.getTree());
                            }

                            // AST REWRITE
                            // elements: b1, sExprOr
                            // token labels:
                            // rule labels: retval, b1
                            // token list labels:
                            // rule list labels:
                            // wildcard labels:
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                            RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                            root_0 = (Object) adaptor.nil();
                            // 1211:7: -> ^( SCALAR_AND_TAG $sExprOr $b1)
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1212:9: ^( SCALAR_AND_TAG $sExprOr $b1)
                                {
                                    Object root_1 = (Object) adaptor.nil();
                                    root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_AND_TAG, "SCALAR_AND_TAG"), root_1);
                                    adaptor.addChild(root_1, stream_retval.nextTree());
                                    adaptor.addChild(root_1, stream_b1.nextTree());
                                    adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;

                        }
                        break;

                        default:
                            break loop73;
                    }
                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "sExprAnd"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1216:1: sExprAnd : ( ( ( NOT sExprPredicate ) -> ^( SCALAR_NOT_TAG sExprPredicate ) ) | sExprPredicate );
    public final ValidationMlParser.sExprAnd_return sExprAnd() throws Exception {
        ValidationMlParser.sExprAnd_return retval = new ValidationMlParser.sExprAnd_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NOT425 = null;
        ParserRuleReturnScope sExprPredicate426 = null;
        ParserRuleReturnScope sExprPredicate427 = null;

        Object NOT425_tree = null;
        RewriteRuleTokenStream stream_NOT = new RewriteRuleTokenStream(adaptor, "token NOT");
        RewriteRuleSubtreeStream stream_sExprPredicate = new RewriteRuleSubtreeStream(adaptor, "rule sExprPredicate");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1217:3: ( ( ( NOT sExprPredicate ) -> ^( SCALAR_NOT_TAG sExprPredicate ) ) | sExprPredicate )
            int alt74 = 2;
            int LA74_0 = input.LA(1);
            if ((LA74_0 == NOT)) {
                alt74 = 1;
            } else if ((LA74_0 == ABS || LA74_0 == BOOLEAN_CONSTANT || LA74_0 == CONCAT || LA74_0 == EXP || LA74_0 == FLOAT_CONSTANT || LA74_0 == IDENTIFIER || LA74_0 == INTEGER_CONSTANT || LA74_0 == LCASE || LA74_0 == LEN || LA74_0 == LN || LA74_0 == MINUS || LA74_0 == MOD || LA74_0 == NULL_CONSTANT || LA74_0 == PLUS || LA74_0 == POWER || LA74_0 == ROUND || LA74_0 == STRING_CONSTANT || LA74_0 == SUBSTR || (LA74_0 >= TRIM && LA74_0 <= TRUNC) || LA74_0 == UCASE || LA74_0 == 426 || LA74_0 == 430)) {
                alt74 = 2;
            } else {
                NoViableAltException nvae =
                        new NoViableAltException("", 74, 0, input);
                throw nvae;
            }

            switch (alt74) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1218:3: ( ( NOT sExprPredicate ) -> ^( SCALAR_NOT_TAG sExprPredicate ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1218:3: ( ( NOT sExprPredicate ) -> ^( SCALAR_NOT_TAG sExprPredicate ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1219:5: ( NOT sExprPredicate )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1219:5: ( NOT sExprPredicate )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1219:6: NOT sExprPredicate
                        {
                            NOT425 = (Token) match(input, NOT, FOLLOW_NOT_in_sExprAnd10652);
                            stream_NOT.add(NOT425);

                            pushFollow(FOLLOW_sExprPredicate_in_sExprAnd10654);
                            sExprPredicate426 = sExprPredicate();
                            state._fsp--;

                            stream_sExprPredicate.add(sExprPredicate426.getTree());
                        }

                        // AST REWRITE
                        // elements: sExprPredicate
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1220:7: -> ^( SCALAR_NOT_TAG sExprPredicate )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1221:9: ^( SCALAR_NOT_TAG sExprPredicate )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_NOT_TAG, "SCALAR_NOT_TAG"), root_1);
                                adaptor.addChild(root_1, stream_sExprPredicate.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1223:5: sExprPredicate
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_sExprPredicate_in_sExprAnd10687);
                    sExprPredicate427 = sExprPredicate();
                    state._fsp--;

                    adaptor.addChild(root_0, sExprPredicate427.getTree());

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "sExprPredicate"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1226:1: sExprPredicate : ( scalarExpr -> scalarExpr ) ( ( ( compOpScalar b2= scalarExpr ) -> ^( compOpScalar $sExprPredicate $b2) ) | ( ( IN c2= setExpr ) -> ^( SCALAR_IN_SET_TAG $sExprPredicate $c2) ) | ( ( BETWEEN a1= scalarExpr AND a2= scalarExpr ) -> ^( SCALAR_BETWEEN_TAG $sExprPredicate $a1 $a2) ) | ( ( NOT IN c2= setExpr ) -> ^( SCALAR_NOT_IN_SET_TAG $sExprPredicate $c2) ) | ( ( NOT BETWEEN a1= scalarExpr AND a2= scalarExpr ) -> ^( SCALAR_NOT_BETWEEN_TAG $sExprPredicate $a1 $a2) ) )? ;
    public final ValidationMlParser.sExprPredicate_return sExprPredicate() throws Exception {
        ValidationMlParser.sExprPredicate_return retval = new ValidationMlParser.sExprPredicate_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IN430 = null;
        Token BETWEEN431 = null;
        Token AND432 = null;
        Token NOT433 = null;
        Token IN434 = null;
        Token NOT435 = null;
        Token BETWEEN436 = null;
        Token AND437 = null;
        ParserRuleReturnScope b2 = null;
        ParserRuleReturnScope c2 = null;
        ParserRuleReturnScope a1 = null;
        ParserRuleReturnScope a2 = null;
        ParserRuleReturnScope scalarExpr428 = null;
        ParserRuleReturnScope compOpScalar429 = null;

        Object IN430_tree = null;
        Object BETWEEN431_tree = null;
        Object AND432_tree = null;
        Object NOT433_tree = null;
        Object IN434_tree = null;
        Object NOT435_tree = null;
        Object BETWEEN436_tree = null;
        Object AND437_tree = null;
        RewriteRuleTokenStream stream_NOT = new RewriteRuleTokenStream(adaptor, "token NOT");
        RewriteRuleTokenStream stream_IN = new RewriteRuleTokenStream(adaptor, "token IN");
        RewriteRuleTokenStream stream_AND = new RewriteRuleTokenStream(adaptor, "token AND");
        RewriteRuleTokenStream stream_BETWEEN = new RewriteRuleTokenStream(adaptor, "token BETWEEN");
        RewriteRuleSubtreeStream stream_compOpScalar = new RewriteRuleSubtreeStream(adaptor, "rule compOpScalar");
        RewriteRuleSubtreeStream stream_scalarExpr = new RewriteRuleSubtreeStream(adaptor, "rule scalarExpr");
        RewriteRuleSubtreeStream stream_setExpr = new RewriteRuleSubtreeStream(adaptor, "rule setExpr");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1227:3: ( ( scalarExpr -> scalarExpr ) ( ( ( compOpScalar b2= scalarExpr ) -> ^( compOpScalar $sExprPredicate $b2) ) | ( ( IN c2= setExpr ) -> ^( SCALAR_IN_SET_TAG $sExprPredicate $c2) ) | ( ( BETWEEN a1= scalarExpr AND a2= scalarExpr ) -> ^( SCALAR_BETWEEN_TAG $sExprPredicate $a1 $a2) ) | ( ( NOT IN c2= setExpr ) -> ^( SCALAR_NOT_IN_SET_TAG $sExprPredicate $c2) ) | ( ( NOT BETWEEN a1= scalarExpr AND a2= scalarExpr ) -> ^( SCALAR_NOT_BETWEEN_TAG $sExprPredicate $a1 $a2) ) )? )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1228:3: ( scalarExpr -> scalarExpr ) ( ( ( compOpScalar b2= scalarExpr ) -> ^( compOpScalar $sExprPredicate $b2) ) | ( ( IN c2= setExpr ) -> ^( SCALAR_IN_SET_TAG $sExprPredicate $c2) ) | ( ( BETWEEN a1= scalarExpr AND a2= scalarExpr ) -> ^( SCALAR_BETWEEN_TAG $sExprPredicate $a1 $a2) ) | ( ( NOT IN c2= setExpr ) -> ^( SCALAR_NOT_IN_SET_TAG $sExprPredicate $c2) ) | ( ( NOT BETWEEN a1= scalarExpr AND a2= scalarExpr ) -> ^( SCALAR_NOT_BETWEEN_TAG $sExprPredicate $a1 $a2) ) )?
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1228:3: ( scalarExpr -> scalarExpr )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1228:4: scalarExpr
                {
                    pushFollow(FOLLOW_scalarExpr_in_sExprPredicate10703);
                    scalarExpr428 = scalarExpr();
                    state._fsp--;

                    stream_scalarExpr.add(scalarExpr428.getTree());
                    // AST REWRITE
                    // elements: scalarExpr
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1229:7: -> scalarExpr
                    {
                        adaptor.addChild(root_0, stream_scalarExpr.nextTree());
                    }


                    retval.tree = root_0;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1230:3: ( ( ( compOpScalar b2= scalarExpr ) -> ^( compOpScalar $sExprPredicate $b2) ) | ( ( IN c2= setExpr ) -> ^( SCALAR_IN_SET_TAG $sExprPredicate $c2) ) | ( ( BETWEEN a1= scalarExpr AND a2= scalarExpr ) -> ^( SCALAR_BETWEEN_TAG $sExprPredicate $a1 $a2) ) | ( ( NOT IN c2= setExpr ) -> ^( SCALAR_NOT_IN_SET_TAG $sExprPredicate $c2) ) | ( ( NOT BETWEEN a1= scalarExpr AND a2= scalarExpr ) -> ^( SCALAR_NOT_BETWEEN_TAG $sExprPredicate $a1 $a2) ) )?
                int alt75 = 6;
                switch (input.LA(1)) {
                    case EQ:
                    case GE:
                    case GT:
                    case LE:
                    case LT:
                    case NE: {
                        alt75 = 1;
                    }
                    break;
                    case IN: {
                        alt75 = 2;
                    }
                    break;
                    case BETWEEN: {
                        alt75 = 3;
                    }
                    break;
                    case NOT: {
                        int LA75_4 = input.LA(2);
                        if ((LA75_4 == IN)) {
                            alt75 = 4;
                        } else if ((LA75_4 == BETWEEN)) {
                            alt75 = 5;
                        }
                    }
                    break;
                }
                switch (alt75) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1231:5: ( ( compOpScalar b2= scalarExpr ) -> ^( compOpScalar $sExprPredicate $b2) )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1231:5: ( ( compOpScalar b2= scalarExpr ) -> ^( compOpScalar $sExprPredicate $b2) )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1232:7: ( compOpScalar b2= scalarExpr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1232:7: ( compOpScalar b2= scalarExpr )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1232:8: compOpScalar b2= scalarExpr
                            {
                                pushFollow(FOLLOW_compOpScalar_in_sExprPredicate10733);
                                compOpScalar429 = compOpScalar();
                                state._fsp--;

                                stream_compOpScalar.add(compOpScalar429.getTree());
                                pushFollow(FOLLOW_scalarExpr_in_sExprPredicate10737);
                                b2 = scalarExpr();
                                state._fsp--;

                                stream_scalarExpr.add(b2.getTree());
                            }

                            // AST REWRITE
                            // elements: b2, compOpScalar, sExprPredicate
                            // token labels:
                            // rule labels: b2, retval
                            // token list labels:
                            // rule list labels:
                            // wildcard labels:
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                            RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                            root_0 = (Object) adaptor.nil();
                            // 1233:9: -> ^( compOpScalar $sExprPredicate $b2)
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1234:11: ^( compOpScalar $sExprPredicate $b2)
                                {
                                    Object root_1 = (Object) adaptor.nil();
                                    root_1 = (Object) adaptor.becomeRoot(stream_compOpScalar.nextNode(), root_1);
                                    adaptor.addChild(root_1, stream_retval.nextTree());
                                    adaptor.addChild(root_1, stream_b2.nextTree());
                                    adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;

                        }

                    }
                    break;
                    case 2:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1237:5: ( ( IN c2= setExpr ) -> ^( SCALAR_IN_SET_TAG $sExprPredicate $c2) )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1237:5: ( ( IN c2= setExpr ) -> ^( SCALAR_IN_SET_TAG $sExprPredicate $c2) )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1238:7: ( IN c2= setExpr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1238:7: ( IN c2= setExpr )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1238:8: IN c2= setExpr
                            {
                                IN430 = (Token) match(input, IN, FOLLOW_IN_in_sExprPredicate10795);
                                stream_IN.add(IN430);

                                pushFollow(FOLLOW_setExpr_in_sExprPredicate10799);
                                c2 = setExpr();
                                state._fsp--;

                                stream_setExpr.add(c2.getTree());
                            }

                            // AST REWRITE
                            // elements: sExprPredicate, c2
                            // token labels:
                            // rule labels: retval, c2
                            // token list labels:
                            // rule list labels:
                            // wildcard labels:
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                            RewriteRuleSubtreeStream stream_c2 = new RewriteRuleSubtreeStream(adaptor, "rule c2", c2 != null ? c2.getTree() : null);

                            root_0 = (Object) adaptor.nil();
                            // 1239:9: -> ^( SCALAR_IN_SET_TAG $sExprPredicate $c2)
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1240:11: ^( SCALAR_IN_SET_TAG $sExprPredicate $c2)
                                {
                                    Object root_1 = (Object) adaptor.nil();
                                    root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_IN_SET_TAG, "SCALAR_IN_SET_TAG"), root_1);
                                    adaptor.addChild(root_1, stream_retval.nextTree());
                                    adaptor.addChild(root_1, stream_c2.nextTree());
                                    adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;

                        }

                    }
                    break;
                    case 3:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1243:5: ( ( BETWEEN a1= scalarExpr AND a2= scalarExpr ) -> ^( SCALAR_BETWEEN_TAG $sExprPredicate $a1 $a2) )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1243:5: ( ( BETWEEN a1= scalarExpr AND a2= scalarExpr ) -> ^( SCALAR_BETWEEN_TAG $sExprPredicate $a1 $a2) )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1244:7: ( BETWEEN a1= scalarExpr AND a2= scalarExpr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1244:7: ( BETWEEN a1= scalarExpr AND a2= scalarExpr )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1244:8: BETWEEN a1= scalarExpr AND a2= scalarExpr
                            {
                                BETWEEN431 = (Token) match(input, BETWEEN, FOLLOW_BETWEEN_in_sExprPredicate10857);
                                stream_BETWEEN.add(BETWEEN431);

                                pushFollow(FOLLOW_scalarExpr_in_sExprPredicate10861);
                                a1 = scalarExpr();
                                state._fsp--;

                                stream_scalarExpr.add(a1.getTree());
                                AND432 = (Token) match(input, AND, FOLLOW_AND_in_sExprPredicate10863);
                                stream_AND.add(AND432);

                                pushFollow(FOLLOW_scalarExpr_in_sExprPredicate10867);
                                a2 = scalarExpr();
                                state._fsp--;

                                stream_scalarExpr.add(a2.getTree());
                            }

                            // AST REWRITE
                            // elements: sExprPredicate, a1, a2
                            // token labels:
                            // rule labels: a1, a2, retval
                            // token list labels:
                            // rule list labels:
                            // wildcard labels:
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_a1 = new RewriteRuleSubtreeStream(adaptor, "rule a1", a1 != null ? a1.getTree() : null);
                            RewriteRuleSubtreeStream stream_a2 = new RewriteRuleSubtreeStream(adaptor, "rule a2", a2 != null ? a2.getTree() : null);
                            RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                            root_0 = (Object) adaptor.nil();
                            // 1245:9: -> ^( SCALAR_BETWEEN_TAG $sExprPredicate $a1 $a2)
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1246:11: ^( SCALAR_BETWEEN_TAG $sExprPredicate $a1 $a2)
                                {
                                    Object root_1 = (Object) adaptor.nil();
                                    root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_BETWEEN_TAG, "SCALAR_BETWEEN_TAG"), root_1);
                                    adaptor.addChild(root_1, stream_retval.nextTree());
                                    adaptor.addChild(root_1, stream_a1.nextTree());
                                    adaptor.addChild(root_1, stream_a2.nextTree());
                                    adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;

                        }

                    }
                    break;
                    case 4:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1249:5: ( ( NOT IN c2= setExpr ) -> ^( SCALAR_NOT_IN_SET_TAG $sExprPredicate $c2) )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1249:5: ( ( NOT IN c2= setExpr ) -> ^( SCALAR_NOT_IN_SET_TAG $sExprPredicate $c2) )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1250:7: ( NOT IN c2= setExpr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1250:7: ( NOT IN c2= setExpr )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1250:8: NOT IN c2= setExpr
                            {
                                NOT433 = (Token) match(input, NOT, FOLLOW_NOT_in_sExprPredicate10928);
                                stream_NOT.add(NOT433);

                                IN434 = (Token) match(input, IN, FOLLOW_IN_in_sExprPredicate10930);
                                stream_IN.add(IN434);

                                pushFollow(FOLLOW_setExpr_in_sExprPredicate10934);
                                c2 = setExpr();
                                state._fsp--;

                                stream_setExpr.add(c2.getTree());
                            }

                            // AST REWRITE
                            // elements: sExprPredicate, c2
                            // token labels:
                            // rule labels: retval, c2
                            // token list labels:
                            // rule list labels:
                            // wildcard labels:
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                            RewriteRuleSubtreeStream stream_c2 = new RewriteRuleSubtreeStream(adaptor, "rule c2", c2 != null ? c2.getTree() : null);

                            root_0 = (Object) adaptor.nil();
                            // 1251:9: -> ^( SCALAR_NOT_IN_SET_TAG $sExprPredicate $c2)
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1252:11: ^( SCALAR_NOT_IN_SET_TAG $sExprPredicate $c2)
                                {
                                    Object root_1 = (Object) adaptor.nil();
                                    root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_NOT_IN_SET_TAG, "SCALAR_NOT_IN_SET_TAG"), root_1);
                                    adaptor.addChild(root_1, stream_retval.nextTree());
                                    adaptor.addChild(root_1, stream_c2.nextTree());
                                    adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;

                        }

                    }
                    break;
                    case 5:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1255:5: ( ( NOT BETWEEN a1= scalarExpr AND a2= scalarExpr ) -> ^( SCALAR_NOT_BETWEEN_TAG $sExprPredicate $a1 $a2) )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1255:5: ( ( NOT BETWEEN a1= scalarExpr AND a2= scalarExpr ) -> ^( SCALAR_NOT_BETWEEN_TAG $sExprPredicate $a1 $a2) )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1256:7: ( NOT BETWEEN a1= scalarExpr AND a2= scalarExpr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1256:7: ( NOT BETWEEN a1= scalarExpr AND a2= scalarExpr )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1256:8: NOT BETWEEN a1= scalarExpr AND a2= scalarExpr
                            {
                                NOT435 = (Token) match(input, NOT, FOLLOW_NOT_in_sExprPredicate10992);
                                stream_NOT.add(NOT435);

                                BETWEEN436 = (Token) match(input, BETWEEN, FOLLOW_BETWEEN_in_sExprPredicate10994);
                                stream_BETWEEN.add(BETWEEN436);

                                pushFollow(FOLLOW_scalarExpr_in_sExprPredicate10998);
                                a1 = scalarExpr();
                                state._fsp--;

                                stream_scalarExpr.add(a1.getTree());
                                AND437 = (Token) match(input, AND, FOLLOW_AND_in_sExprPredicate11000);
                                stream_AND.add(AND437);

                                pushFollow(FOLLOW_scalarExpr_in_sExprPredicate11004);
                                a2 = scalarExpr();
                                state._fsp--;

                                stream_scalarExpr.add(a2.getTree());
                            }

                            // AST REWRITE
                            // elements: a2, a1, sExprPredicate
                            // token labels:
                            // rule labels: a1, a2, retval
                            // token list labels:
                            // rule list labels:
                            // wildcard labels:
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_a1 = new RewriteRuleSubtreeStream(adaptor, "rule a1", a1 != null ? a1.getTree() : null);
                            RewriteRuleSubtreeStream stream_a2 = new RewriteRuleSubtreeStream(adaptor, "rule a2", a2 != null ? a2.getTree() : null);
                            RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                            root_0 = (Object) adaptor.nil();
                            // 1257:9: -> ^( SCALAR_NOT_BETWEEN_TAG $sExprPredicate $a1 $a2)
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1258:11: ^( SCALAR_NOT_BETWEEN_TAG $sExprPredicate $a1 $a2)
                                {
                                    Object root_1 = (Object) adaptor.nil();
                                    root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_NOT_BETWEEN_TAG, "SCALAR_NOT_BETWEEN_TAG"), root_1);
                                    adaptor.addChild(root_1, stream_retval.nextTree());
                                    adaptor.addChild(root_1, stream_a1.nextTree());
                                    adaptor.addChild(root_1, stream_a2.nextTree());
                                    adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;

                        }

                    }
                    break;

                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "scalarExpr"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1263:1: scalarExpr : ( sExprAdd -> sExprAdd ) ( ( ( PLUS b2= sExprAdd ) -> ^( SCALAR_PLUS_TAG $scalarExpr $b2) ) | ( ( MINUS b2= sExprAdd ) -> ^( SCALAR_MINUS_TAG $scalarExpr $b2) ) )* ;
    public final ValidationMlParser.scalarExpr_return scalarExpr() throws Exception {
        ValidationMlParser.scalarExpr_return retval = new ValidationMlParser.scalarExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PLUS439 = null;
        Token MINUS440 = null;
        ParserRuleReturnScope b2 = null;
        ParserRuleReturnScope sExprAdd438 = null;

        Object PLUS439_tree = null;
        Object MINUS440_tree = null;
        RewriteRuleTokenStream stream_PLUS = new RewriteRuleTokenStream(adaptor, "token PLUS");
        RewriteRuleTokenStream stream_MINUS = new RewriteRuleTokenStream(adaptor, "token MINUS");
        RewriteRuleSubtreeStream stream_sExprAdd = new RewriteRuleSubtreeStream(adaptor, "rule sExprAdd");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1264:3: ( ( sExprAdd -> sExprAdd ) ( ( ( PLUS b2= sExprAdd ) -> ^( SCALAR_PLUS_TAG $scalarExpr $b2) ) | ( ( MINUS b2= sExprAdd ) -> ^( SCALAR_MINUS_TAG $scalarExpr $b2) ) )* )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1265:3: ( sExprAdd -> sExprAdd ) ( ( ( PLUS b2= sExprAdd ) -> ^( SCALAR_PLUS_TAG $scalarExpr $b2) ) | ( ( MINUS b2= sExprAdd ) -> ^( SCALAR_MINUS_TAG $scalarExpr $b2) ) )*
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1265:3: ( sExprAdd -> sExprAdd )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1265:4: sExprAdd
                {
                    pushFollow(FOLLOW_sExprAdd_in_scalarExpr11065);
                    sExprAdd438 = sExprAdd();
                    state._fsp--;

                    stream_sExprAdd.add(sExprAdd438.getTree());
                    // AST REWRITE
                    // elements: sExprAdd
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1266:7: -> sExprAdd
                    {
                        adaptor.addChild(root_0, stream_sExprAdd.nextTree());
                    }


                    retval.tree = root_0;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1267:3: ( ( ( PLUS b2= sExprAdd ) -> ^( SCALAR_PLUS_TAG $scalarExpr $b2) ) | ( ( MINUS b2= sExprAdd ) -> ^( SCALAR_MINUS_TAG $scalarExpr $b2) ) )*
                loop76:
                while (true) {
                    int alt76 = 3;
                    int LA76_0 = input.LA(1);
                    if ((LA76_0 == PLUS)) {
                        alt76 = 1;
                    } else if ((LA76_0 == MINUS)) {
                        alt76 = 2;
                    }

                    switch (alt76) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1268:5: ( ( PLUS b2= sExprAdd ) -> ^( SCALAR_PLUS_TAG $scalarExpr $b2) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1268:5: ( ( PLUS b2= sExprAdd ) -> ^( SCALAR_PLUS_TAG $scalarExpr $b2) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1269:7: ( PLUS b2= sExprAdd )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1269:7: ( PLUS b2= sExprAdd )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1269:8: PLUS b2= sExprAdd
                                {
                                    PLUS439 = (Token) match(input, PLUS, FOLLOW_PLUS_in_scalarExpr11095);
                                    stream_PLUS.add(PLUS439);

                                    pushFollow(FOLLOW_sExprAdd_in_scalarExpr11099);
                                    b2 = sExprAdd();
                                    state._fsp--;

                                    stream_sExprAdd.add(b2.getTree());
                                }

                                // AST REWRITE
                                // elements: scalarExpr, b2
                                // token labels:
                                // rule labels: b2, retval
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 1270:9: -> ^( SCALAR_PLUS_TAG $scalarExpr $b2)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1271:11: ^( SCALAR_PLUS_TAG $scalarExpr $b2)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_PLUS_TAG, "SCALAR_PLUS_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_b2.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;
                        case 2:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1274:5: ( ( MINUS b2= sExprAdd ) -> ^( SCALAR_MINUS_TAG $scalarExpr $b2) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1274:5: ( ( MINUS b2= sExprAdd ) -> ^( SCALAR_MINUS_TAG $scalarExpr $b2) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1275:7: ( MINUS b2= sExprAdd )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1275:7: ( MINUS b2= sExprAdd )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1275:8: MINUS b2= sExprAdd
                                {
                                    MINUS440 = (Token) match(input, MINUS, FOLLOW_MINUS_in_scalarExpr11157);
                                    stream_MINUS.add(MINUS440);

                                    pushFollow(FOLLOW_sExprAdd_in_scalarExpr11161);
                                    b2 = sExprAdd();
                                    state._fsp--;

                                    stream_sExprAdd.add(b2.getTree());
                                }

                                // AST REWRITE
                                // elements: b2, scalarExpr
                                // token labels:
                                // rule labels: b2, retval
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 1276:9: -> ^( SCALAR_MINUS_TAG $scalarExpr $b2)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1277:11: ^( SCALAR_MINUS_TAG $scalarExpr $b2)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_MINUS_TAG, "SCALAR_MINUS_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_b2.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;

                        default:
                            break loop76;
                    }
                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "sExprAdd"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1282:1: sExprAdd : ( sExprFactor -> sExprFactor ) ( ( ( DIVIDE b2= sExprFactor ) -> ^( SCALAR_DIVIDE_TAG $sExprAdd $b2) ) | ( ( MULTIPLY b2= sExprFactor ) -> ^( SCALAR_MULTIPLY_TAG $sExprAdd $b2) ) | ( ( PERCENT b2= sExprFactor ) -> ^( SCALAR_PERCENT_TAG $sExprAdd $b2) ) )* ;
    public final ValidationMlParser.sExprAdd_return sExprAdd() throws Exception {
        ValidationMlParser.sExprAdd_return retval = new ValidationMlParser.sExprAdd_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DIVIDE442 = null;
        Token MULTIPLY443 = null;
        Token PERCENT444 = null;
        ParserRuleReturnScope b2 = null;
        ParserRuleReturnScope sExprFactor441 = null;

        Object DIVIDE442_tree = null;
        Object MULTIPLY443_tree = null;
        Object PERCENT444_tree = null;
        RewriteRuleTokenStream stream_PERCENT = new RewriteRuleTokenStream(adaptor, "token PERCENT");
        RewriteRuleTokenStream stream_MULTIPLY = new RewriteRuleTokenStream(adaptor, "token MULTIPLY");
        RewriteRuleTokenStream stream_DIVIDE = new RewriteRuleTokenStream(adaptor, "token DIVIDE");
        RewriteRuleSubtreeStream stream_sExprFactor = new RewriteRuleSubtreeStream(adaptor, "rule sExprFactor");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1283:3: ( ( sExprFactor -> sExprFactor ) ( ( ( DIVIDE b2= sExprFactor ) -> ^( SCALAR_DIVIDE_TAG $sExprAdd $b2) ) | ( ( MULTIPLY b2= sExprFactor ) -> ^( SCALAR_MULTIPLY_TAG $sExprAdd $b2) ) | ( ( PERCENT b2= sExprFactor ) -> ^( SCALAR_PERCENT_TAG $sExprAdd $b2) ) )* )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1284:3: ( sExprFactor -> sExprFactor ) ( ( ( DIVIDE b2= sExprFactor ) -> ^( SCALAR_DIVIDE_TAG $sExprAdd $b2) ) | ( ( MULTIPLY b2= sExprFactor ) -> ^( SCALAR_MULTIPLY_TAG $sExprAdd $b2) ) | ( ( PERCENT b2= sExprFactor ) -> ^( SCALAR_PERCENT_TAG $sExprAdd $b2) ) )*
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1284:3: ( sExprFactor -> sExprFactor )
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1284:4: sExprFactor
                {
                    pushFollow(FOLLOW_sExprFactor_in_sExprAdd11219);
                    sExprFactor441 = sExprFactor();
                    state._fsp--;

                    stream_sExprFactor.add(sExprFactor441.getTree());
                    // AST REWRITE
                    // elements: sExprFactor
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1285:7: -> sExprFactor
                    {
                        adaptor.addChild(root_0, stream_sExprFactor.nextTree());
                    }


                    retval.tree = root_0;

                }

                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1286:3: ( ( ( DIVIDE b2= sExprFactor ) -> ^( SCALAR_DIVIDE_TAG $sExprAdd $b2) ) | ( ( MULTIPLY b2= sExprFactor ) -> ^( SCALAR_MULTIPLY_TAG $sExprAdd $b2) ) | ( ( PERCENT b2= sExprFactor ) -> ^( SCALAR_PERCENT_TAG $sExprAdd $b2) ) )*
                loop77:
                while (true) {
                    int alt77 = 4;
                    switch (input.LA(1)) {
                        case DIVIDE: {
                            alt77 = 1;
                        }
                        break;
                        case MULTIPLY: {
                            alt77 = 2;
                        }
                        break;
                        case PERCENT: {
                            alt77 = 3;
                        }
                        break;
                    }
                    switch (alt77) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1287:5: ( ( DIVIDE b2= sExprFactor ) -> ^( SCALAR_DIVIDE_TAG $sExprAdd $b2) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1287:5: ( ( DIVIDE b2= sExprFactor ) -> ^( SCALAR_DIVIDE_TAG $sExprAdd $b2) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1288:7: ( DIVIDE b2= sExprFactor )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1288:7: ( DIVIDE b2= sExprFactor )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1288:8: DIVIDE b2= sExprFactor
                                {
                                    DIVIDE442 = (Token) match(input, DIVIDE, FOLLOW_DIVIDE_in_sExprAdd11249);
                                    stream_DIVIDE.add(DIVIDE442);

                                    pushFollow(FOLLOW_sExprFactor_in_sExprAdd11253);
                                    b2 = sExprFactor();
                                    state._fsp--;

                                    stream_sExprFactor.add(b2.getTree());
                                }

                                // AST REWRITE
                                // elements: sExprAdd, b2
                                // token labels:
                                // rule labels: b2, retval
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 1289:9: -> ^( SCALAR_DIVIDE_TAG $sExprAdd $b2)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1290:11: ^( SCALAR_DIVIDE_TAG $sExprAdd $b2)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_DIVIDE_TAG, "SCALAR_DIVIDE_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_b2.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;
                        case 2:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1293:5: ( ( MULTIPLY b2= sExprFactor ) -> ^( SCALAR_MULTIPLY_TAG $sExprAdd $b2) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1293:5: ( ( MULTIPLY b2= sExprFactor ) -> ^( SCALAR_MULTIPLY_TAG $sExprAdd $b2) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1294:7: ( MULTIPLY b2= sExprFactor )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1294:7: ( MULTIPLY b2= sExprFactor )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1294:8: MULTIPLY b2= sExprFactor
                                {
                                    MULTIPLY443 = (Token) match(input, MULTIPLY, FOLLOW_MULTIPLY_in_sExprAdd11311);
                                    stream_MULTIPLY.add(MULTIPLY443);

                                    pushFollow(FOLLOW_sExprFactor_in_sExprAdd11315);
                                    b2 = sExprFactor();
                                    state._fsp--;

                                    stream_sExprFactor.add(b2.getTree());
                                }

                                // AST REWRITE
                                // elements: sExprAdd, b2
                                // token labels:
                                // rule labels: b2, retval
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 1295:9: -> ^( SCALAR_MULTIPLY_TAG $sExprAdd $b2)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1296:11: ^( SCALAR_MULTIPLY_TAG $sExprAdd $b2)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_MULTIPLY_TAG, "SCALAR_MULTIPLY_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_b2.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;
                        case 3:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1299:5: ( ( PERCENT b2= sExprFactor ) -> ^( SCALAR_PERCENT_TAG $sExprAdd $b2) )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1299:5: ( ( PERCENT b2= sExprFactor ) -> ^( SCALAR_PERCENT_TAG $sExprAdd $b2) )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1300:7: ( PERCENT b2= sExprFactor )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1300:7: ( PERCENT b2= sExprFactor )
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1300:8: PERCENT b2= sExprFactor
                                {
                                    PERCENT444 = (Token) match(input, PERCENT, FOLLOW_PERCENT_in_sExprAdd11373);
                                    stream_PERCENT.add(PERCENT444);

                                    pushFollow(FOLLOW_sExprFactor_in_sExprAdd11377);
                                    b2 = sExprFactor();
                                    state._fsp--;

                                    stream_sExprFactor.add(b2.getTree());
                                }

                                // AST REWRITE
                                // elements: b2, sExprAdd
                                // token labels:
                                // rule labels: b2, retval
                                // token list labels:
                                // rule list labels:
                                // wildcard labels:
                                retval.tree = root_0;
                                RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                                root_0 = (Object) adaptor.nil();
                                // 1301:9: -> ^( SCALAR_PERCENT_TAG $sExprAdd $b2)
                                {
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1302:11: ^( SCALAR_PERCENT_TAG $sExprAdd $b2)
                                    {
                                        Object root_1 = (Object) adaptor.nil();
                                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_PERCENT_TAG, "SCALAR_PERCENT_TAG"), root_1);
                                        adaptor.addChild(root_1, stream_retval.nextTree());
                                        adaptor.addChild(root_1, stream_b2.nextTree());
                                        adaptor.addChild(root_0, root_1);
                                    }

                                }


                                retval.tree = root_0;

                            }

                        }
                        break;

                        default:
                            break loop77;
                    }
                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "sExprFactor"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1307:1: sExprFactor : ( ( sExprAtom -> sExprAtom ) | ( ( PLUS sExprAtom ) -> sExprAtom ) | ( ( MINUS sExprAtom ) -> ^( SCALAR_MINUS_UNARY_TAG sExprAtom ) ) ) ;
    public final ValidationMlParser.sExprFactor_return sExprFactor() throws Exception {
        ValidationMlParser.sExprFactor_return retval = new ValidationMlParser.sExprFactor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PLUS446 = null;
        Token MINUS448 = null;
        ParserRuleReturnScope sExprAtom445 = null;
        ParserRuleReturnScope sExprAtom447 = null;
        ParserRuleReturnScope sExprAtom449 = null;

        Object PLUS446_tree = null;
        Object MINUS448_tree = null;
        RewriteRuleTokenStream stream_PLUS = new RewriteRuleTokenStream(adaptor, "token PLUS");
        RewriteRuleTokenStream stream_MINUS = new RewriteRuleTokenStream(adaptor, "token MINUS");
        RewriteRuleSubtreeStream stream_sExprAtom = new RewriteRuleSubtreeStream(adaptor, "rule sExprAtom");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1308:3: ( ( ( sExprAtom -> sExprAtom ) | ( ( PLUS sExprAtom ) -> sExprAtom ) | ( ( MINUS sExprAtom ) -> ^( SCALAR_MINUS_UNARY_TAG sExprAtom ) ) ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1309:3: ( ( sExprAtom -> sExprAtom ) | ( ( PLUS sExprAtom ) -> sExprAtom ) | ( ( MINUS sExprAtom ) -> ^( SCALAR_MINUS_UNARY_TAG sExprAtom ) ) )
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1309:3: ( ( sExprAtom -> sExprAtom ) | ( ( PLUS sExprAtom ) -> sExprAtom ) | ( ( MINUS sExprAtom ) -> ^( SCALAR_MINUS_UNARY_TAG sExprAtom ) ) )
                int alt78 = 3;
                switch (input.LA(1)) {
                    case ABS:
                    case BOOLEAN_CONSTANT:
                    case CONCAT:
                    case EXP:
                    case FLOAT_CONSTANT:
                    case IDENTIFIER:
                    case INTEGER_CONSTANT:
                    case LCASE:
                    case LEN:
                    case LN:
                    case MOD:
                    case NULL_CONSTANT:
                    case POWER:
                    case ROUND:
                    case STRING_CONSTANT:
                    case SUBSTR:
                    case TRIM:
                    case TRUNC:
                    case UCASE:
                    case 426:
                    case 430: {
                        alt78 = 1;
                    }
                    break;
                    case PLUS: {
                        alt78 = 2;
                    }
                    break;
                    case MINUS: {
                        alt78 = 3;
                    }
                    break;
                    default:
                        NoViableAltException nvae =
                                new NoViableAltException("", 78, 0, input);
                        throw nvae;
                }
                switch (alt78) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1310:5: ( sExprAtom -> sExprAtom )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1310:5: ( sExprAtom -> sExprAtom )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1310:6: sExprAtom
                        {
                            pushFollow(FOLLOW_sExprAtom_in_sExprFactor11441);
                            sExprAtom445 = sExprAtom();
                            state._fsp--;

                            stream_sExprAtom.add(sExprAtom445.getTree());
                            // AST REWRITE
                            // elements: sExprAtom
                            // token labels:
                            // rule labels: retval
                            // token list labels:
                            // rule list labels:
                            // wildcard labels:
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                            root_0 = (Object) adaptor.nil();
                            // 1311:9: -> sExprAtom
                            {
                                adaptor.addChild(root_0, stream_sExprAtom.nextTree());
                            }


                            retval.tree = root_0;

                        }

                    }
                    break;
                    case 2:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1313:5: ( ( PLUS sExprAtom ) -> sExprAtom )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1313:5: ( ( PLUS sExprAtom ) -> sExprAtom )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1314:7: ( PLUS sExprAtom )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1314:7: ( PLUS sExprAtom )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1314:8: PLUS sExprAtom
                            {
                                PLUS446 = (Token) match(input, PLUS, FOLLOW_PLUS_in_sExprFactor11475);
                                stream_PLUS.add(PLUS446);

                                pushFollow(FOLLOW_sExprAtom_in_sExprFactor11477);
                                sExprAtom447 = sExprAtom();
                                state._fsp--;

                                stream_sExprAtom.add(sExprAtom447.getTree());
                            }

                            // AST REWRITE
                            // elements: sExprAtom
                            // token labels:
                            // rule labels: retval
                            // token list labels:
                            // rule list labels:
                            // wildcard labels:
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                            root_0 = (Object) adaptor.nil();
                            // 1315:9: -> sExprAtom
                            {
                                adaptor.addChild(root_0, stream_sExprAtom.nextTree());
                            }


                            retval.tree = root_0;

                        }

                    }
                    break;
                    case 3:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1318:5: ( ( MINUS sExprAtom ) -> ^( SCALAR_MINUS_UNARY_TAG sExprAtom ) )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1318:5: ( ( MINUS sExprAtom ) -> ^( SCALAR_MINUS_UNARY_TAG sExprAtom ) )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1319:7: ( MINUS sExprAtom )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1319:7: ( MINUS sExprAtom )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1319:8: MINUS sExprAtom
                            {
                                MINUS448 = (Token) match(input, MINUS, FOLLOW_MINUS_in_sExprFactor11517);
                                stream_MINUS.add(MINUS448);

                                pushFollow(FOLLOW_sExprAtom_in_sExprFactor11519);
                                sExprAtom449 = sExprAtom();
                                state._fsp--;

                                stream_sExprAtom.add(sExprAtom449.getTree());
                            }

                            // AST REWRITE
                            // elements: sExprAtom
                            // token labels:
                            // rule labels: retval
                            // token list labels:
                            // rule list labels:
                            // wildcard labels:
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                            root_0 = (Object) adaptor.nil();
                            // 1320:9: -> ^( SCALAR_MINUS_UNARY_TAG sExprAtom )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1321:11: ^( SCALAR_MINUS_UNARY_TAG sExprAtom )
                                {
                                    Object root_1 = (Object) adaptor.nil();
                                    root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_MINUS_UNARY_TAG, "SCALAR_MINUS_UNARY_TAG"), root_1);
                                    adaptor.addChild(root_1, stream_sExprAtom.nextTree());
                                    adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;

                        }

                    }
                    break;

                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "sExprAtom"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1326:1: sExprAtom : ( varID | constant | '(' ! bScalarExpr ')' !| ( LEN '(' scalarExpr ')' -> ^( SCALAR_LEN_TAG scalarExpr ) ) | ( CONCAT '(' b1= scalarExpr ( ',' b2= scalarExpr )+ ')' -> ^( SCALAR_CONCAT_TAG $b1 ( $b2)+ ) ) | ( TRIM '(' scalarExpr ')' -> ^( SCALAR_TRIM_TAG scalarExpr ) ) | ( UCASE '(' scalarExpr ')' -> ^( SCALAR_UCASE_TAG scalarExpr ) ) | ( LCASE '(' scalarExpr ')' -> ^( SCALAR_LCASE_TAG scalarExpr ) ) | ( SUBSTR '(' b1= scalarExpr ',' b2= scalarExpr ',' b3= scalarExpr ')' -> ^( SCALAR_SUBSTR_TAG $b1 $b2 $b3) ) | ( ROUND '(' scalarExpr ',' INTEGER_CONSTANT ')' -> ^( SCALAR_ROUND_TAG scalarExpr INTEGER_CONSTANT ) ) | ( TRUNC '(' scalarExpr ')' -> ^( SCALAR_TRUNC_TAG scalarExpr ) ) | ( LN '(' scalarExpr ')' -> ^( SCALAR_LEN_TAG scalarExpr ) ) | ( EXP '(' scalarExpr ')' -> ^( SCALAR_EXP_TAG scalarExpr ) ) | ( MOD '(' scalarExpr ',' INTEGER_CONSTANT ')' -> ^( SCALAR_MOD_TAG scalarExpr INTEGER_CONSTANT ) ) | ( ABS '(' scalarExpr ')' -> ^( SCALAR_ABS_TAG scalarExpr ) ) | ( POWER '(' scalarExpr ',' powerExponent ')' -> ^( SCALAR_POWER_TAG scalarExpr powerExponent ) ) | (sys= 'SYSTIMESTAMP' ) -> ^( SYSTIMESTAMP_TAG $sys) );
    public final ValidationMlParser.sExprAtom_return sExprAtom() throws Exception {
        ValidationMlParser.sExprAtom_return retval = new ValidationMlParser.sExprAtom_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token sys = null;
        Token char_literal452 = null;
        Token char_literal454 = null;
        Token LEN455 = null;
        Token char_literal456 = null;
        Token char_literal458 = null;
        Token CONCAT459 = null;
        Token char_literal460 = null;
        Token char_literal461 = null;
        Token char_literal462 = null;
        Token TRIM463 = null;
        Token char_literal464 = null;
        Token char_literal466 = null;
        Token UCASE467 = null;
        Token char_literal468 = null;
        Token char_literal470 = null;
        Token LCASE471 = null;
        Token char_literal472 = null;
        Token char_literal474 = null;
        Token SUBSTR475 = null;
        Token char_literal476 = null;
        Token char_literal477 = null;
        Token char_literal478 = null;
        Token char_literal479 = null;
        Token ROUND480 = null;
        Token char_literal481 = null;
        Token char_literal483 = null;
        Token INTEGER_CONSTANT484 = null;
        Token char_literal485 = null;
        Token TRUNC486 = null;
        Token char_literal487 = null;
        Token char_literal489 = null;
        Token LN490 = null;
        Token char_literal491 = null;
        Token char_literal493 = null;
        Token EXP494 = null;
        Token char_literal495 = null;
        Token char_literal497 = null;
        Token MOD498 = null;
        Token char_literal499 = null;
        Token char_literal501 = null;
        Token INTEGER_CONSTANT502 = null;
        Token char_literal503 = null;
        Token ABS504 = null;
        Token char_literal505 = null;
        Token char_literal507 = null;
        Token POWER508 = null;
        Token char_literal509 = null;
        Token char_literal511 = null;
        Token char_literal513 = null;
        ParserRuleReturnScope b1 = null;
        ParserRuleReturnScope b2 = null;
        ParserRuleReturnScope b3 = null;
        ParserRuleReturnScope varID450 = null;
        ParserRuleReturnScope constant451 = null;
        ParserRuleReturnScope bScalarExpr453 = null;
        ParserRuleReturnScope scalarExpr457 = null;
        ParserRuleReturnScope scalarExpr465 = null;
        ParserRuleReturnScope scalarExpr469 = null;
        ParserRuleReturnScope scalarExpr473 = null;
        ParserRuleReturnScope scalarExpr482 = null;
        ParserRuleReturnScope scalarExpr488 = null;
        ParserRuleReturnScope scalarExpr492 = null;
        ParserRuleReturnScope scalarExpr496 = null;
        ParserRuleReturnScope scalarExpr500 = null;
        ParserRuleReturnScope scalarExpr506 = null;
        ParserRuleReturnScope scalarExpr510 = null;
        ParserRuleReturnScope powerExponent512 = null;

        Object sys_tree = null;
        Object char_literal452_tree = null;
        Object char_literal454_tree = null;
        Object LEN455_tree = null;
        Object char_literal456_tree = null;
        Object char_literal458_tree = null;
        Object CONCAT459_tree = null;
        Object char_literal460_tree = null;
        Object char_literal461_tree = null;
        Object char_literal462_tree = null;
        Object TRIM463_tree = null;
        Object char_literal464_tree = null;
        Object char_literal466_tree = null;
        Object UCASE467_tree = null;
        Object char_literal468_tree = null;
        Object char_literal470_tree = null;
        Object LCASE471_tree = null;
        Object char_literal472_tree = null;
        Object char_literal474_tree = null;
        Object SUBSTR475_tree = null;
        Object char_literal476_tree = null;
        Object char_literal477_tree = null;
        Object char_literal478_tree = null;
        Object char_literal479_tree = null;
        Object ROUND480_tree = null;
        Object char_literal481_tree = null;
        Object char_literal483_tree = null;
        Object INTEGER_CONSTANT484_tree = null;
        Object char_literal485_tree = null;
        Object TRUNC486_tree = null;
        Object char_literal487_tree = null;
        Object char_literal489_tree = null;
        Object LN490_tree = null;
        Object char_literal491_tree = null;
        Object char_literal493_tree = null;
        Object EXP494_tree = null;
        Object char_literal495_tree = null;
        Object char_literal497_tree = null;
        Object MOD498_tree = null;
        Object char_literal499_tree = null;
        Object char_literal501_tree = null;
        Object INTEGER_CONSTANT502_tree = null;
        Object char_literal503_tree = null;
        Object ABS504_tree = null;
        Object char_literal505_tree = null;
        Object char_literal507_tree = null;
        Object POWER508_tree = null;
        Object char_literal509_tree = null;
        Object char_literal511_tree = null;
        Object char_literal513_tree = null;
        RewriteRuleTokenStream stream_LN = new RewriteRuleTokenStream(adaptor, "token LN");
        RewriteRuleTokenStream stream_MOD = new RewriteRuleTokenStream(adaptor, "token MOD");
        RewriteRuleTokenStream stream_TRIM = new RewriteRuleTokenStream(adaptor, "token TRIM");
        RewriteRuleTokenStream stream_CONCAT = new RewriteRuleTokenStream(adaptor, "token CONCAT");
        RewriteRuleTokenStream stream_ROUND = new RewriteRuleTokenStream(adaptor, "token ROUND");
        RewriteRuleTokenStream stream_UCASE = new RewriteRuleTokenStream(adaptor, "token UCASE");
        RewriteRuleTokenStream stream_POWER = new RewriteRuleTokenStream(adaptor, "token POWER");
        RewriteRuleTokenStream stream_TRUNC = new RewriteRuleTokenStream(adaptor, "token TRUNC");
        RewriteRuleTokenStream stream_INTEGER_CONSTANT = new RewriteRuleTokenStream(adaptor, "token INTEGER_CONSTANT");
        RewriteRuleTokenStream stream_430 = new RewriteRuleTokenStream(adaptor, "token 430");
        RewriteRuleTokenStream stream_LCASE = new RewriteRuleTokenStream(adaptor, "token LCASE");
        RewriteRuleTokenStream stream_ABS = new RewriteRuleTokenStream(adaptor, "token ABS");
        RewriteRuleTokenStream stream_LEN = new RewriteRuleTokenStream(adaptor, "token LEN");
        RewriteRuleTokenStream stream_426 = new RewriteRuleTokenStream(adaptor, "token 426");
        RewriteRuleTokenStream stream_427 = new RewriteRuleTokenStream(adaptor, "token 427");
        RewriteRuleTokenStream stream_CARTESIAN_PER = new RewriteRuleTokenStream(adaptor, "token CARTESIAN_PER");
        RewriteRuleTokenStream stream_EXP = new RewriteRuleTokenStream(adaptor, "token EXP");
        RewriteRuleTokenStream stream_SUBSTR = new RewriteRuleTokenStream(adaptor, "token SUBSTR");
        RewriteRuleSubtreeStream stream_powerExponent = new RewriteRuleSubtreeStream(adaptor, "rule powerExponent");
        RewriteRuleSubtreeStream stream_scalarExpr = new RewriteRuleSubtreeStream(adaptor, "rule scalarExpr");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1327:3: ( varID | constant | '(' ! bScalarExpr ')' !| ( LEN '(' scalarExpr ')' -> ^( SCALAR_LEN_TAG scalarExpr ) ) | ( CONCAT '(' b1= scalarExpr ( ',' b2= scalarExpr )+ ')' -> ^( SCALAR_CONCAT_TAG $b1 ( $b2)+ ) ) | ( TRIM '(' scalarExpr ')' -> ^( SCALAR_TRIM_TAG scalarExpr ) ) | ( UCASE '(' scalarExpr ')' -> ^( SCALAR_UCASE_TAG scalarExpr ) ) | ( LCASE '(' scalarExpr ')' -> ^( SCALAR_LCASE_TAG scalarExpr ) ) | ( SUBSTR '(' b1= scalarExpr ',' b2= scalarExpr ',' b3= scalarExpr ')' -> ^( SCALAR_SUBSTR_TAG $b1 $b2 $b3) ) | ( ROUND '(' scalarExpr ',' INTEGER_CONSTANT ')' -> ^( SCALAR_ROUND_TAG scalarExpr INTEGER_CONSTANT ) ) | ( TRUNC '(' scalarExpr ')' -> ^( SCALAR_TRUNC_TAG scalarExpr ) ) | ( LN '(' scalarExpr ')' -> ^( SCALAR_LEN_TAG scalarExpr ) ) | ( EXP '(' scalarExpr ')' -> ^( SCALAR_EXP_TAG scalarExpr ) ) | ( MOD '(' scalarExpr ',' INTEGER_CONSTANT ')' -> ^( SCALAR_MOD_TAG scalarExpr INTEGER_CONSTANT ) ) | ( ABS '(' scalarExpr ')' -> ^( SCALAR_ABS_TAG scalarExpr ) ) | ( POWER '(' scalarExpr ',' powerExponent ')' -> ^( SCALAR_POWER_TAG scalarExpr powerExponent ) ) | (sys= 'SYSTIMESTAMP' ) -> ^( SYSTIMESTAMP_TAG $sys) )
            int alt80 = 17;
            switch (input.LA(1)) {
                case IDENTIFIER: {
                    alt80 = 1;
                }
                break;
                case BOOLEAN_CONSTANT:
                case FLOAT_CONSTANT:
                case INTEGER_CONSTANT:
                case NULL_CONSTANT:
                case STRING_CONSTANT: {
                    alt80 = 2;
                }
                break;
                case 426: {
                    alt80 = 3;
                }
                break;
                case LEN: {
                    alt80 = 4;
                }
                break;
                case CONCAT: {
                    alt80 = 5;
                }
                break;
                case TRIM: {
                    alt80 = 6;
                }
                break;
                case UCASE: {
                    alt80 = 7;
                }
                break;
                case LCASE: {
                    alt80 = 8;
                }
                break;
                case SUBSTR: {
                    alt80 = 9;
                }
                break;
                case ROUND: {
                    alt80 = 10;
                }
                break;
                case TRUNC: {
                    alt80 = 11;
                }
                break;
                case LN: {
                    alt80 = 12;
                }
                break;
                case EXP: {
                    alt80 = 13;
                }
                break;
                case MOD: {
                    alt80 = 14;
                }
                break;
                case ABS: {
                    alt80 = 15;
                }
                break;
                case POWER: {
                    alt80 = 16;
                }
                break;
                case 430: {
                    alt80 = 17;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 80, 0, input);
                    throw nvae;
            }
            switch (alt80) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1328:3: varID
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_varID_in_sExprAtom11571);
                    varID450 = varID();
                    state._fsp--;

                    adaptor.addChild(root_0, varID450.getTree());

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1329:5: constant
                {
                    root_0 = (Object) adaptor.nil();


                    pushFollow(FOLLOW_constant_in_sExprAtom11577);
                    constant451 = constant();
                    state._fsp--;

                    adaptor.addChild(root_0, constant451.getTree());

                }
                break;
                case 3:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1330:5: '(' ! bScalarExpr ')' !
                {
                    root_0 = (Object) adaptor.nil();


                    char_literal452 = (Token) match(input, 426, FOLLOW_426_in_sExprAtom11583);
                    pushFollow(FOLLOW_bScalarExpr_in_sExprAtom11586);
                    bScalarExpr453 = bScalarExpr();
                    state._fsp--;

                    adaptor.addChild(root_0, bScalarExpr453.getTree());

                    char_literal454 = (Token) match(input, 427, FOLLOW_427_in_sExprAtom11588);
                }
                break;
                case 4:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1331:5: ( LEN '(' scalarExpr ')' -> ^( SCALAR_LEN_TAG scalarExpr ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1331:5: ( LEN '(' scalarExpr ')' -> ^( SCALAR_LEN_TAG scalarExpr ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1331:6: LEN '(' scalarExpr ')'
                    {
                        LEN455 = (Token) match(input, LEN, FOLLOW_LEN_in_sExprAtom11596);
                        stream_LEN.add(LEN455);

                        char_literal456 = (Token) match(input, 426, FOLLOW_426_in_sExprAtom11598);
                        stream_426.add(char_literal456);

                        pushFollow(FOLLOW_scalarExpr_in_sExprAtom11600);
                        scalarExpr457 = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(scalarExpr457.getTree());
                        char_literal458 = (Token) match(input, 427, FOLLOW_427_in_sExprAtom11602);
                        stream_427.add(char_literal458);

                        // AST REWRITE
                        // elements: scalarExpr
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1332:7: -> ^( SCALAR_LEN_TAG scalarExpr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1333:9: ^( SCALAR_LEN_TAG scalarExpr )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_LEN_TAG, "SCALAR_LEN_TAG"), root_1);
                                adaptor.addChild(root_1, stream_scalarExpr.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 5:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1335:3: ( CONCAT '(' b1= scalarExpr ( ',' b2= scalarExpr )+ ')' -> ^( SCALAR_CONCAT_TAG $b1 ( $b2)+ ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1335:3: ( CONCAT '(' b1= scalarExpr ( ',' b2= scalarExpr )+ ')' -> ^( SCALAR_CONCAT_TAG $b1 ( $b2)+ ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1336:5: CONCAT '(' b1= scalarExpr ( ',' b2= scalarExpr )+ ')'
                    {
                        CONCAT459 = (Token) match(input, CONCAT, FOLLOW_CONCAT_in_sExprAtom11639);
                        stream_CONCAT.add(CONCAT459);

                        char_literal460 = (Token) match(input, 426, FOLLOW_426_in_sExprAtom11641);
                        stream_426.add(char_literal460);

                        pushFollow(FOLLOW_scalarExpr_in_sExprAtom11645);
                        b1 = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(b1.getTree());
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1336:30: ( ',' b2= scalarExpr )+
                        int cnt79 = 0;
                        loop79:
                        while (true) {
                            int alt79 = 2;
                            int LA79_0 = input.LA(1);
                            if ((LA79_0 == CARTESIAN_PER)) {
                                alt79 = 1;
                            }

                            switch (alt79) {
                                case 1:
                                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1336:31: ',' b2= scalarExpr
                                {
                                    char_literal461 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_sExprAtom11648);
                                    stream_CARTESIAN_PER.add(char_literal461);

                                    pushFollow(FOLLOW_scalarExpr_in_sExprAtom11652);
                                    b2 = scalarExpr();
                                    state._fsp--;

                                    stream_scalarExpr.add(b2.getTree());
                                }
                                break;

                                default:
                                    if (cnt79 >= 1) break loop79;
                                    EarlyExitException eee = new EarlyExitException(79, input);
                                    throw eee;
                            }
                            cnt79++;
                        }

                        char_literal462 = (Token) match(input, 427, FOLLOW_427_in_sExprAtom11656);
                        stream_427.add(char_literal462);

                        // AST REWRITE
                        // elements: b1, b2
                        // token labels:
                        // rule labels: b2, retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1337:7: -> ^( SCALAR_CONCAT_TAG $b1 ( $b2)+ )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1338:9: ^( SCALAR_CONCAT_TAG $b1 ( $b2)+ )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_CONCAT_TAG, "SCALAR_CONCAT_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                if (!(stream_b2.hasNext())) {
                                    throw new RewriteEarlyExitException();
                                }
                                while (stream_b2.hasNext()) {
                                    adaptor.addChild(root_1, stream_b2.nextTree());
                                }
                                stream_b2.reset();

                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 6:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1340:5: ( TRIM '(' scalarExpr ')' -> ^( SCALAR_TRIM_TAG scalarExpr ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1340:5: ( TRIM '(' scalarExpr ')' -> ^( SCALAR_TRIM_TAG scalarExpr ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1340:6: TRIM '(' scalarExpr ')'
                    {
                        TRIM463 = (Token) match(input, TRIM, FOLLOW_TRIM_in_sExprAtom11694);
                        stream_TRIM.add(TRIM463);

                        char_literal464 = (Token) match(input, 426, FOLLOW_426_in_sExprAtom11696);
                        stream_426.add(char_literal464);

                        pushFollow(FOLLOW_scalarExpr_in_sExprAtom11698);
                        scalarExpr465 = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(scalarExpr465.getTree());
                        char_literal466 = (Token) match(input, 427, FOLLOW_427_in_sExprAtom11700);
                        stream_427.add(char_literal466);

                        // AST REWRITE
                        // elements: scalarExpr
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1341:7: -> ^( SCALAR_TRIM_TAG scalarExpr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1342:9: ^( SCALAR_TRIM_TAG scalarExpr )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_TRIM_TAG, "SCALAR_TRIM_TAG"), root_1);
                                adaptor.addChild(root_1, stream_scalarExpr.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 7:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1343:5: ( UCASE '(' scalarExpr ')' -> ^( SCALAR_UCASE_TAG scalarExpr ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1343:5: ( UCASE '(' scalarExpr ')' -> ^( SCALAR_UCASE_TAG scalarExpr ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1343:6: UCASE '(' scalarExpr ')'
                    {
                        UCASE467 = (Token) match(input, UCASE, FOLLOW_UCASE_in_sExprAtom11730);
                        stream_UCASE.add(UCASE467);

                        char_literal468 = (Token) match(input, 426, FOLLOW_426_in_sExprAtom11732);
                        stream_426.add(char_literal468);

                        pushFollow(FOLLOW_scalarExpr_in_sExprAtom11734);
                        scalarExpr469 = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(scalarExpr469.getTree());
                        char_literal470 = (Token) match(input, 427, FOLLOW_427_in_sExprAtom11736);
                        stream_427.add(char_literal470);

                        // AST REWRITE
                        // elements: scalarExpr
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1344:7: -> ^( SCALAR_UCASE_TAG scalarExpr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1345:9: ^( SCALAR_UCASE_TAG scalarExpr )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_UCASE_TAG, "SCALAR_UCASE_TAG"), root_1);
                                adaptor.addChild(root_1, stream_scalarExpr.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 8:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1346:5: ( LCASE '(' scalarExpr ')' -> ^( SCALAR_LCASE_TAG scalarExpr ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1346:5: ( LCASE '(' scalarExpr ')' -> ^( SCALAR_LCASE_TAG scalarExpr ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1346:6: LCASE '(' scalarExpr ')'
                    {
                        LCASE471 = (Token) match(input, LCASE, FOLLOW_LCASE_in_sExprAtom11766);
                        stream_LCASE.add(LCASE471);

                        char_literal472 = (Token) match(input, 426, FOLLOW_426_in_sExprAtom11768);
                        stream_426.add(char_literal472);

                        pushFollow(FOLLOW_scalarExpr_in_sExprAtom11770);
                        scalarExpr473 = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(scalarExpr473.getTree());
                        char_literal474 = (Token) match(input, 427, FOLLOW_427_in_sExprAtom11772);
                        stream_427.add(char_literal474);

                        // AST REWRITE
                        // elements: scalarExpr
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1347:7: -> ^( SCALAR_LCASE_TAG scalarExpr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1348:9: ^( SCALAR_LCASE_TAG scalarExpr )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_LCASE_TAG, "SCALAR_LCASE_TAG"), root_1);
                                adaptor.addChild(root_1, stream_scalarExpr.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 9:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1349:5: ( SUBSTR '(' b1= scalarExpr ',' b2= scalarExpr ',' b3= scalarExpr ')' -> ^( SCALAR_SUBSTR_TAG $b1 $b2 $b3) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1349:5: ( SUBSTR '(' b1= scalarExpr ',' b2= scalarExpr ',' b3= scalarExpr ')' -> ^( SCALAR_SUBSTR_TAG $b1 $b2 $b3) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1349:6: SUBSTR '(' b1= scalarExpr ',' b2= scalarExpr ',' b3= scalarExpr ')'
                    {
                        SUBSTR475 = (Token) match(input, SUBSTR, FOLLOW_SUBSTR_in_sExprAtom11802);
                        stream_SUBSTR.add(SUBSTR475);

                        char_literal476 = (Token) match(input, 426, FOLLOW_426_in_sExprAtom11804);
                        stream_426.add(char_literal476);

                        pushFollow(FOLLOW_scalarExpr_in_sExprAtom11808);
                        b1 = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(b1.getTree());
                        char_literal477 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_sExprAtom11810);
                        stream_CARTESIAN_PER.add(char_literal477);

                        pushFollow(FOLLOW_scalarExpr_in_sExprAtom11814);
                        b2 = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(b2.getTree());
                        char_literal478 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_sExprAtom11816);
                        stream_CARTESIAN_PER.add(char_literal478);

                        pushFollow(FOLLOW_scalarExpr_in_sExprAtom11820);
                        b3 = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(b3.getTree());
                        char_literal479 = (Token) match(input, 427, FOLLOW_427_in_sExprAtom11822);
                        stream_427.add(char_literal479);

                        // AST REWRITE
                        // elements: b3, b1, b2
                        // token labels:
                        // rule labels: b2, b3, retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                        RewriteRuleSubtreeStream stream_b3 = new RewriteRuleSubtreeStream(adaptor, "rule b3", b3 != null ? b3.getTree() : null);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1350:7: -> ^( SCALAR_SUBSTR_TAG $b1 $b2 $b3)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1351:9: ^( SCALAR_SUBSTR_TAG $b1 $b2 $b3)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_SUBSTR_TAG, "SCALAR_SUBSTR_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                adaptor.addChild(root_1, stream_b2.nextTree());
                                adaptor.addChild(root_1, stream_b3.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 10:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1352:5: ( ROUND '(' scalarExpr ',' INTEGER_CONSTANT ')' -> ^( SCALAR_ROUND_TAG scalarExpr INTEGER_CONSTANT ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1352:5: ( ROUND '(' scalarExpr ',' INTEGER_CONSTANT ')' -> ^( SCALAR_ROUND_TAG scalarExpr INTEGER_CONSTANT ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1352:6: ROUND '(' scalarExpr ',' INTEGER_CONSTANT ')'
                    {
                        ROUND480 = (Token) match(input, ROUND, FOLLOW_ROUND_in_sExprAtom11859);
                        stream_ROUND.add(ROUND480);

                        char_literal481 = (Token) match(input, 426, FOLLOW_426_in_sExprAtom11861);
                        stream_426.add(char_literal481);

                        pushFollow(FOLLOW_scalarExpr_in_sExprAtom11863);
                        scalarExpr482 = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(scalarExpr482.getTree());
                        char_literal483 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_sExprAtom11865);
                        stream_CARTESIAN_PER.add(char_literal483);

                        INTEGER_CONSTANT484 = (Token) match(input, INTEGER_CONSTANT, FOLLOW_INTEGER_CONSTANT_in_sExprAtom11867);
                        stream_INTEGER_CONSTANT.add(INTEGER_CONSTANT484);

                        char_literal485 = (Token) match(input, 427, FOLLOW_427_in_sExprAtom11869);
                        stream_427.add(char_literal485);

                        // AST REWRITE
                        // elements: INTEGER_CONSTANT, scalarExpr
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1353:7: -> ^( SCALAR_ROUND_TAG scalarExpr INTEGER_CONSTANT )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1354:9: ^( SCALAR_ROUND_TAG scalarExpr INTEGER_CONSTANT )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_ROUND_TAG, "SCALAR_ROUND_TAG"), root_1);
                                adaptor.addChild(root_1, stream_scalarExpr.nextTree());
                                adaptor.addChild(root_1, stream_INTEGER_CONSTANT.nextNode());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 11:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1355:5: ( TRUNC '(' scalarExpr ')' -> ^( SCALAR_TRUNC_TAG scalarExpr ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1355:5: ( TRUNC '(' scalarExpr ')' -> ^( SCALAR_TRUNC_TAG scalarExpr ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1355:6: TRUNC '(' scalarExpr ')'
                    {
                        TRUNC486 = (Token) match(input, TRUNC, FOLLOW_TRUNC_in_sExprAtom11901);
                        stream_TRUNC.add(TRUNC486);

                        char_literal487 = (Token) match(input, 426, FOLLOW_426_in_sExprAtom11903);
                        stream_426.add(char_literal487);

                        pushFollow(FOLLOW_scalarExpr_in_sExprAtom11905);
                        scalarExpr488 = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(scalarExpr488.getTree());
                        char_literal489 = (Token) match(input, 427, FOLLOW_427_in_sExprAtom11907);
                        stream_427.add(char_literal489);

                        // AST REWRITE
                        // elements: scalarExpr
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1356:7: -> ^( SCALAR_TRUNC_TAG scalarExpr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1357:9: ^( SCALAR_TRUNC_TAG scalarExpr )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_TRUNC_TAG, "SCALAR_TRUNC_TAG"), root_1);
                                adaptor.addChild(root_1, stream_scalarExpr.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 12:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1358:5: ( LN '(' scalarExpr ')' -> ^( SCALAR_LEN_TAG scalarExpr ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1358:5: ( LN '(' scalarExpr ')' -> ^( SCALAR_LEN_TAG scalarExpr ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1358:6: LN '(' scalarExpr ')'
                    {
                        LN490 = (Token) match(input, LN, FOLLOW_LN_in_sExprAtom11937);
                        stream_LN.add(LN490);

                        char_literal491 = (Token) match(input, 426, FOLLOW_426_in_sExprAtom11939);
                        stream_426.add(char_literal491);

                        pushFollow(FOLLOW_scalarExpr_in_sExprAtom11941);
                        scalarExpr492 = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(scalarExpr492.getTree());
                        char_literal493 = (Token) match(input, 427, FOLLOW_427_in_sExprAtom11943);
                        stream_427.add(char_literal493);

                        // AST REWRITE
                        // elements: scalarExpr
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1359:7: -> ^( SCALAR_LEN_TAG scalarExpr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1360:9: ^( SCALAR_LEN_TAG scalarExpr )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_LEN_TAG, "SCALAR_LEN_TAG"), root_1);
                                adaptor.addChild(root_1, stream_scalarExpr.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 13:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1361:5: ( EXP '(' scalarExpr ')' -> ^( SCALAR_EXP_TAG scalarExpr ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1361:5: ( EXP '(' scalarExpr ')' -> ^( SCALAR_EXP_TAG scalarExpr ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1361:6: EXP '(' scalarExpr ')'
                    {
                        EXP494 = (Token) match(input, EXP, FOLLOW_EXP_in_sExprAtom11973);
                        stream_EXP.add(EXP494);

                        char_literal495 = (Token) match(input, 426, FOLLOW_426_in_sExprAtom11975);
                        stream_426.add(char_literal495);

                        pushFollow(FOLLOW_scalarExpr_in_sExprAtom11977);
                        scalarExpr496 = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(scalarExpr496.getTree());
                        char_literal497 = (Token) match(input, 427, FOLLOW_427_in_sExprAtom11979);
                        stream_427.add(char_literal497);

                        // AST REWRITE
                        // elements: scalarExpr
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1362:7: -> ^( SCALAR_EXP_TAG scalarExpr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1363:9: ^( SCALAR_EXP_TAG scalarExpr )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_EXP_TAG, "SCALAR_EXP_TAG"), root_1);
                                adaptor.addChild(root_1, stream_scalarExpr.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 14:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1364:5: ( MOD '(' scalarExpr ',' INTEGER_CONSTANT ')' -> ^( SCALAR_MOD_TAG scalarExpr INTEGER_CONSTANT ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1364:5: ( MOD '(' scalarExpr ',' INTEGER_CONSTANT ')' -> ^( SCALAR_MOD_TAG scalarExpr INTEGER_CONSTANT ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1364:6: MOD '(' scalarExpr ',' INTEGER_CONSTANT ')'
                    {
                        MOD498 = (Token) match(input, MOD, FOLLOW_MOD_in_sExprAtom12009);
                        stream_MOD.add(MOD498);

                        char_literal499 = (Token) match(input, 426, FOLLOW_426_in_sExprAtom12011);
                        stream_426.add(char_literal499);

                        pushFollow(FOLLOW_scalarExpr_in_sExprAtom12013);
                        scalarExpr500 = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(scalarExpr500.getTree());
                        char_literal501 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_sExprAtom12015);
                        stream_CARTESIAN_PER.add(char_literal501);

                        INTEGER_CONSTANT502 = (Token) match(input, INTEGER_CONSTANT, FOLLOW_INTEGER_CONSTANT_in_sExprAtom12017);
                        stream_INTEGER_CONSTANT.add(INTEGER_CONSTANT502);

                        char_literal503 = (Token) match(input, 427, FOLLOW_427_in_sExprAtom12019);
                        stream_427.add(char_literal503);

                        // AST REWRITE
                        // elements: scalarExpr, INTEGER_CONSTANT
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1365:7: -> ^( SCALAR_MOD_TAG scalarExpr INTEGER_CONSTANT )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1366:9: ^( SCALAR_MOD_TAG scalarExpr INTEGER_CONSTANT )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_MOD_TAG, "SCALAR_MOD_TAG"), root_1);
                                adaptor.addChild(root_1, stream_scalarExpr.nextTree());
                                adaptor.addChild(root_1, stream_INTEGER_CONSTANT.nextNode());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 15:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1367:5: ( ABS '(' scalarExpr ')' -> ^( SCALAR_ABS_TAG scalarExpr ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1367:5: ( ABS '(' scalarExpr ')' -> ^( SCALAR_ABS_TAG scalarExpr ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1367:6: ABS '(' scalarExpr ')'
                    {
                        ABS504 = (Token) match(input, ABS, FOLLOW_ABS_in_sExprAtom12051);
                        stream_ABS.add(ABS504);

                        char_literal505 = (Token) match(input, 426, FOLLOW_426_in_sExprAtom12053);
                        stream_426.add(char_literal505);

                        pushFollow(FOLLOW_scalarExpr_in_sExprAtom12055);
                        scalarExpr506 = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(scalarExpr506.getTree());
                        char_literal507 = (Token) match(input, 427, FOLLOW_427_in_sExprAtom12057);
                        stream_427.add(char_literal507);

                        // AST REWRITE
                        // elements: scalarExpr
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1368:7: -> ^( SCALAR_ABS_TAG scalarExpr )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1369:9: ^( SCALAR_ABS_TAG scalarExpr )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_ABS_TAG, "SCALAR_ABS_TAG"), root_1);
                                adaptor.addChild(root_1, stream_scalarExpr.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 16:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1370:5: ( POWER '(' scalarExpr ',' powerExponent ')' -> ^( SCALAR_POWER_TAG scalarExpr powerExponent ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1370:5: ( POWER '(' scalarExpr ',' powerExponent ')' -> ^( SCALAR_POWER_TAG scalarExpr powerExponent ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1370:6: POWER '(' scalarExpr ',' powerExponent ')'
                    {
                        POWER508 = (Token) match(input, POWER, FOLLOW_POWER_in_sExprAtom12087);
                        stream_POWER.add(POWER508);

                        char_literal509 = (Token) match(input, 426, FOLLOW_426_in_sExprAtom12089);
                        stream_426.add(char_literal509);

                        pushFollow(FOLLOW_scalarExpr_in_sExprAtom12091);
                        scalarExpr510 = scalarExpr();
                        state._fsp--;

                        stream_scalarExpr.add(scalarExpr510.getTree());
                        char_literal511 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_sExprAtom12093);
                        stream_CARTESIAN_PER.add(char_literal511);

                        pushFollow(FOLLOW_powerExponent_in_sExprAtom12095);
                        powerExponent512 = powerExponent();
                        state._fsp--;

                        stream_powerExponent.add(powerExponent512.getTree());
                        char_literal513 = (Token) match(input, 427, FOLLOW_427_in_sExprAtom12097);
                        stream_427.add(char_literal513);

                        // AST REWRITE
                        // elements: scalarExpr, powerExponent
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1371:7: -> ^( SCALAR_POWER_TAG scalarExpr powerExponent )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1372:9: ^( SCALAR_POWER_TAG scalarExpr powerExponent )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_POWER_TAG, "SCALAR_POWER_TAG"), root_1);
                                adaptor.addChild(root_1, stream_scalarExpr.nextTree());
                                adaptor.addChild(root_1, stream_powerExponent.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 17:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1373:5: (sys= 'SYSTIMESTAMP' )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1373:5: (sys= 'SYSTIMESTAMP' )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1373:6: sys= 'SYSTIMESTAMP'
                    {
                        sys = (Token) match(input, 430, FOLLOW_430_in_sExprAtom12131);
                        stream_430.add(sys);

                    }

                    // AST REWRITE
                    // elements: sys
                    // token labels: sys
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_sys = new RewriteRuleTokenStream(adaptor, "token sys", sys);
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1374:5: -> ^( SYSTIMESTAMP_TAG $sys)
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1375:7: ^( SYSTIMESTAMP_TAG $sys)
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SYSTIMESTAMP_TAG, "SYSTIMESTAMP_TAG"), root_1);
                            adaptor.addChild(root_1, stream_sys.nextNode());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "componentID"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1378:1: componentID : v1= IDENTIFIER -> ^( COMPONENTID_TAG $v1) ;
    public final ValidationMlParser.componentID_return componentID() throws Exception {
        ValidationMlParser.componentID_return retval = new ValidationMlParser.componentID_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token v1 = null;

        Object v1_tree = null;
        RewriteRuleTokenStream stream_IDENTIFIER = new RewriteRuleTokenStream(adaptor, "token IDENTIFIER");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1379:3: (v1= IDENTIFIER -> ^( COMPONENTID_TAG $v1) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1380:3: v1= IDENTIFIER
            {
                v1 = (Token) match(input, IDENTIFIER, FOLLOW_IDENTIFIER_in_componentID12168);
                stream_IDENTIFIER.add(v1);

                // AST REWRITE
                // elements: v1
                // token labels: v1
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleTokenStream stream_v1 = new RewriteRuleTokenStream(adaptor, "token v1", v1);
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                root_0 = (Object) adaptor.nil();
                // 1381:5: -> ^( COMPONENTID_TAG $v1)
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1382:7: ^( COMPONENTID_TAG $v1)
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(COMPONENTID_TAG, "COMPONENTID_TAG"), root_1);
                        adaptor.addChild(root_1, stream_v1.nextNode());
                        adaptor.addChild(root_0, root_1);
                    }

                }


                retval.tree = root_0;

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "compOpScalarClause"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1385:1: compOpScalarClause : ( ( EQ -> ^( SCALAR_EQ_CLAUSE_TAG ) ) | ( LT -> ^( SCALAR_LT_CLAUSE_TAG ) ) | ( GT -> ^( SCALAR_GT_CLAUSE_TAG ) ) | ( LE -> ^( SCALAR_LE_CLAUSE_TAG ) ) | ( GE -> ^( SCALAR_GE_CLAUSE_TAG ) ) | ( NE -> ^( SCALAR_NE_CLAUSE_TAG ) ) );
    public final ValidationMlParser.compOpScalarClause_return compOpScalarClause() throws Exception {
        ValidationMlParser.compOpScalarClause_return retval = new ValidationMlParser.compOpScalarClause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EQ514 = null;
        Token LT515 = null;
        Token GT516 = null;
        Token LE517 = null;
        Token GE518 = null;
        Token NE519 = null;

        Object EQ514_tree = null;
        Object LT515_tree = null;
        Object GT516_tree = null;
        Object LE517_tree = null;
        Object GE518_tree = null;
        Object NE519_tree = null;
        RewriteRuleTokenStream stream_NE = new RewriteRuleTokenStream(adaptor, "token NE");
        RewriteRuleTokenStream stream_LT = new RewriteRuleTokenStream(adaptor, "token LT");
        RewriteRuleTokenStream stream_LE = new RewriteRuleTokenStream(adaptor, "token LE");
        RewriteRuleTokenStream stream_EQ = new RewriteRuleTokenStream(adaptor, "token EQ");
        RewriteRuleTokenStream stream_GT = new RewriteRuleTokenStream(adaptor, "token GT");
        RewriteRuleTokenStream stream_GE = new RewriteRuleTokenStream(adaptor, "token GE");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1386:3: ( ( EQ -> ^( SCALAR_EQ_CLAUSE_TAG ) ) | ( LT -> ^( SCALAR_LT_CLAUSE_TAG ) ) | ( GT -> ^( SCALAR_GT_CLAUSE_TAG ) ) | ( LE -> ^( SCALAR_LE_CLAUSE_TAG ) ) | ( GE -> ^( SCALAR_GE_CLAUSE_TAG ) ) | ( NE -> ^( SCALAR_NE_CLAUSE_TAG ) ) )
            int alt81 = 6;
            switch (input.LA(1)) {
                case EQ: {
                    alt81 = 1;
                }
                break;
                case LT: {
                    alt81 = 2;
                }
                break;
                case GT: {
                    alt81 = 3;
                }
                break;
                case LE: {
                    alt81 = 4;
                }
                break;
                case GE: {
                    alt81 = 5;
                }
                break;
                case NE: {
                    alt81 = 6;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 81, 0, input);
                    throw nvae;
            }
            switch (alt81) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1387:3: ( EQ -> ^( SCALAR_EQ_CLAUSE_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1387:3: ( EQ -> ^( SCALAR_EQ_CLAUSE_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1387:4: EQ
                    {
                        EQ514 = (Token) match(input, EQ, FOLLOW_EQ_in_compOpScalarClause12203);
                        stream_EQ.add(EQ514);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1388:7: -> ^( SCALAR_EQ_CLAUSE_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1389:9: ^( SCALAR_EQ_CLAUSE_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_EQ_CLAUSE_TAG, "SCALAR_EQ_CLAUSE_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1390:5: ( LT -> ^( SCALAR_LT_CLAUSE_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1390:5: ( LT -> ^( SCALAR_LT_CLAUSE_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1390:6: LT
                    {
                        LT515 = (Token) match(input, LT, FOLLOW_LT_in_compOpScalarClause12231);
                        stream_LT.add(LT515);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1391:7: -> ^( SCALAR_LT_CLAUSE_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1392:9: ^( SCALAR_LT_CLAUSE_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_LT_CLAUSE_TAG, "SCALAR_LT_CLAUSE_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 3:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1393:5: ( GT -> ^( SCALAR_GT_CLAUSE_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1393:5: ( GT -> ^( SCALAR_GT_CLAUSE_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1393:6: GT
                    {
                        GT516 = (Token) match(input, GT, FOLLOW_GT_in_compOpScalarClause12259);
                        stream_GT.add(GT516);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1394:7: -> ^( SCALAR_GT_CLAUSE_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1395:9: ^( SCALAR_GT_CLAUSE_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_GT_CLAUSE_TAG, "SCALAR_GT_CLAUSE_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 4:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1396:5: ( LE -> ^( SCALAR_LE_CLAUSE_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1396:5: ( LE -> ^( SCALAR_LE_CLAUSE_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1396:6: LE
                    {
                        LE517 = (Token) match(input, LE, FOLLOW_LE_in_compOpScalarClause12287);
                        stream_LE.add(LE517);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1397:7: -> ^( SCALAR_LE_CLAUSE_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1398:9: ^( SCALAR_LE_CLAUSE_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_LE_CLAUSE_TAG, "SCALAR_LE_CLAUSE_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 5:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1399:5: ( GE -> ^( SCALAR_GE_CLAUSE_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1399:5: ( GE -> ^( SCALAR_GE_CLAUSE_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1399:6: GE
                    {
                        GE518 = (Token) match(input, GE, FOLLOW_GE_in_compOpScalarClause12315);
                        stream_GE.add(GE518);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1400:7: -> ^( SCALAR_GE_CLAUSE_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1401:9: ^( SCALAR_GE_CLAUSE_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_GE_CLAUSE_TAG, "SCALAR_GE_CLAUSE_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 6:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1402:5: ( NE -> ^( SCALAR_NE_CLAUSE_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1402:5: ( NE -> ^( SCALAR_NE_CLAUSE_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1402:6: NE
                    {
                        NE519 = (Token) match(input, NE, FOLLOW_NE_in_compOpScalarClause12343);
                        stream_NE.add(NE519);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1403:7: -> ^( SCALAR_NE_CLAUSE_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1404:9: ^( SCALAR_NE_CLAUSE_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_NE_CLAUSE_TAG, "SCALAR_NE_CLAUSE_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "logBase"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1407:1: logBase : INTEGER_CONSTANT -> ^( INT_CONSTANT_TAG INTEGER_CONSTANT ) ;
    public final ValidationMlParser.logBase_return logBase() throws Exception {
        ValidationMlParser.logBase_return retval = new ValidationMlParser.logBase_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token INTEGER_CONSTANT520 = null;

        Object INTEGER_CONSTANT520_tree = null;
        RewriteRuleTokenStream stream_INTEGER_CONSTANT = new RewriteRuleTokenStream(adaptor, "token INTEGER_CONSTANT");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1408:3: ( INTEGER_CONSTANT -> ^( INT_CONSTANT_TAG INTEGER_CONSTANT ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1409:3: INTEGER_CONSTANT
            {
                INTEGER_CONSTANT520 = (Token) match(input, INTEGER_CONSTANT, FOLLOW_INTEGER_CONSTANT_in_logBase12379);
                stream_INTEGER_CONSTANT.add(INTEGER_CONSTANT520);

                // AST REWRITE
                // elements: INTEGER_CONSTANT
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                root_0 = (Object) adaptor.nil();
                // 1410:5: -> ^( INT_CONSTANT_TAG INTEGER_CONSTANT )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1411:7: ^( INT_CONSTANT_TAG INTEGER_CONSTANT )
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(INT_CONSTANT_TAG, "INT_CONSTANT_TAG"), root_1);
                        adaptor.addChild(root_1, stream_INTEGER_CONSTANT.nextNode());
                        adaptor.addChild(root_0, root_1);
                    }

                }


                retval.tree = root_0;

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "powerExponent"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1414:1: powerExponent : ( ( exponent -> exponent ) | ( ( PLUS exponent ) -> exponent ) | ( ( MINUS exponent ) -> ^( SCALAR_MINUS_UNARY_TAG exponent ) ) ) ;
    public final ValidationMlParser.powerExponent_return powerExponent() throws Exception {
        ValidationMlParser.powerExponent_return retval = new ValidationMlParser.powerExponent_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PLUS522 = null;
        Token MINUS524 = null;
        ParserRuleReturnScope exponent521 = null;
        ParserRuleReturnScope exponent523 = null;
        ParserRuleReturnScope exponent525 = null;

        Object PLUS522_tree = null;
        Object MINUS524_tree = null;
        RewriteRuleTokenStream stream_PLUS = new RewriteRuleTokenStream(adaptor, "token PLUS");
        RewriteRuleTokenStream stream_MINUS = new RewriteRuleTokenStream(adaptor, "token MINUS");
        RewriteRuleSubtreeStream stream_exponent = new RewriteRuleSubtreeStream(adaptor, "rule exponent");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1415:3: ( ( ( exponent -> exponent ) | ( ( PLUS exponent ) -> exponent ) | ( ( MINUS exponent ) -> ^( SCALAR_MINUS_UNARY_TAG exponent ) ) ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1416:3: ( ( exponent -> exponent ) | ( ( PLUS exponent ) -> exponent ) | ( ( MINUS exponent ) -> ^( SCALAR_MINUS_UNARY_TAG exponent ) ) )
            {
                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1416:3: ( ( exponent -> exponent ) | ( ( PLUS exponent ) -> exponent ) | ( ( MINUS exponent ) -> ^( SCALAR_MINUS_UNARY_TAG exponent ) ) )
                int alt82 = 3;
                switch (input.LA(1)) {
                    case FLOAT_CONSTANT:
                    case INTEGER_CONSTANT: {
                        alt82 = 1;
                    }
                    break;
                    case PLUS: {
                        alt82 = 2;
                    }
                    break;
                    case MINUS: {
                        alt82 = 3;
                    }
                    break;
                    default:
                        NoViableAltException nvae =
                                new NoViableAltException("", 82, 0, input);
                        throw nvae;
                }
                switch (alt82) {
                    case 1:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1417:5: ( exponent -> exponent )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1417:5: ( exponent -> exponent )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1417:6: exponent
                        {
                            pushFollow(FOLLOW_exponent_in_powerExponent12419);
                            exponent521 = exponent();
                            state._fsp--;

                            stream_exponent.add(exponent521.getTree());
                            // AST REWRITE
                            // elements: exponent
                            // token labels:
                            // rule labels: retval
                            // token list labels:
                            // rule list labels:
                            // wildcard labels:
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                            root_0 = (Object) adaptor.nil();
                            // 1418:9: -> exponent
                            {
                                adaptor.addChild(root_0, stream_exponent.nextTree());
                            }


                            retval.tree = root_0;

                        }

                    }
                    break;
                    case 2:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1420:5: ( ( PLUS exponent ) -> exponent )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1420:5: ( ( PLUS exponent ) -> exponent )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1421:7: ( PLUS exponent )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1421:7: ( PLUS exponent )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1421:8: PLUS exponent
                            {
                                PLUS522 = (Token) match(input, PLUS, FOLLOW_PLUS_in_powerExponent12453);
                                stream_PLUS.add(PLUS522);

                                pushFollow(FOLLOW_exponent_in_powerExponent12455);
                                exponent523 = exponent();
                                state._fsp--;

                                stream_exponent.add(exponent523.getTree());
                            }

                            // AST REWRITE
                            // elements: exponent
                            // token labels:
                            // rule labels: retval
                            // token list labels:
                            // rule list labels:
                            // wildcard labels:
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                            root_0 = (Object) adaptor.nil();
                            // 1422:9: -> exponent
                            {
                                adaptor.addChild(root_0, stream_exponent.nextTree());
                            }


                            retval.tree = root_0;

                        }

                    }
                    break;
                    case 3:
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1425:5: ( ( MINUS exponent ) -> ^( SCALAR_MINUS_UNARY_TAG exponent ) )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1425:5: ( ( MINUS exponent ) -> ^( SCALAR_MINUS_UNARY_TAG exponent ) )
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1426:7: ( MINUS exponent )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1426:7: ( MINUS exponent )
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1426:8: MINUS exponent
                            {
                                MINUS524 = (Token) match(input, MINUS, FOLLOW_MINUS_in_powerExponent12495);
                                stream_MINUS.add(MINUS524);

                                pushFollow(FOLLOW_exponent_in_powerExponent12497);
                                exponent525 = exponent();
                                state._fsp--;

                                stream_exponent.add(exponent525.getTree());
                            }

                            // AST REWRITE
                            // elements: exponent
                            // token labels:
                            // rule labels: retval
                            // token list labels:
                            // rule list labels:
                            // wildcard labels:
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                            root_0 = (Object) adaptor.nil();
                            // 1427:9: -> ^( SCALAR_MINUS_UNARY_TAG exponent )
                            {
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1428:11: ^( SCALAR_MINUS_UNARY_TAG exponent )
                                {
                                    Object root_1 = (Object) adaptor.nil();
                                    root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_MINUS_UNARY_TAG, "SCALAR_MINUS_UNARY_TAG"), root_1);
                                    adaptor.addChild(root_1, stream_exponent.nextTree());
                                    adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;

                        }

                    }
                    break;

                }

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "exponent"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1433:1: exponent : ( INTEGER_CONSTANT -> ^( INT_CONSTANT_TAG INTEGER_CONSTANT ) | FLOAT_CONSTANT -> ^( FLOAT_CONSTANT_TAG FLOAT_CONSTANT ) );
    public final ValidationMlParser.exponent_return exponent() throws Exception {
        ValidationMlParser.exponent_return retval = new ValidationMlParser.exponent_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token INTEGER_CONSTANT526 = null;
        Token FLOAT_CONSTANT527 = null;

        Object INTEGER_CONSTANT526_tree = null;
        Object FLOAT_CONSTANT527_tree = null;
        RewriteRuleTokenStream stream_FLOAT_CONSTANT = new RewriteRuleTokenStream(adaptor, "token FLOAT_CONSTANT");
        RewriteRuleTokenStream stream_INTEGER_CONSTANT = new RewriteRuleTokenStream(adaptor, "token INTEGER_CONSTANT");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1434:3: ( INTEGER_CONSTANT -> ^( INT_CONSTANT_TAG INTEGER_CONSTANT ) | FLOAT_CONSTANT -> ^( FLOAT_CONSTANT_TAG FLOAT_CONSTANT ) )
            int alt83 = 2;
            int LA83_0 = input.LA(1);
            if ((LA83_0 == INTEGER_CONSTANT)) {
                alt83 = 1;
            } else if ((LA83_0 == FLOAT_CONSTANT)) {
                alt83 = 2;
            } else {
                NoViableAltException nvae =
                        new NoViableAltException("", 83, 0, input);
                throw nvae;
            }

            switch (alt83) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1435:3: INTEGER_CONSTANT
                {
                    INTEGER_CONSTANT526 = (Token) match(input, INTEGER_CONSTANT, FOLLOW_INTEGER_CONSTANT_in_exponent12549);
                    stream_INTEGER_CONSTANT.add(INTEGER_CONSTANT526);

                    // AST REWRITE
                    // elements: INTEGER_CONSTANT
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1436:5: -> ^( INT_CONSTANT_TAG INTEGER_CONSTANT )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1437:7: ^( INT_CONSTANT_TAG INTEGER_CONSTANT )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(INT_CONSTANT_TAG, "INT_CONSTANT_TAG"), root_1);
                            adaptor.addChild(root_1, stream_INTEGER_CONSTANT.nextNode());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1438:5: FLOAT_CONSTANT
                {
                    FLOAT_CONSTANT527 = (Token) match(input, FLOAT_CONSTANT, FOLLOW_FLOAT_CONSTANT_in_exponent12573);
                    stream_FLOAT_CONSTANT.add(FLOAT_CONSTANT527);

                    // AST REWRITE
                    // elements: FLOAT_CONSTANT
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1439:5: -> ^( FLOAT_CONSTANT_TAG FLOAT_CONSTANT )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1440:7: ^( FLOAT_CONSTANT_TAG FLOAT_CONSTANT )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(FLOAT_CONSTANT_TAG, "FLOAT_CONSTANT_TAG"), root_1);
                            adaptor.addChild(root_1, stream_FLOAT_CONSTANT.nextNode());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "setExpr"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1443:1: setExpr : ( '(' ! constant ( ',' ! constant )* ')' !| UNION '(' setExpr ( ',' setExpr )+ ')' -> ^( SET_UNION_TAG setExpr ( setExpr )+ ) | ( DIFF '(' b1= setExpr ',' b2= setExpr ')' -> ^( SET_DIFF_TAG $b1 $b2) ) | ( INTERSECT '(' b1= setExpr ',' b2= setExpr ')' -> ^( SET_INTERSECT_TAG $b1 $b2) ) );
    public final ValidationMlParser.setExpr_return setExpr() throws Exception {
        ValidationMlParser.setExpr_return retval = new ValidationMlParser.setExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token char_literal528 = null;
        Token char_literal530 = null;
        Token char_literal532 = null;
        Token UNION533 = null;
        Token char_literal534 = null;
        Token char_literal536 = null;
        Token char_literal538 = null;
        Token DIFF539 = null;
        Token char_literal540 = null;
        Token char_literal541 = null;
        Token char_literal542 = null;
        Token INTERSECT543 = null;
        Token char_literal544 = null;
        Token char_literal545 = null;
        Token char_literal546 = null;
        ParserRuleReturnScope b1 = null;
        ParserRuleReturnScope b2 = null;
        ParserRuleReturnScope constant529 = null;
        ParserRuleReturnScope constant531 = null;
        ParserRuleReturnScope setExpr535 = null;
        ParserRuleReturnScope setExpr537 = null;

        Object char_literal528_tree = null;
        Object char_literal530_tree = null;
        Object char_literal532_tree = null;
        Object UNION533_tree = null;
        Object char_literal534_tree = null;
        Object char_literal536_tree = null;
        Object char_literal538_tree = null;
        Object DIFF539_tree = null;
        Object char_literal540_tree = null;
        Object char_literal541_tree = null;
        Object char_literal542_tree = null;
        Object INTERSECT543_tree = null;
        Object char_literal544_tree = null;
        Object char_literal545_tree = null;
        Object char_literal546_tree = null;
        RewriteRuleTokenStream stream_DIFF = new RewriteRuleTokenStream(adaptor, "token DIFF");
        RewriteRuleTokenStream stream_426 = new RewriteRuleTokenStream(adaptor, "token 426");
        RewriteRuleTokenStream stream_427 = new RewriteRuleTokenStream(adaptor, "token 427");
        RewriteRuleTokenStream stream_CARTESIAN_PER = new RewriteRuleTokenStream(adaptor, "token CARTESIAN_PER");
        RewriteRuleTokenStream stream_INTERSECT = new RewriteRuleTokenStream(adaptor, "token INTERSECT");
        RewriteRuleTokenStream stream_UNION = new RewriteRuleTokenStream(adaptor, "token UNION");
        RewriteRuleSubtreeStream stream_setExpr = new RewriteRuleSubtreeStream(adaptor, "rule setExpr");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1444:3: ( '(' ! constant ( ',' ! constant )* ')' !| UNION '(' setExpr ( ',' setExpr )+ ')' -> ^( SET_UNION_TAG setExpr ( setExpr )+ ) | ( DIFF '(' b1= setExpr ',' b2= setExpr ')' -> ^( SET_DIFF_TAG $b1 $b2) ) | ( INTERSECT '(' b1= setExpr ',' b2= setExpr ')' -> ^( SET_INTERSECT_TAG $b1 $b2) ) )
            int alt86 = 4;
            switch (input.LA(1)) {
                case 426: {
                    alt86 = 1;
                }
                break;
                case UNION: {
                    alt86 = 2;
                }
                break;
                case DIFF: {
                    alt86 = 3;
                }
                break;
                case INTERSECT: {
                    alt86 = 4;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 86, 0, input);
                    throw nvae;
            }
            switch (alt86) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1445:3: '(' ! constant ( ',' ! constant )* ')' !
                {
                    root_0 = (Object) adaptor.nil();


                    char_literal528 = (Token) match(input, 426, FOLLOW_426_in_setExpr12606);
                    pushFollow(FOLLOW_constant_in_setExpr12609);
                    constant529 = constant();
                    state._fsp--;

                    adaptor.addChild(root_0, constant529.getTree());

                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1445:17: ( ',' ! constant )*
                    loop84:
                    while (true) {
                        int alt84 = 2;
                        int LA84_0 = input.LA(1);
                        if ((LA84_0 == CARTESIAN_PER)) {
                            alt84 = 1;
                        }

                        switch (alt84) {
                            case 1:
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1445:18: ',' ! constant
                            {
                                char_literal530 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_setExpr12612);
                                pushFollow(FOLLOW_constant_in_setExpr12615);
                                constant531 = constant();
                                state._fsp--;

                                adaptor.addChild(root_0, constant531.getTree());

                            }
                            break;

                            default:
                                break loop84;
                        }
                    }

                    char_literal532 = (Token) match(input, 427, FOLLOW_427_in_setExpr12619);
                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1446:5: UNION '(' setExpr ( ',' setExpr )+ ')'
                {
                    UNION533 = (Token) match(input, UNION, FOLLOW_UNION_in_setExpr12626);
                    stream_UNION.add(UNION533);

                    char_literal534 = (Token) match(input, 426, FOLLOW_426_in_setExpr12628);
                    stream_426.add(char_literal534);

                    pushFollow(FOLLOW_setExpr_in_setExpr12630);
                    setExpr535 = setExpr();
                    state._fsp--;

                    stream_setExpr.add(setExpr535.getTree());
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1446:23: ( ',' setExpr )+
                    int cnt85 = 0;
                    loop85:
                    while (true) {
                        int alt85 = 2;
                        int LA85_0 = input.LA(1);
                        if ((LA85_0 == CARTESIAN_PER)) {
                            alt85 = 1;
                        }

                        switch (alt85) {
                            case 1:
                                // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1446:24: ',' setExpr
                            {
                                char_literal536 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_setExpr12633);
                                stream_CARTESIAN_PER.add(char_literal536);

                                pushFollow(FOLLOW_setExpr_in_setExpr12635);
                                setExpr537 = setExpr();
                                state._fsp--;

                                stream_setExpr.add(setExpr537.getTree());
                            }
                            break;

                            default:
                                if (cnt85 >= 1) break loop85;
                                EarlyExitException eee = new EarlyExitException(85, input);
                                throw eee;
                        }
                        cnt85++;
                    }

                    char_literal538 = (Token) match(input, 427, FOLLOW_427_in_setExpr12639);
                    stream_427.add(char_literal538);

                    // AST REWRITE
                    // elements: setExpr, setExpr
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1447:5: -> ^( SET_UNION_TAG setExpr ( setExpr )+ )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1448:7: ^( SET_UNION_TAG setExpr ( setExpr )+ )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SET_UNION_TAG, "SET_UNION_TAG"), root_1);
                            adaptor.addChild(root_1, stream_setExpr.nextTree());
                            if (!(stream_setExpr.hasNext())) {
                                throw new RewriteEarlyExitException();
                            }
                            while (stream_setExpr.hasNext()) {
                                adaptor.addChild(root_1, stream_setExpr.nextTree());
                            }
                            stream_setExpr.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;
                case 3:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1449:5: ( DIFF '(' b1= setExpr ',' b2= setExpr ')' -> ^( SET_DIFF_TAG $b1 $b2) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1449:5: ( DIFF '(' b1= setExpr ',' b2= setExpr ')' -> ^( SET_DIFF_TAG $b1 $b2) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1449:6: DIFF '(' b1= setExpr ',' b2= setExpr ')'
                    {
                        DIFF539 = (Token) match(input, DIFF, FOLLOW_DIFF_in_setExpr12667);
                        stream_DIFF.add(DIFF539);

                        char_literal540 = (Token) match(input, 426, FOLLOW_426_in_setExpr12669);
                        stream_426.add(char_literal540);

                        pushFollow(FOLLOW_setExpr_in_setExpr12673);
                        b1 = setExpr();
                        state._fsp--;

                        stream_setExpr.add(b1.getTree());
                        char_literal541 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_setExpr12675);
                        stream_CARTESIAN_PER.add(char_literal541);

                        pushFollow(FOLLOW_setExpr_in_setExpr12679);
                        b2 = setExpr();
                        state._fsp--;

                        stream_setExpr.add(b2.getTree());
                        char_literal542 = (Token) match(input, 427, FOLLOW_427_in_setExpr12681);
                        stream_427.add(char_literal542);

                        // AST REWRITE
                        // elements: b2, b1
                        // token labels:
                        // rule labels: b2, retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1450:7: -> ^( SET_DIFF_TAG $b1 $b2)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1451:9: ^( SET_DIFF_TAG $b1 $b2)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SET_DIFF_TAG, "SET_DIFF_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                adaptor.addChild(root_1, stream_b2.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 4:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1452:5: ( INTERSECT '(' b1= setExpr ',' b2= setExpr ')' -> ^( SET_INTERSECT_TAG $b1 $b2) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1452:5: ( INTERSECT '(' b1= setExpr ',' b2= setExpr ')' -> ^( SET_INTERSECT_TAG $b1 $b2) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1452:6: INTERSECT '(' b1= setExpr ',' b2= setExpr ')'
                    {
                        INTERSECT543 = (Token) match(input, INTERSECT, FOLLOW_INTERSECT_in_setExpr12715);
                        stream_INTERSECT.add(INTERSECT543);

                        char_literal544 = (Token) match(input, 426, FOLLOW_426_in_setExpr12717);
                        stream_426.add(char_literal544);

                        pushFollow(FOLLOW_setExpr_in_setExpr12721);
                        b1 = setExpr();
                        state._fsp--;

                        stream_setExpr.add(b1.getTree());
                        char_literal545 = (Token) match(input, CARTESIAN_PER, FOLLOW_CARTESIAN_PER_in_setExpr12723);
                        stream_CARTESIAN_PER.add(char_literal545);

                        pushFollow(FOLLOW_setExpr_in_setExpr12727);
                        b2 = setExpr();
                        state._fsp--;

                        stream_setExpr.add(b2.getTree());
                        char_literal546 = (Token) match(input, 427, FOLLOW_427_in_setExpr12729);
                        stream_427.add(char_literal546);

                        // AST REWRITE
                        // elements: b1, b2
                        // token labels:
                        // rule labels: b2, retval, b1
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_b2 = new RewriteRuleSubtreeStream(adaptor, "rule b2", b2 != null ? b2.getTree() : null);
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);
                        RewriteRuleSubtreeStream stream_b1 = new RewriteRuleSubtreeStream(adaptor, "rule b1", b1 != null ? b1.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1453:7: -> ^( SET_INTERSECT_TAG $b1 $b2)
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1454:9: ^( SET_INTERSECT_TAG $b1 $b2)
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SET_INTERSECT_TAG, "SET_INTERSECT_TAG"), root_1);
                                adaptor.addChild(root_1, stream_b1.nextTree());
                                adaptor.addChild(root_1, stream_b2.nextTree());
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "varID"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1457:1: varID : IDENTIFIER -> ^( VARID_TAG IDENTIFIER ) ;
    public final ValidationMlParser.varID_return varID() throws Exception {
        ValidationMlParser.varID_return retval = new ValidationMlParser.varID_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENTIFIER547 = null;

        Object IDENTIFIER547_tree = null;
        RewriteRuleTokenStream stream_IDENTIFIER = new RewriteRuleTokenStream(adaptor, "token IDENTIFIER");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1458:3: ( IDENTIFIER -> ^( VARID_TAG IDENTIFIER ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1459:3: IDENTIFIER
            {
                IDENTIFIER547 = (Token) match(input, IDENTIFIER, FOLLOW_IDENTIFIER_in_varID12771);
                stream_IDENTIFIER.add(IDENTIFIER547);

                // AST REWRITE
                // elements: IDENTIFIER
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                retval.tree = root_0;
                RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                root_0 = (Object) adaptor.nil();
                // 1460:5: -> ^( VARID_TAG IDENTIFIER )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1461:7: ^( VARID_TAG IDENTIFIER )
                    {
                        Object root_1 = (Object) adaptor.nil();
                        root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(VARID_TAG, "VARID_TAG"), root_1);
                        adaptor.addChild(root_1, stream_IDENTIFIER.nextNode());
                        adaptor.addChild(root_0, root_1);
                    }

                }


                retval.tree = root_0;

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "compOp"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1464:1: compOp : ( ( EQ -> ^( DATASET_EQ_TAG ) ) | ( LT -> ^( DATASET_LT_TAG ) ) | ( GT -> ^( DATASET_GT_TAG ) ) | ( LE -> ^( DATASET_LE_TAG ) ) | ( GE -> ^( DATASET_GE_TAG ) ) | ( NE -> ^( DATASET_NE_TAG ) ) );
    public final ValidationMlParser.compOp_return compOp() throws Exception {
        ValidationMlParser.compOp_return retval = new ValidationMlParser.compOp_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EQ548 = null;
        Token LT549 = null;
        Token GT550 = null;
        Token LE551 = null;
        Token GE552 = null;
        Token NE553 = null;

        Object EQ548_tree = null;
        Object LT549_tree = null;
        Object GT550_tree = null;
        Object LE551_tree = null;
        Object GE552_tree = null;
        Object NE553_tree = null;
        RewriteRuleTokenStream stream_NE = new RewriteRuleTokenStream(adaptor, "token NE");
        RewriteRuleTokenStream stream_LT = new RewriteRuleTokenStream(adaptor, "token LT");
        RewriteRuleTokenStream stream_LE = new RewriteRuleTokenStream(adaptor, "token LE");
        RewriteRuleTokenStream stream_EQ = new RewriteRuleTokenStream(adaptor, "token EQ");
        RewriteRuleTokenStream stream_GT = new RewriteRuleTokenStream(adaptor, "token GT");
        RewriteRuleTokenStream stream_GE = new RewriteRuleTokenStream(adaptor, "token GE");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1465:3: ( ( EQ -> ^( DATASET_EQ_TAG ) ) | ( LT -> ^( DATASET_LT_TAG ) ) | ( GT -> ^( DATASET_GT_TAG ) ) | ( LE -> ^( DATASET_LE_TAG ) ) | ( GE -> ^( DATASET_GE_TAG ) ) | ( NE -> ^( DATASET_NE_TAG ) ) )
            int alt87 = 6;
            switch (input.LA(1)) {
                case EQ: {
                    alt87 = 1;
                }
                break;
                case LT: {
                    alt87 = 2;
                }
                break;
                case GT: {
                    alt87 = 3;
                }
                break;
                case LE: {
                    alt87 = 4;
                }
                break;
                case GE: {
                    alt87 = 5;
                }
                break;
                case NE: {
                    alt87 = 6;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 87, 0, input);
                    throw nvae;
            }
            switch (alt87) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1466:3: ( EQ -> ^( DATASET_EQ_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1466:3: ( EQ -> ^( DATASET_EQ_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1466:4: EQ
                    {
                        EQ548 = (Token) match(input, EQ, FOLLOW_EQ_in_compOp12805);
                        stream_EQ.add(EQ548);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1467:7: -> ^( DATASET_EQ_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1468:9: ^( DATASET_EQ_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_EQ_TAG, "DATASET_EQ_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1469:5: ( LT -> ^( DATASET_LT_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1469:5: ( LT -> ^( DATASET_LT_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1469:6: LT
                    {
                        LT549 = (Token) match(input, LT, FOLLOW_LT_in_compOp12833);
                        stream_LT.add(LT549);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1470:7: -> ^( DATASET_LT_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1471:9: ^( DATASET_LT_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_LT_TAG, "DATASET_LT_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 3:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1472:5: ( GT -> ^( DATASET_GT_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1472:5: ( GT -> ^( DATASET_GT_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1472:6: GT
                    {
                        GT550 = (Token) match(input, GT, FOLLOW_GT_in_compOp12861);
                        stream_GT.add(GT550);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1473:7: -> ^( DATASET_GT_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1474:9: ^( DATASET_GT_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_GT_TAG, "DATASET_GT_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 4:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1475:5: ( LE -> ^( DATASET_LE_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1475:5: ( LE -> ^( DATASET_LE_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1475:6: LE
                    {
                        LE551 = (Token) match(input, LE, FOLLOW_LE_in_compOp12889);
                        stream_LE.add(LE551);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1476:7: -> ^( DATASET_LE_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1477:9: ^( DATASET_LE_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_LE_TAG, "DATASET_LE_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 5:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1478:5: ( GE -> ^( DATASET_GE_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1478:5: ( GE -> ^( DATASET_GE_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1478:6: GE
                    {
                        GE552 = (Token) match(input, GE, FOLLOW_GE_in_compOp12917);
                        stream_GE.add(GE552);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1479:7: -> ^( DATASET_GE_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1480:9: ^( DATASET_GE_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_GE_TAG, "DATASET_GE_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 6:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1481:5: ( NE -> ^( DATASET_NE_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1481:5: ( NE -> ^( DATASET_NE_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1481:6: NE
                    {
                        NE553 = (Token) match(input, NE, FOLLOW_NE_in_compOp12945);
                        stream_NE.add(NE553);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1482:7: -> ^( DATASET_NE_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1483:9: ^( DATASET_NE_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(DATASET_NE_TAG, "DATASET_NE_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "compOpScalar"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1486:1: compOpScalar : ( ( EQ -> ^( SCALAR_EQ_TAG ) ) | ( LT -> ^( SCALAR_LT_TAG ) ) | ( GT -> ^( SCALAR_GT_TAG ) ) | ( LE -> ^( SCALAR_LE_TAG ) ) | ( GE -> ^( SCALAR_GE_TAG ) ) | ( NE -> ^( SCALAR_NE_TAG ) ) );
    public final ValidationMlParser.compOpScalar_return compOpScalar() throws Exception {
        ValidationMlParser.compOpScalar_return retval = new ValidationMlParser.compOpScalar_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EQ554 = null;
        Token LT555 = null;
        Token GT556 = null;
        Token LE557 = null;
        Token GE558 = null;
        Token NE559 = null;

        Object EQ554_tree = null;
        Object LT555_tree = null;
        Object GT556_tree = null;
        Object LE557_tree = null;
        Object GE558_tree = null;
        Object NE559_tree = null;
        RewriteRuleTokenStream stream_NE = new RewriteRuleTokenStream(adaptor, "token NE");
        RewriteRuleTokenStream stream_LT = new RewriteRuleTokenStream(adaptor, "token LT");
        RewriteRuleTokenStream stream_LE = new RewriteRuleTokenStream(adaptor, "token LE");
        RewriteRuleTokenStream stream_EQ = new RewriteRuleTokenStream(adaptor, "token EQ");
        RewriteRuleTokenStream stream_GT = new RewriteRuleTokenStream(adaptor, "token GT");
        RewriteRuleTokenStream stream_GE = new RewriteRuleTokenStream(adaptor, "token GE");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1487:3: ( ( EQ -> ^( SCALAR_EQ_TAG ) ) | ( LT -> ^( SCALAR_LT_TAG ) ) | ( GT -> ^( SCALAR_GT_TAG ) ) | ( LE -> ^( SCALAR_LE_TAG ) ) | ( GE -> ^( SCALAR_GE_TAG ) ) | ( NE -> ^( SCALAR_NE_TAG ) ) )
            int alt88 = 6;
            switch (input.LA(1)) {
                case EQ: {
                    alt88 = 1;
                }
                break;
                case LT: {
                    alt88 = 2;
                }
                break;
                case GT: {
                    alt88 = 3;
                }
                break;
                case LE: {
                    alt88 = 4;
                }
                break;
                case GE: {
                    alt88 = 5;
                }
                break;
                case NE: {
                    alt88 = 6;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 88, 0, input);
                    throw nvae;
            }
            switch (alt88) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1488:3: ( EQ -> ^( SCALAR_EQ_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1488:3: ( EQ -> ^( SCALAR_EQ_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1488:4: EQ
                    {
                        EQ554 = (Token) match(input, EQ, FOLLOW_EQ_in_compOpScalar12982);
                        stream_EQ.add(EQ554);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1489:7: -> ^( SCALAR_EQ_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1490:9: ^( SCALAR_EQ_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_EQ_TAG, "SCALAR_EQ_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1491:5: ( LT -> ^( SCALAR_LT_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1491:5: ( LT -> ^( SCALAR_LT_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1491:6: LT
                    {
                        LT555 = (Token) match(input, LT, FOLLOW_LT_in_compOpScalar13010);
                        stream_LT.add(LT555);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1492:7: -> ^( SCALAR_LT_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1493:9: ^( SCALAR_LT_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_LT_TAG, "SCALAR_LT_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 3:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1494:5: ( GT -> ^( SCALAR_GT_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1494:5: ( GT -> ^( SCALAR_GT_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1494:6: GT
                    {
                        GT556 = (Token) match(input, GT, FOLLOW_GT_in_compOpScalar13038);
                        stream_GT.add(GT556);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1495:7: -> ^( SCALAR_GT_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1496:9: ^( SCALAR_GT_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_GT_TAG, "SCALAR_GT_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 4:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1497:5: ( LE -> ^( SCALAR_LE_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1497:5: ( LE -> ^( SCALAR_LE_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1497:6: LE
                    {
                        LE557 = (Token) match(input, LE, FOLLOW_LE_in_compOpScalar13066);
                        stream_LE.add(LE557);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1498:7: -> ^( SCALAR_LE_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1499:9: ^( SCALAR_LE_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_LE_TAG, "SCALAR_LE_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 5:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1500:5: ( GE -> ^( SCALAR_GE_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1500:5: ( GE -> ^( SCALAR_GE_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1500:6: GE
                    {
                        GE558 = (Token) match(input, GE, FOLLOW_GE_in_compOpScalar13094);
                        stream_GE.add(GE558);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1501:7: -> ^( SCALAR_GE_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1502:9: ^( SCALAR_GE_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_GE_TAG, "SCALAR_GE_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;
                case 6:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1503:5: ( NE -> ^( SCALAR_NE_TAG ) )
                {
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1503:5: ( NE -> ^( SCALAR_NE_TAG ) )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1503:6: NE
                    {
                        NE559 = (Token) match(input, NE, FOLLOW_NE_in_compOpScalar13122);
                        stream_NE.add(NE559);

                        // AST REWRITE
                        // elements:
                        // token labels:
                        // rule labels: retval
                        // token list labels:
                        // rule list labels:
                        // wildcard labels:
                        retval.tree = root_0;
                        RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                        root_0 = (Object) adaptor.nil();
                        // 1504:7: -> ^( SCALAR_NE_TAG )
                        {
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1505:9: ^( SCALAR_NE_TAG )
                            {
                                Object root_1 = (Object) adaptor.nil();
                                root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(SCALAR_NE_TAG, "SCALAR_NE_TAG"), root_1);
                                adaptor.addChild(root_0, root_1);
                            }

                        }


                        retval.tree = root_0;

                    }

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "constant"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1508:1: constant : ( INTEGER_CONSTANT -> ^( INT_CONSTANT_TAG INTEGER_CONSTANT ) | FLOAT_CONSTANT -> ^( FLOAT_CONSTANT_TAG FLOAT_CONSTANT ) | BOOLEAN_CONSTANT -> ^( BOOLEAN_CONSTANT_TAG BOOLEAN_CONSTANT ) | STRING_CONSTANT -> ^( STRING_CONSTANT_TAG STRING_CONSTANT ) | NULL_CONSTANT -> ^( NULL_CONSTANT_TAG NULL_CONSTANT ) );
    public final ValidationMlParser.constant_return constant() throws Exception {
        ValidationMlParser.constant_return retval = new ValidationMlParser.constant_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token INTEGER_CONSTANT560 = null;
        Token FLOAT_CONSTANT561 = null;
        Token BOOLEAN_CONSTANT562 = null;
        Token STRING_CONSTANT563 = null;
        Token NULL_CONSTANT564 = null;

        Object INTEGER_CONSTANT560_tree = null;
        Object FLOAT_CONSTANT561_tree = null;
        Object BOOLEAN_CONSTANT562_tree = null;
        Object STRING_CONSTANT563_tree = null;
        Object NULL_CONSTANT564_tree = null;
        RewriteRuleTokenStream stream_NULL_CONSTANT = new RewriteRuleTokenStream(adaptor, "token NULL_CONSTANT");
        RewriteRuleTokenStream stream_BOOLEAN_CONSTANT = new RewriteRuleTokenStream(adaptor, "token BOOLEAN_CONSTANT");
        RewriteRuleTokenStream stream_STRING_CONSTANT = new RewriteRuleTokenStream(adaptor, "token STRING_CONSTANT");
        RewriteRuleTokenStream stream_FLOAT_CONSTANT = new RewriteRuleTokenStream(adaptor, "token FLOAT_CONSTANT");
        RewriteRuleTokenStream stream_INTEGER_CONSTANT = new RewriteRuleTokenStream(adaptor, "token INTEGER_CONSTANT");

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1509:3: ( INTEGER_CONSTANT -> ^( INT_CONSTANT_TAG INTEGER_CONSTANT ) | FLOAT_CONSTANT -> ^( FLOAT_CONSTANT_TAG FLOAT_CONSTANT ) | BOOLEAN_CONSTANT -> ^( BOOLEAN_CONSTANT_TAG BOOLEAN_CONSTANT ) | STRING_CONSTANT -> ^( STRING_CONSTANT_TAG STRING_CONSTANT ) | NULL_CONSTANT -> ^( NULL_CONSTANT_TAG NULL_CONSTANT ) )
            int alt89 = 5;
            switch (input.LA(1)) {
                case INTEGER_CONSTANT: {
                    alt89 = 1;
                }
                break;
                case FLOAT_CONSTANT: {
                    alt89 = 2;
                }
                break;
                case BOOLEAN_CONSTANT: {
                    alt89 = 3;
                }
                break;
                case STRING_CONSTANT: {
                    alt89 = 4;
                }
                break;
                case NULL_CONSTANT: {
                    alt89 = 5;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 89, 0, input);
                    throw nvae;
            }
            switch (alt89) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1510:3: INTEGER_CONSTANT
                {
                    INTEGER_CONSTANT560 = (Token) match(input, INTEGER_CONSTANT, FOLLOW_INTEGER_CONSTANT_in_constant13158);
                    stream_INTEGER_CONSTANT.add(INTEGER_CONSTANT560);

                    // AST REWRITE
                    // elements: INTEGER_CONSTANT
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1511:5: -> ^( INT_CONSTANT_TAG INTEGER_CONSTANT )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1512:7: ^( INT_CONSTANT_TAG INTEGER_CONSTANT )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(INT_CONSTANT_TAG, "INT_CONSTANT_TAG"), root_1);
                            adaptor.addChild(root_1, stream_INTEGER_CONSTANT.nextNode());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1513:5: FLOAT_CONSTANT
                {
                    FLOAT_CONSTANT561 = (Token) match(input, FLOAT_CONSTANT, FOLLOW_FLOAT_CONSTANT_in_constant13182);
                    stream_FLOAT_CONSTANT.add(FLOAT_CONSTANT561);

                    // AST REWRITE
                    // elements: FLOAT_CONSTANT
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1514:5: -> ^( FLOAT_CONSTANT_TAG FLOAT_CONSTANT )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1515:7: ^( FLOAT_CONSTANT_TAG FLOAT_CONSTANT )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(FLOAT_CONSTANT_TAG, "FLOAT_CONSTANT_TAG"), root_1);
                            adaptor.addChild(root_1, stream_FLOAT_CONSTANT.nextNode());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;
                case 3:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1516:5: BOOLEAN_CONSTANT
                {
                    BOOLEAN_CONSTANT562 = (Token) match(input, BOOLEAN_CONSTANT, FOLLOW_BOOLEAN_CONSTANT_in_constant13206);
                    stream_BOOLEAN_CONSTANT.add(BOOLEAN_CONSTANT562);

                    // AST REWRITE
                    // elements: BOOLEAN_CONSTANT
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1517:5: -> ^( BOOLEAN_CONSTANT_TAG BOOLEAN_CONSTANT )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1518:7: ^( BOOLEAN_CONSTANT_TAG BOOLEAN_CONSTANT )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(BOOLEAN_CONSTANT_TAG, "BOOLEAN_CONSTANT_TAG"), root_1);
                            adaptor.addChild(root_1, stream_BOOLEAN_CONSTANT.nextNode());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;
                case 4:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1519:5: STRING_CONSTANT
                {
                    STRING_CONSTANT563 = (Token) match(input, STRING_CONSTANT, FOLLOW_STRING_CONSTANT_in_constant13230);
                    stream_STRING_CONSTANT.add(STRING_CONSTANT563);

                    // AST REWRITE
                    // elements: STRING_CONSTANT
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1520:5: -> ^( STRING_CONSTANT_TAG STRING_CONSTANT )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1521:7: ^( STRING_CONSTANT_TAG STRING_CONSTANT )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(STRING_CONSTANT_TAG, "STRING_CONSTANT_TAG"), root_1);
                            adaptor.addChild(root_1, stream_STRING_CONSTANT.nextNode());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;
                case 5:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1522:5: NULL_CONSTANT
                {
                    NULL_CONSTANT564 = (Token) match(input, NULL_CONSTANT, FOLLOW_NULL_CONSTANT_in_constant13254);
                    stream_NULL_CONSTANT.add(NULL_CONSTANT564);

                    // AST REWRITE
                    // elements: NULL_CONSTANT
                    // token labels:
                    // rule labels: retval
                    // token list labels:
                    // rule list labels:
                    // wildcard labels:
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval", retval != null ? retval.getTree() : null);

                    root_0 = (Object) adaptor.nil();
                    // 1523:5: -> ^( NULL_CONSTANT_TAG NULL_CONSTANT )
                    {
                        // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1524:7: ^( NULL_CONSTANT_TAG NULL_CONSTANT )
                        {
                            Object root_1 = (Object) adaptor.nil();
                            root_1 = (Object) adaptor.becomeRoot((Object) adaptor.create(NULL_CONSTANT_TAG, "NULL_CONSTANT_TAG"), root_1);
                            adaptor.addChild(root_1, stream_NULL_CONSTANT.nextNode());
                            adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "isCompl"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1613:1: isCompl : ( ( 'Y' ) | ( 'N' ) );
    public final ValidationMlParser.isCompl_return isCompl() throws Exception {
        ValidationMlParser.isCompl_return retval = new ValidationMlParser.isCompl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set565 = null;

        Object set565_tree = null;

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1614:3: ( ( 'Y' ) | ( 'N' ) )
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:
            {
                root_0 = (Object) adaptor.nil();


                set565 = input.LT(1);
                if (input.LA(1) == 429 || input.LA(1) == 431) {
                    input.consume();
                    adaptor.addChild(root_0, (Object) adaptor.create(set565));
                    state.errorRecovery = false;
                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                }
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $ANTLR start "lhperc"
    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1619:1: lhperc : ( ( PLUS | MINUS )? FLOAT_CONSTANT | ( 'INF' ) | ( PLUS | MINUS )? INTEGER_CONSTANT );
    public final ValidationMlParser.lhperc_return lhperc() throws Exception {
        ValidationMlParser.lhperc_return retval = new ValidationMlParser.lhperc_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set566 = null;
        Token FLOAT_CONSTANT567 = null;
        Token string_literal568 = null;
        Token set569 = null;
        Token INTEGER_CONSTANT570 = null;

        Object set566_tree = null;
        Object FLOAT_CONSTANT567_tree = null;
        Object string_literal568_tree = null;
        Object set569_tree = null;
        Object INTEGER_CONSTANT570_tree = null;

        try {
            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1620:3: ( ( PLUS | MINUS )? FLOAT_CONSTANT | ( 'INF' ) | ( PLUS | MINUS )? INTEGER_CONSTANT )
            int alt92 = 3;
            switch (input.LA(1)) {
                case MINUS:
                case PLUS: {
                    int LA92_1 = input.LA(2);
                    if ((LA92_1 == FLOAT_CONSTANT)) {
                        alt92 = 1;
                    } else if ((LA92_1 == INTEGER_CONSTANT)) {
                        alt92 = 3;
                    } else {
                        int nvaeMark = input.mark();
                        try {
                            input.consume();
                            NoViableAltException nvae =
                                    new NoViableAltException("", 92, 1, input);
                            throw nvae;
                        } finally {
                            input.rewind(nvaeMark);
                        }
                    }

                }
                break;
                case FLOAT_CONSTANT: {
                    alt92 = 1;
                }
                break;
                case 428: {
                    alt92 = 2;
                }
                break;
                case INTEGER_CONSTANT: {
                    alt92 = 3;
                }
                break;
                default:
                    NoViableAltException nvae =
                            new NoViableAltException("", 92, 0, input);
                    throw nvae;
            }
            switch (alt92) {
                case 1:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1621:3: ( PLUS | MINUS )? FLOAT_CONSTANT
                {
                    root_0 = (Object) adaptor.nil();


                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1621:3: ( PLUS | MINUS )?
                    int alt90 = 2;
                    int LA90_0 = input.LA(1);
                    if ((LA90_0 == MINUS || LA90_0 == PLUS)) {
                        alt90 = 1;
                    }
                    switch (alt90) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:
                        {
                            set566 = input.LT(1);
                            if (input.LA(1) == MINUS || input.LA(1) == PLUS) {
                                input.consume();
                                adaptor.addChild(root_0, (Object) adaptor.create(set566));
                                state.errorRecovery = false;
                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                throw mse;
                            }
                        }
                        break;

                    }

                    FLOAT_CONSTANT567 = (Token) match(input, FLOAT_CONSTANT, FOLLOW_FLOAT_CONSTANT_in_lhperc13691);
                    FLOAT_CONSTANT567_tree = (Object) adaptor.create(FLOAT_CONSTANT567);
                    adaptor.addChild(root_0, FLOAT_CONSTANT567_tree);

                }
                break;
                case 2:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1626:5: ( 'INF' )
                {
                    root_0 = (Object) adaptor.nil();


                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1626:5: ( 'INF' )
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1626:6: 'INF'
                    {
                        string_literal568 = (Token) match(input, 428, FOLLOW_428_in_lhperc13698);
                        string_literal568_tree = (Object) adaptor.create(string_literal568);
                        adaptor.addChild(root_0, string_literal568_tree);

                    }

                }
                break;
                case 3:
                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1628:3: ( PLUS | MINUS )? INTEGER_CONSTANT
                {
                    root_0 = (Object) adaptor.nil();


                    // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1628:3: ( PLUS | MINUS )?
                    int alt91 = 2;
                    int LA91_0 = input.LA(1);
                    if ((LA91_0 == MINUS || LA91_0 == PLUS)) {
                        alt91 = 1;
                    }
                    switch (alt91) {
                        case 1:
                            // /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:
                        {
                            set569 = input.LT(1);
                            if (input.LA(1) == MINUS || input.LA(1) == PLUS) {
                                input.consume();
                                adaptor.addChild(root_0, (Object) adaptor.create(set569));
                                state.errorRecovery = false;
                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                throw mse;
                            }
                        }
                        break;

                    }

                    INTEGER_CONSTANT570 = (Token) match(input, INTEGER_CONSTANT, FOLLOW_INTEGER_CONSTANT_in_lhperc13730);
                    INTEGER_CONSTANT570_tree = (Object) adaptor.create(INTEGER_CONSTANT570);
                    adaptor.addChild(root_0, INTEGER_CONSTANT570_tree);

                }
                break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (Object) adaptor.errorNode(input, retval.start, input.LT(-1), re);
        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    public static class start_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class statement_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class expr_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class exprOr_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class exprAnd_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class exprEq_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class exprExists_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class exprComp_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class exprAdd_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class exprMultiply_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class exprFactor_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class exprMember_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class exprAtom_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class variableRef_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class getExpr_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class persistentDatasetID_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class putExpr_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class evalExpr_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class validationExpr_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class mergeExpr_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class hierarchyExpr_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class mappingExpr_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class aggrParam_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class aggregategetClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class aggregateClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class aggrFunctionClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class datasetIDGroup_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class caseElseClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class caseCaseClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class getFiltersClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class getFilterClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class datasetClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class aggrFilterClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class filterClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class ascdescClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class renameClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class aggrFunction_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class percentileFunction_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class calcClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class attrCalcClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class calcClauseItem_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class calcExpr_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class dropClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class dropClauseItem_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class keepClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class keepClauseItem_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class compareClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class inBetweenClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class dimClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class varRole_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class bScalarExpr_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class sExprOr_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class sExprAnd_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class sExprPredicate_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class scalarExpr_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class sExprAdd_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class sExprFactor_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class sExprAtom_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class componentID_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class compOpScalarClause_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class logBase_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class powerExponent_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class exponent_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class setExpr_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class varID_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class compOp_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class compOpScalar_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class constant_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class isCompl_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    public static class lhperc_return extends ParserRuleReturnScope {
        Object tree;

        @Override
        public Object getTree() {
            return tree;
        }
    }

    protected class DFA50 extends DFA {

        public DFA50(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 50;
            this.eot = DFA50_eot;
            this.eof = DFA50_eof;
            this.min = DFA50_min;
            this.max = DFA50_max;
            this.accept = DFA50_accept;
            this.special = DFA50_special;
            this.transition = DFA50_transition;
        }

        @Override
        public String getDescription() {
            return "981:1: datasetClause : ( ( RENAME renameClause -> ^( DATASET_RENAME_CLAUSE_TAG renameClause ) ) | aggrFilterClause | ( calcClause -> ^( DATASET_CALC_CLAUSE_TAG calcClause ) ) | ( attrCalcClause -> ^( DATASET_CALC_CLAUSE_TAG attrCalcClause ) ) | ( keepClause -> ^( DATASET_KEEP_CLAUSE_TAG keepClause ) ) | ( dropClause -> ^( DATASET_DROP_CLAUSE_TAG dropClause ) ) | ( compareClause -> ^( DATASET_COMPARE_TAG compareClause ) ) );";
        }
    }
}

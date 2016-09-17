// $ANTLR 3.5 /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g 2016-09-17 19:42:32

package kohl.hadrien.antlr3;


import org.antlr.runtime.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class ValidationMlLexer extends Lexer {
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
	static final String DFA6_eotS =
			"\4\uffff";
	static final String DFA6_eofS =
			"\4\uffff";
	static final String DFA6_minS =
			"\1\60\1\56\2\uffff";
	static final String DFA6_maxS =
			"\1\71\1\145\2\uffff";
	static final String DFA6_acceptS =
			"\2\uffff\1\1\1\2";
	static final String DFA6_specialS =
			"\4\uffff}>";
	static final String[] DFA6_transitionS = {
			"\12\1",
			"\1\2\1\uffff\12\1\13\uffff\1\3\37\uffff\1\3",
			"",
			""
	};
	static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
	static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
	// $ANTLR end "ABS"
	static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
	// $ANTLR end "AGGREGATE"
	static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
	// $ANTLR end "ALL"
	static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
	// $ANTLR end "AND"
	static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
	// $ANTLR end "AS"
	static final short[][] DFA6_transition;
	// $ANTLR end "ASC"
	static final String DFA13_eotS =
			"\1\uffff\1\57\1\uffff\3\57\1\uffff\4\57\1\124\1\57\1\uffff\1\57\1\136" +
					"\5\57\1\155\1\57\1\160\1\162\3\57\1\uffff\1\u0082\1\57\1\u008f\4\57\3" +
					"\uffff\1\u0097\1\57\1\u0099\2\uffff\1\u009a\1\u008f\5\uffff\4\57\1\u00a1" +
					"\2\57\1\u00a4\3\57\1\u00a8\23\57\3\uffff\1\57\1\u00c8\2\57\1\u00d0\3\57" +
					"\2\uffff\3\57\1\u00d8\1\57\1\u00de\5\57\1\u00e8\3\uffff\1\57\4\uffff\5" +
					"\57\1\u00f0\1\u00f2\7\57\2\uffff\10\57\1\u0109\3\57\1\uffff\7\57\1\uffff" +
					"\1\57\3\uffff\1\u0117\1\57\1\u0119\1\u011a\1\u011b\1\uffff\1\57\1\u011d" +
					"\1\uffff\3\57\1\uffff\11\57\1\u012b\2\57\1\u012f\1\57\1\u0131\5\57\1\u0137" +
					"\4\57\1\u013c\1\57\1\u013e\1\57\1\u0140\1\57\1\uffff\6\57\1\u0149\1\uffff" +
					"\4\57\1\u014e\2\57\1\uffff\5\57\1\uffff\3\57\1\u015e\2\57\1\u0161\2\57" +
					"\1\uffff\1\57\1\u0165\1\57\1\u0168\1\57\1\u016a\1\57\1\uffff\1\57\1\uffff" +
					"\5\57\1\u0172\20\57\1\uffff\13\57\1\u018f\1\57\1\uffff\1\57\3\uffff\1" +
					"\57\1\uffff\3\57\1\u0196\1\u0197\4\57\1\u019c\3\57\1\uffff\3\57\1\uffff" +
					"\1\57\1\uffff\1\u01a5\1\57\1\u01a7\2\57\1\uffff\1\u01aa\3\57\1\uffff\1" +
					"\57\1\uffff\1\57\1\uffff\1\u01b1\2\57\1\u01b4\4\57\1\uffff\4\57\1\uffff" +
					"\15\57\1\u01ca\1\u01cb\1\uffff\1\u01cc\1\57\1\uffff\3\57\1\uffff\2\57" +
					"\1\uffff\1\57\1\uffff\1\u01d4\4\57\1\u01da\1\57\1\uffff\1\u01dc\1\u01dd" +
					"\3\57\1\u01e1\1\57\1\u01e3\1\57\1\u01e5\2\57\1\u01e9\1\57\1\u01eb\1\57" +
					"\1\u01ed\1\57\1\u01ef\1\u01f0\1\u01f1\1\u01f2\5\57\1\u01f8\1\uffff\5\57" +
					"\1\u01ff\2\uffff\1\57\1\u0201\2\57\1\uffff\1\u0205\4\57\1\u020c\1\u020e" +
					"\1\57\1\uffff\1\57\1\uffff\2\57\1\uffff\6\57\1\uffff\2\57\1\uffff\3\57" +
					"\1\u021e\1\57\1\u0220\1\57\1\u01ef\15\57\3\uffff\1\u022f\1\u0230\4\57" +
					"\1\u0235\1\uffff\1\u0236\3\57\1\u023a\1\uffff\1\u023b\2\uffff\3\57\1\uffff" +
					"\1\u023f\1\uffff\1\57\1\uffff\3\57\1\uffff\1\57\1\uffff\1\57\1\uffff\1" +
					"\u0246\4\uffff\1\u0247\1\57\1\u0249\1\u024a\1\u024b\1\uffff\6\57\1\uffff" +
					"\1\57\1\uffff\1\57\1\u0254\1\57\1\uffff\4\57\1\u025b\2\uffff\1\u025c\1" +
					"\uffff\7\57\1\u0264\1\57\1\u0266\5\57\1\uffff\1\u026c\1\uffff\2\57\1\u026f" +
					"\4\57\1\u0274\3\57\1\u0278\2\57\2\uffff\1\u027b\3\57\2\uffff\3\57\2\uffff" +
					"\1\57\1\u0283\1\u0284\1\uffff\1\u0285\4\57\1\u028a\2\uffff\1\u028b\3\uffff" +
					"\4\57\1\u0290\3\57\1\uffff\6\57\2\uffff\1\u029a\3\57\1\u029e\1\u029f\1" +
					"\57\1\uffff\1\57\1\uffff\1\u02a2\1\57\1\u02a4\1\u02a5\1\57\1\uffff\2\57" +
					"\1\uffff\2\57\1\u02ab\1\u02ac\1\uffff\2\57\1\u02af\1\uffff\2\57\1\uffff" +
					"\1\u02b2\2\57\1\u02b5\2\57\1\u02b8\3\uffff\3\57\1\u02bc\2\uffff\2\57\1" +
					"\u02bf\1\57\1\uffff\10\57\1\u02c9\1\uffff\1\57\1\u02cb\1\57\2\uffff\1" +
					"\u02cd\1\57\1\uffff\1\57\2\uffff\1\57\1\u02d1\3\57\2\uffff\1\57\1\u02d6" +
					"\1\uffff\1\57\1\u02d8\1\uffff\2\57\1\uffff\2\57\1\uffff\3\57\1\uffff\1" +
					"\57\1\u02e1\1\uffff\1\u02e2\10\57\1\uffff\1\57\1\uffff\1\57\1\uffff\1" +
					"\57\1\u02ee\1\u02f0\1\uffff\1\u02f1\1\u02f2\1\u02f3\1\u02f4\1\uffff\1" +
					"\57\1\uffff\2\57\1\u02f8\1\57\1\u02fa\3\57\2\uffff\1\u02fe\1\u02ff\1\u0300" +
					"\1\57\1\u0302\5\57\1\u0308\1\uffff\1\57\5\uffff\1\u030a\2\57\1\uffff\1" +
					"\u030d\1\uffff\1\u030e\2\57\3\uffff\1\57\1\uffff\4\57\1\u0316\1\uffff" +
					"\1\57\1\uffff\2\57\2\uffff\3\57\1\u031d\1\57\1\u031f\1\57\1\uffff\4\57" +
					"\1\u0325\1\57\1\uffff\1\57\1\uffff\1\u0328\1\u0329\1\u032a\1\u032c\1\u032d" +
					"\1\uffff\1\u032e\1\57\3\uffff\1\57\3\uffff\2\57\1\u0333\1\57\1\uffff\1" +
					"\u0335\1\uffff";
	// $ANTLR end "ASSIGN"
	static final String DFA13_eofS =
			"\u0336\uffff";
	// $ANTLR end "ATTRCALC"
	static final String DFA13_minS =
			"\1\11\1\142\1\uffff\1\124\1\145\1\141\1\uffff\2\141\1\145\1\104\1\52\1" +
					"\154\1\uffff\1\141\1\75\1\145\1\151\1\146\1\145\1\141\1\75\1\105\1\55" +
					"\1\52\1\145\1\156\1\143\1\uffff\1\53\1\141\1\60\1\155\2\151\1\157\3\uffff" +
					"\1\60\1\131\1\60\2\uffff\1\56\1\60\5\uffff\1\163\1\147\1\154\1\144\1\60" +
					"\1\164\1\147\1\60\1\124\1\164\1\145\1\60\1\154\1\141\1\155\1\162\1\164" +
					"\1\144\1\156\1\144\1\154\1\164\1\163\1\146\1\157\1\155\1\164\1\144\1\142" +
					"\1\105\1\106\3\uffff\1\163\1\60\1\154\1\141\1\60\1\154\1\157\1\154\2\uffff" +
					"\1\164\2\145\1\60\1\142\1\60\1\137\1\145\1\163\1\147\1\146\1\60\3\uffff" +
					"\1\101\4\uffff\1\170\1\137\1\157\2\154\2\60\1\145\1\163\1\162\1\165\1" +
					"\167\1\164\1\157\2\uffff\1\156\2\154\1\166\1\157\1\145\1\155\1\141\1\60" +
					"\1\151\1\164\1\160\1\uffff\1\160\1\145\2\151\1\162\1\164\1\162\1\uffff" +
					"\1\123\3\uffff\1\60\1\162\3\60\1\uffff\1\162\1\60\1\uffff\1\122\1\167" +
					"\1\141\1\uffff\1\143\1\145\1\162\1\143\1\160\1\143\1\156\1\162\1\143\1" +
					"\60\1\151\1\147\1\60\1\163\1\60\1\164\1\141\1\143\1\146\1\143\1\60\1\160" +
					"\2\144\1\145\1\60\1\163\1\60\1\116\1\60\1\145\1\uffff\1\145\1\157\1\154" +
					"\1\150\1\163\1\145\1\60\1\uffff\1\164\1\163\1\155\1\163\1\60\1\162\1\145" +
					"\1\uffff\2\141\1\154\1\145\1\144\1\uffff\1\165\1\151\1\160\1\60\1\164" +
					"\1\145\1\60\1\164\1\147\1\uffff\1\123\1\60\1\144\1\60\1\157\1\60\1\154" +
					"\1\uffff\1\145\1\uffff\1\162\1\146\1\143\1\163\1\145\1\60\1\144\1\153" +
					"\1\160\1\141\1\165\1\145\1\156\1\147\1\165\1\156\2\145\1\170\1\144\1\156" +
					"\1\165\1\uffff\1\155\1\145\1\144\1\155\3\145\1\157\1\156\1\141\1\150\1" +
					"\60\1\124\1\uffff\1\145\3\uffff\1\143\1\uffff\1\111\1\145\1\153\2\60\1" +
					"\154\1\153\1\154\1\141\1\60\1\164\1\143\1\150\1\uffff\1\141\1\145\1\163" +
					"\1\uffff\1\151\1\uffff\1\60\1\163\1\60\1\160\1\162\1\uffff\1\60\2\151" +
					"\1\162\1\uffff\1\164\1\uffff\1\124\1\uffff\1\60\1\166\1\162\1\60\1\145" +
					"\1\165\1\164\1\171\1\uffff\1\145\1\164\1\143\1\145\1\uffff\1\141\1\164" +
					"\2\154\1\165\1\170\1\141\1\162\1\157\1\145\1\154\1\156\1\141\2\60\1\uffff" +
					"\1\60\1\162\1\uffff\1\143\1\164\1\125\1\uffff\1\165\1\145\1\uffff\1\164" +
					"\1\uffff\1\60\1\162\1\154\1\151\1\145\1\60\1\162\1\uffff\2\60\1\145\1" +
					"\155\1\162\1\60\1\144\1\60\1\156\1\60\1\163\1\137\1\60\1\151\1\60\1\162" +
					"\1\60\1\143\4\60\1\162\1\164\1\156\1\147\1\154\1\60\1\uffff\1\111\1\147" +
					"\1\141\1\102\1\145\1\60\2\uffff\1\145\1\60\1\143\1\164\1\uffff\1\60\1" +
					"\150\1\156\1\137\1\156\1\40\1\60\1\156\1\uffff\1\145\1\uffff\2\145\1\uffff" +
					"\2\146\1\151\1\162\1\111\1\146\1\uffff\1\145\1\143\1\uffff\1\143\1\144" +
					"\1\163\1\60\1\162\1\60\1\165\1\60\1\162\1\163\2\141\1\144\1\157\1\171" +
					"\1\163\1\156\1\141\1\154\1\166\1\154\3\uffff\2\60\1\150\1\122\1\160\1" +
					"\170\1\60\1\uffff\1\60\1\141\1\154\1\156\1\60\1\uffff\1\60\2\uffff\1\162" +
					"\1\145\1\156\1\uffff\1\60\1\uffff\1\164\1\uffff\1\150\1\151\1\142\1\uffff" +
					"\1\141\1\uffff\1\162\1\uffff\1\60\4\uffff\1\60\1\163\3\60\1\uffff\1\115" +
					"\1\141\1\154\1\125\1\156\1\144\1\uffff\1\156\1\uffff\1\150\1\60\1\144" +
					"\1\uffff\1\141\1\164\1\143\1\145\1\60\2\uffff\1\60\1\uffff\1\147\1\164" +
					"\1\162\1\160\2\146\1\164\1\60\1\106\1\60\1\154\1\157\1\153\1\145\1\137" +
					"\1\uffff\1\60\1\uffff\1\162\1\143\1\60\2\156\1\145\1\146\1\60\1\145\1" +
					"\164\1\162\1\60\1\141\1\151\2\uffff\1\60\1\105\1\154\1\151\2\uffff\1\160" +
					"\2\164\2\uffff\1\143\2\60\1\uffff\1\60\1\157\1\154\1\145\1\156\1\60\2" +
					"\uffff\1\60\3\uffff\1\105\1\164\1\143\1\124\1\60\1\141\1\147\1\145\1\uffff" +
					"\1\151\1\156\1\137\1\150\1\141\1\171\2\uffff\1\60\1\137\1\143\1\141\2" +
					"\60\1\171\1\uffff\1\111\1\uffff\1\60\1\144\2\60\1\151\1\uffff\1\162\1" +
					"\150\1\uffff\2\143\2\60\1\uffff\1\143\1\150\1\60\1\uffff\1\154\1\144\1" +
					"\uffff\1\60\1\151\1\163\1\60\1\145\1\151\1\60\3\uffff\1\154\1\164\1\150" +
					"\1\60\2\uffff\1\123\1\145\1\60\1\105\1\uffff\2\164\1\143\1\163\1\147\1" +
					"\144\1\141\1\154\1\60\1\uffff\1\154\1\60\1\156\2\uffff\1\60\1\105\1\uffff" +
					"\1\145\2\uffff\1\156\1\60\1\171\2\145\2\uffff\1\164\1\60\1\uffff\1\151" +
					"\1\60\1\uffff\1\143\1\164\1\uffff\1\162\1\154\1\uffff\1\144\1\145\1\141" +
					"\1\uffff\1\124\1\60\1\uffff\1\60\1\145\1\150\1\153\1\164\1\145\1\141\1" +
					"\162\1\165\1\uffff\1\145\1\uffff\1\143\1\uffff\1\122\2\60\1\uffff\4\60" +
					"\1\uffff\1\144\1\uffff\1\141\1\163\1\60\1\145\1\60\1\162\1\166\1\101\2" +
					"\uffff\3\60\1\151\1\60\1\164\1\141\1\145\1\166\1\171\1\60\1\uffff\1\141" +
					"\5\uffff\1\60\1\164\1\137\1\uffff\1\60\1\uffff\1\60\1\151\1\115\3\uffff" +
					"\1\156\1\uffff\1\145\1\143\1\163\1\145\1\60\1\uffff\1\154\1\uffff\1\145" +
					"\1\151\2\uffff\1\157\1\120\1\143\1\60\1\164\1\60\1\154\1\uffff\1\154\1" +
					"\163\1\156\1\162\1\60\1\164\1\uffff\1\145\1\uffff\5\60\1\uffff\1\60\1" +
					"\162\3\uffff\1\141\3\uffff\1\163\1\154\1\60\1\154\1\uffff\1\60\1\uffff";
	// $ANTLR end "ATTRIBUTE"
	static final String DFA13_maxS =
			"\1\172\1\166\1\uffff\1\124\1\171\1\165\1\uffff\1\165\1\162\1\171\1\116" +
					"\1\57\1\170\1\uffff\1\162\1\75\1\145\1\155\1\163\1\145\1\157\1\76\1\105" +
					"\1\55\1\52\2\166\1\165\1\uffff\1\53\1\157\1\172\1\163\2\151\1\157\3\uffff" +
					"\1\172\1\131\1\172\2\uffff\1\145\1\172\5\uffff\1\163\1\147\1\154\1\144" +
					"\1\172\1\164\1\147\1\172\1\124\1\164\1\145\1\172\1\163\1\145\1\165\1\162" +
					"\1\170\1\162\1\163\1\144\1\154\1\164\1\163\1\166\1\157\1\155\1\166\1\144" +
					"\1\155\1\105\1\106\3\uffff\1\163\1\172\1\162\1\141\1\172\1\162\1\157\1" +
					"\154\2\uffff\1\164\2\145\1\172\1\142\1\172\1\156\1\171\1\163\1\167\1\156" +
					"\1\172\3\uffff\1\101\4\uffff\1\170\1\164\1\157\2\154\2\172\1\145\1\163" +
					"\1\162\1\165\1\167\1\164\1\157\2\uffff\1\156\1\164\1\165\1\166\1\157\1" +
					"\162\1\155\1\151\1\172\2\165\1\160\1\uffff\1\160\1\145\2\151\1\162\1\164" +
					"\1\162\1\uffff\1\123\3\uffff\1\172\1\162\3\172\1\uffff\1\162\1\172\1\uffff" +
					"\1\122\1\167\1\141\1\uffff\1\143\1\145\1\162\1\143\1\160\1\144\1\156\1" +
					"\162\1\143\1\172\1\151\1\147\1\172\1\163\1\172\1\164\1\141\1\143\1\146" +
					"\1\143\1\172\1\160\2\144\1\145\1\172\1\163\1\172\1\116\1\172\1\145\1\uffff" +
					"\1\145\1\157\2\154\1\163\1\145\1\172\1\uffff\1\164\1\163\1\155\1\163\1" +
					"\172\1\162\1\145\1\uffff\2\141\1\154\1\145\1\171\1\uffff\1\165\1\166\1" +
					"\160\1\172\1\164\1\145\1\172\1\164\1\147\1\uffff\1\123\1\172\1\144\1\172" +
					"\1\157\1\172\1\154\1\uffff\1\145\1\uffff\1\162\1\146\1\143\1\163\1\145" +
					"\1\172\1\144\1\153\1\160\1\141\1\165\1\145\1\156\1\147\1\165\1\156\2\145" +
					"\1\170\1\144\1\156\1\165\1\uffff\1\155\1\156\1\144\1\155\3\145\1\157\1" +
					"\156\1\141\1\150\1\172\1\124\1\uffff\1\145\3\uffff\1\143\1\uffff\1\111" +
					"\1\145\1\153\2\172\1\154\1\153\1\154\1\141\1\172\1\164\1\145\1\150\1\uffff" +
					"\1\141\1\145\1\163\1\uffff\1\151\1\uffff\1\172\1\163\1\172\1\160\1\162" +
					"\1\uffff\1\172\2\151\1\162\1\uffff\1\164\1\uffff\1\124\1\uffff\1\172\1" +
					"\166\1\162\1\172\1\145\1\165\1\164\1\171\1\uffff\1\145\1\164\1\143\1\145" +
					"\1\uffff\1\141\1\164\2\154\1\165\1\170\1\141\1\162\1\157\1\145\1\154\1" +
					"\156\1\141\2\172\1\uffff\1\172\1\162\1\uffff\1\143\1\164\1\125\1\uffff" +
					"\1\165\1\145\1\uffff\1\164\1\uffff\1\172\1\162\1\154\1\151\1\145\1\172" +
					"\1\162\1\uffff\2\172\1\145\1\155\1\162\1\172\1\144\1\172\1\156\1\172\1" +
					"\163\1\146\1\172\1\151\1\172\1\162\1\172\1\143\4\172\1\162\1\164\1\156" +
					"\1\147\1\154\1\172\1\uffff\1\111\1\147\1\141\1\102\1\145\1\172\2\uffff" +
					"\1\145\1\172\1\143\1\164\1\uffff\1\172\1\150\1\156\1\153\1\156\2\172\1" +
					"\156\1\uffff\1\145\1\uffff\2\145\1\uffff\2\146\1\151\1\162\1\111\1\146" +
					"\1\uffff\1\145\1\143\1\uffff\1\143\1\144\1\163\1\172\1\162\1\172\1\165" +
					"\1\172\1\162\1\163\2\141\1\144\1\157\1\171\1\163\1\156\1\141\1\154\1\166" +
					"\1\154\3\uffff\2\172\1\150\1\122\1\160\1\170\1\172\1\uffff\1\172\1\141" +
					"\1\154\1\156\1\172\1\uffff\1\172\2\uffff\1\162\1\145\1\156\1\uffff\1\172" +
					"\1\uffff\1\164\1\uffff\1\150\1\151\1\142\1\uffff\1\141\1\uffff\1\162\1" +
					"\uffff\1\172\4\uffff\1\172\1\163\3\172\1\uffff\1\115\1\141\1\154\1\125" +
					"\1\156\1\144\1\uffff\1\156\1\uffff\1\150\1\172\1\144\1\uffff\1\141\1\164" +
					"\1\166\1\145\1\172\2\uffff\1\172\1\uffff\1\147\1\164\1\162\1\160\2\146" +
					"\1\164\1\172\1\106\1\172\1\154\1\157\1\153\1\145\1\137\1\uffff\1\172\1" +
					"\uffff\1\162\1\143\1\172\2\156\1\145\1\146\1\172\1\145\1\164\1\162\1\172" +
					"\1\141\1\151\2\uffff\1\172\1\105\1\154\1\151\2\uffff\1\160\2\164\2\uffff" +
					"\1\143\2\172\1\uffff\1\172\1\157\1\154\1\145\1\156\1\172\2\uffff\1\172" +
					"\3\uffff\1\105\1\164\1\143\1\124\1\172\1\141\1\147\1\145\1\uffff\1\151" +
					"\1\156\1\137\1\150\1\141\1\171\2\uffff\1\172\1\137\1\143\1\141\2\172\1" +
					"\171\1\uffff\1\111\1\uffff\1\172\1\144\2\172\1\151\1\uffff\1\162\1\150" +
					"\1\uffff\2\143\2\172\1\uffff\1\143\1\150\1\172\1\uffff\1\154\1\144\1\uffff" +
					"\1\172\1\151\1\163\1\172\1\145\1\151\1\172\3\uffff\1\154\1\164\1\150\1" +
					"\172\2\uffff\1\123\1\145\1\172\1\105\1\uffff\2\164\1\143\1\163\1\147\1" +
					"\144\1\141\1\154\1\172\1\uffff\1\154\1\172\1\156\2\uffff\1\172\1\105\1" +
					"\uffff\1\145\2\uffff\1\156\1\172\1\171\2\145\2\uffff\1\164\1\172\1\uffff" +
					"\1\151\1\172\1\uffff\1\143\1\164\1\uffff\1\162\1\154\1\uffff\1\144\1\145" +
					"\1\141\1\uffff\1\124\1\172\1\uffff\1\172\1\145\1\150\1\153\1\164\1\145" +
					"\1\141\1\162\1\165\1\uffff\1\145\1\uffff\1\143\1\uffff\1\122\2\172\1\uffff" +
					"\4\172\1\uffff\1\144\1\uffff\1\141\1\163\1\172\1\145\1\172\1\162\1\166" +
					"\1\101\2\uffff\3\172\1\151\1\172\1\164\1\141\1\145\1\166\1\171\1\172\1" +
					"\uffff\1\141\5\uffff\1\172\1\164\1\137\1\uffff\1\172\1\uffff\1\172\1\151" +
					"\1\115\3\uffff\1\156\1\uffff\1\145\1\143\1\163\1\145\1\172\1\uffff\1\154" +
					"\1\uffff\1\145\1\151\2\uffff\1\157\1\120\1\143\1\172\1\164\1\172\1\154" +
					"\1\uffff\1\154\1\163\1\156\1\162\1\172\1\164\1\uffff\1\145\1\uffff\5\172" +
					"\1\uffff\1\172\1\162\3\uffff\1\141\3\uffff\1\163\1\154\1\172\1\154\1\uffff" +
					"\1\172\1\uffff";
	// $ANTLR end "AVG"
	static final String DFA13_acceptS =
			"\2\uffff\1\7\3\uffff\1\21\6\uffff\1\53\16\uffff\1\164\7\uffff\1\u009d" +
					"\1\u009e\1\u009f\3\uffff\1\u00a4\1\u00a5\2\uffff\1\u00ac\1\u00ad\2\u00ae" +
					"\1\u00af\37\uffff\1\44\1\u00b0\1\46\10\uffff\1\71\1\73\14\uffff\1\116" +
					"\1\146\1\123\1\uffff\1\140\1\135\1\145\1\144\16\uffff\1\171\1\166\14\uffff" +
					"\1\u00a9\7\uffff\1\u00a1\1\uffff\1\u00a3\1\u00a7\1\u00a8\5\uffff\1\5\2" +
					"\uffff\1\13\3\uffff\1\17\37\uffff\1\52\7\uffff\1\57\7\uffff\1\76\5\uffff" +
					"\1\100\11\uffff\1\121\7\uffff\1\157\1\uffff\1\160\26\uffff\1\u008f\15" +
					"\uffff\1\1\1\uffff\1\3\1\4\1\6\1\uffff\1\12\15\uffff\1\127\3\uffff\1\134" +
					"\1\uffff\1\142\5\uffff\1\45\4\uffff\1\u0083\1\uffff\1\u0085\1\uffff\1" +
					"\u00a0\10\uffff\1\65\4\uffff\1\72\17\uffff\1\112\2\uffff\1\122\3\uffff" +
					"\1\147\2\uffff\1\151\1\uffff\1\156\7\uffff\1\173\34\uffff\1\u009c\6\uffff" +
					"\1\20\1\22\4\uffff\1\31\10\uffff\1\143\1\uffff\1\37\2\uffff\1\47\6\uffff" +
					"\1\50\2\uffff\1\56\25\uffff\1\111\1\113\1\114\7\uffff\1\u00ab\5\uffff" +
					"\1\170\1\uffff\1\u00a6\1\174\3\uffff\1\u0080\1\uffff\1\u0086\1\uffff\1" +
					"\u0088\3\uffff\1\u008c\1\uffff\1\u008e\1\uffff\1\u0091\1\uffff\1\u00aa" +
					"\1\u0093\1\u0094\1\u0095\5\uffff\1\u009b\6\uffff\1\15\1\uffff\1\25\3\uffff" +
					"\1\32\5\uffff\1\133\1\132\1\uffff\1\137\17\uffff\1\64\1\uffff\1\67\16" +
					"\uffff\1\115\1\117\4\uffff\1\155\1\161\3\uffff\1\167\1\172\3\uffff\1\u0081" +
					"\6\uffff\1\u0092\1\u0096\1\uffff\1\u0098\1\u0099\1\u009a\10\uffff\1\30" +
					"\6\uffff\1\131\1\136\7\uffff\1\u0084\1\uffff\1\51\5\uffff\1\66\2\uffff" +
					"\1\75\4\uffff\1\104\3\uffff\1\110\2\uffff\1\120\7\uffff\1\176\1\177\1" +
					"\u0087\4\uffff\1\u0090\1\u0097\4\uffff\1\14\11\uffff\1\141\3\uffff\1\40" +
					"\1\154\2\uffff\1\54\1\uffff\1\60\1\61\5\uffff\1\102\1\103\2\uffff\1\107" +
					"\2\uffff\1\130\2\uffff\1\162\2\uffff\1\175\3\uffff\1\u008d\2\uffff\1\10" +
					"\11\uffff\1\126\1\uffff\1\41\1\uffff\1\u0082\3\uffff\1\70\4\uffff\1\106" +
					"\1\uffff\1\125\10\uffff\1\2\1\11\13\uffff\1\55\1\uffff\1\62\1\74\1\77" +
					"\1\101\1\105\3\uffff\1\163\1\uffff\1\u0089\3\uffff\1\16\1\23\1\27\1\uffff" +
					"\1\34\5\uffff\1\42\1\uffff\1\124\2\uffff\1\165\1\u008a\7\uffff\1\43\6" +
					"\uffff\1\35\1\uffff\1\26\5\uffff\1\u00a2\2\uffff\1\36\1\63\1\150\1\uffff" +
					"\1\152\1\u008b\1\33\4\uffff\1\24\1\uffff\1\153";
	// $ANTLR end "AVGPERIOD"
	static final String DFA13_specialS =
			"\u0336\uffff}>";
	// $ANTLR end "BETWEEN"
	static final String[] DFA13_transitionS = {
			"\1\61\1\62\1\uffff\1\61\1\60\22\uffff\1\61\1\uffff\1\56\1\44\1\uffff" +
					"\1\34\2\uffff\1\45\1\46\1\30\1\35\1\6\1\27\1\uffff\1\13\12\54\1\2\1\uffff" +
					"\1\25\1\15\1\17\2\uffff\1\3\7\57\1\12\3\57\1\26\1\47\4\57\1\50\1\55\4" +
					"\57\1\51\1\57\1\52\1\uffff\1\53\3\uffff\1\1\1\4\1\5\1\10\1\14\1\16\1" +
					"\20\1\21\1\22\1\57\1\23\1\24\1\7\1\31\1\32\1\33\1\57\1\36\1\11\1\37\1" +
					"\40\1\41\1\42\1\43\2\57",
			"\1\63\4\uffff\1\64\4\uffff\1\65\1\uffff\1\66\1\uffff\1\72\2\uffff\1" +
					"\67\1\70\1\uffff\1\71",
			"",
			"\1\73",
			"\1\74\14\uffff\1\75\6\uffff\1\76",
			"\1\77\6\uffff\1\100\6\uffff\1\101\5\uffff\1\102",
			"",
			"\1\103\3\uffff\1\104\3\uffff\1\105\5\uffff\1\106\5\uffff\1\107",
			"\1\110\3\uffff\1\111\3\uffff\1\112\10\uffff\1\113",
			"\1\115\16\uffff\1\116\1\117\3\uffff\1\114",
			"\1\120\11\uffff\1\121",
			"\1\123\4\uffff\1\122",
			"\1\125\3\uffff\1\126\1\uffff\1\127\3\uffff\1\130\1\uffff\1\131",
			"",
			"\1\134\7\uffff\1\132\10\uffff\1\133",
			"\1\135",
			"\1\137",
			"\1\140\3\uffff\1\141",
			"\1\142\6\uffff\1\143\1\144\4\uffff\1\145",
			"\1\146",
			"\1\147\3\uffff\1\151\10\uffff\1\152\1\150",
			"\1\153\1\154",
			"\1\156",
			"\1\157",
			"\1\161",
			"\1\163\11\uffff\1\164\2\uffff\1\165\2\uffff\1\167\1\166",
			"\1\170\3\uffff\1\171\3\uffff\1\172",
			"\1\173\1\uffff\1\174\6\uffff\1\175\2\uffff\1\176\2\uffff\1\u0080\2\uffff" +
					"\1\177",
			"",
			"\1\u0081",
			"\1\u0083\3\uffff\1\u0084\11\uffff\1\u0085",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\1\u0086\1\57\1\u0087\4\57" +
					"\1\u0088\1\u0089\3\57\1\u008a\1\57\1\u008b\2\57\1\u008c\1\u008d\5\57" +
					"\1\u008e\1\57",
			"\1\u0091\1\u0092\1\uffff\1\u0090\2\uffff\1\u0093",
			"\1\u0094",
			"\1\u0095",
			"\1\u0096",
			"",
			"",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0098",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"",
			"\1\u009b\1\uffff\12\54\13\uffff\1\u009b\37\uffff\1\u009b",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"",
			"",
			"",
			"",
			"\1\u009c",
			"\1\u009d",
			"\1\u009e",
			"\1\u009f",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\2\57\1\u00a0\27\57",
			"\1\u00a2",
			"\1\u00a3",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u00a5",
			"\1\u00a6",
			"\1\u00a7",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u00a9\6\uffff\1\u00aa",
			"\1\u00ab\3\uffff\1\u00ac",
			"\1\u00ad\1\u00ae\6\uffff\1\u00af",
			"\1\u00b0",
			"\1\u00b1\3\uffff\1\u00b2",
			"\1\u00b3\15\uffff\1\u00b4",
			"\1\u00b5\4\uffff\1\u00b6",
			"\1\u00b7",
			"\1\u00b8",
			"\1\u00b9",
			"\1\u00ba",
			"\1\u00bb\14\uffff\1\u00bc\2\uffff\1\u00bd",
			"\1\u00be",
			"\1\u00bf",
			"\1\u00c0\1\uffff\1\u00c1",
			"\1\u00c2",
			"\1\u00c3\12\uffff\1\u00c4",
			"\1\u00c5",
			"\1\u00c6",
			"",
			"",
			"",
			"\1\u00c7",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u00c9\5\uffff\1\u00ca",
			"\1\u00cb",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\2\57\1\u00cc\5\57\1\u00cd" +
					"\1\57\1\u00ce\4\57\1\u00cf\12\57",
			"\1\u00d1\5\uffff\1\u00d2",
			"\1\u00d3",
			"\1\u00d4",
			"",
			"",
			"\1\u00d5",
			"\1\u00d6",
			"\1\u00d7",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u00d9",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\1\57\1\u00da\1\u00db\1\u00dc" +
					"\17\57\1\u00dd\6\57",
			"\1\u00e0\16\uffff\1\u00df",
			"\1\u00e1\23\uffff\1\u00e2",
			"\1\u00e3",
			"\1\u00e5\17\uffff\1\u00e4",
			"\1\u00e6\7\uffff\1\u00e7",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"",
			"",
			"\1\u00e9",
			"",
			"",
			"",
			"",
			"\1\u00ea",
			"\1\u00eb\24\uffff\1\u00ec",
			"\1\u00ed",
			"\1\u00ee",
			"\1\u00ef",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\3\57\1\u00f1\26\57",
			"\1\u00f3",
			"\1\u00f4",
			"\1\u00f5",
			"\1\u00f6",
			"\1\u00f7",
			"\1\u00f8",
			"\1\u00f9",
			"",
			"",
			"\1\u00fa",
			"\1\u00fb\1\uffff\1\u00fc\5\uffff\1\u00fd",
			"\1\u00fe\10\uffff\1\u00ff",
			"\1\u0100",
			"\1\u0101",
			"\1\u0102\14\uffff\1\u0103",
			"\1\u0104",
			"\1\u0105\3\uffff\1\u0106\3\uffff\1\u0107",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\2\57\1\u0108\27\57",
			"\1\u010a\13\uffff\1\u010b",
			"\1\u010c\1\u010d",
			"\1\u010e",
			"",
			"\1\u010f",
			"\1\u0110",
			"\1\u0111",
			"\1\u0112",
			"\1\u0113",
			"\1\u0114",
			"\1\u0115",
			"",
			"\1\u0116",
			"",
			"",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0118",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\1\u011c",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\1\u011e",
			"\1\u011f",
			"\1\u0120",
			"",
			"\1\u0121",
			"\1\u0122",
			"\1\u0123",
			"\1\u0124",
			"\1\u0125",
			"\1\u0126\1\u0127",
			"\1\u0128",
			"\1\u0129",
			"\1\u012a",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u012c",
			"\1\u012d",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\24\57\1\u012e\5\57",
			"\1\u0130",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0132",
			"\1\u0133",
			"\1\u0134",
			"\1\u0135",
			"\1\u0136",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0138",
			"\1\u0139",
			"\1\u013a",
			"\1\u013b",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u013d",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u013f",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0141",
			"",
			"\1\u0142",
			"\1\u0143",
			"\1\u0144",
			"\1\u0145\3\uffff\1\u0146",
			"\1\u0147",
			"\1\u0148",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\1\u014a",
			"\1\u014b",
			"\1\u014c",
			"\1\u014d",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u014f",
			"\1\u0150",
			"",
			"\1\u0151",
			"\1\u0152",
			"\1\u0153",
			"\1\u0154",
			"\1\u0155\1\u0156\7\uffff\1\u0157\13\uffff\1\u0158",
			"",
			"\1\u0159",
			"\1\u015a\14\uffff\1\u015b",
			"\1\u015c",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\22\57\1\u015d\7\57",
			"\1\u015f",
			"\1\u0160",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0162",
			"\1\u0163",
			"",
			"\1\u0164",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0166",
			"\12\57\7\uffff\32\57\4\uffff\1\u0167\1\uffff\32\57",
			"\1\u0169",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u016b",
			"",
			"\1\u016c",
			"",
			"\1\u016d",
			"\1\u016e",
			"\1\u016f",
			"\1\u0170",
			"\1\u0171",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0173",
			"\1\u0174",
			"\1\u0175",
			"\1\u0176",
			"\1\u0177",
			"\1\u0178",
			"\1\u0179",
			"\1\u017a",
			"\1\u017b",
			"\1\u017c",
			"\1\u017d",
			"\1\u017e",
			"\1\u017f",
			"\1\u0180",
			"\1\u0181",
			"\1\u0182",
			"",
			"\1\u0183",
			"\1\u0185\10\uffff\1\u0184",
			"\1\u0186",
			"\1\u0187",
			"\1\u0188",
			"\1\u0189",
			"\1\u018a",
			"\1\u018b",
			"\1\u018c",
			"\1\u018d",
			"\1\u018e",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0190",
			"",
			"\1\u0191",
			"",
			"",
			"",
			"\1\u0192",
			"",
			"\1\u0193",
			"\1\u0194",
			"\1\u0195",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0198",
			"\1\u0199",
			"\1\u019a",
			"\1\u019b",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u019d",
			"\1\u019e\1\uffff\1\u019f",
			"\1\u01a0",
			"",
			"\1\u01a1",
			"\1\u01a2",
			"\1\u01a3",
			"",
			"\1\u01a4",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u01a6",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u01a8",
			"\1\u01a9",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u01ab",
			"\1\u01ac",
			"\1\u01ad",
			"",
			"\1\u01ae",
			"",
			"\1\u01af",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\10\57\1\u01b0\21\57",
			"\1\u01b2",
			"\1\u01b3",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u01b5",
			"\1\u01b6",
			"\1\u01b7",
			"\1\u01b8",
			"",
			"\1\u01b9",
			"\1\u01ba",
			"\1\u01bb",
			"\1\u01bc",
			"",
			"\1\u01bd",
			"\1\u01be",
			"\1\u01bf",
			"\1\u01c0",
			"\1\u01c1",
			"\1\u01c2",
			"\1\u01c3",
			"\1\u01c4",
			"\1\u01c5",
			"\1\u01c6",
			"\1\u01c7",
			"\1\u01c8",
			"\1\u01c9",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u01cd",
			"",
			"\1\u01ce",
			"\1\u01cf",
			"\1\u01d0",
			"",
			"\1\u01d1",
			"\1\u01d2",
			"",
			"\1\u01d3",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u01d5",
			"\1\u01d6",
			"\1\u01d7",
			"\1\u01d8",
			"\2\57\1\u01d9\7\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u01db",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u01de",
			"\1\u01df",
			"\1\u01e0",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u01e2",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u01e4",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u01e6",
			"\1\u01e8\6\uffff\1\u01e7",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u01ea",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u01ec",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u01ee",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u01f3",
			"\1\u01f4",
			"\1\u01f5",
			"\1\u01f6",
			"\1\u01f7",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\1\u01f9",
			"\1\u01fa",
			"\1\u01fb",
			"\1\u01fc",
			"\1\u01fd",
			"\12\57\7\uffff\32\57\4\uffff\1\u01fe\1\uffff\32\57",
			"",
			"",
			"\1\u0200",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0202",
			"\1\u0203",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\u0204\1\uffff\32\57",
			"\1\u0206",
			"\1\u0207",
			"\1\u0208\13\uffff\1\u0209",
			"\1\u020a",
			"\1\u020b\17\uffff\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\2\57\1\u020d\7\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u020f",
			"",
			"\1\u0210",
			"",
			"\1\u0211",
			"\1\u0212",
			"",
			"\1\u0213",
			"\1\u0214",
			"\1\u0215",
			"\1\u0216",
			"\1\u0217",
			"\1\u0218",
			"",
			"\1\u0219",
			"\1\u021a",
			"",
			"\1\u021b",
			"\1\u021c",
			"\1\u021d",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u021f",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0221",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0222",
			"\1\u0223",
			"\1\u0224",
			"\1\u0225",
			"\1\u0226",
			"\1\u0227",
			"\1\u0228",
			"\1\u0229",
			"\1\u022a",
			"\1\u022b",
			"\1\u022c",
			"\1\u022d",
			"\1\u022e",
			"",
			"",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0231",
			"\1\u0232",
			"\1\u0233",
			"\1\u0234",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0237",
			"\1\u0238",
			"\1\u0239",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"",
			"\1\u023c",
			"\1\u023d",
			"\1\u023e",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\1\u0240",
			"",
			"\1\u0241",
			"\1\u0242",
			"\1\u0243",
			"",
			"\1\u0244",
			"",
			"\1\u0245",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"",
			"",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0248",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\1\u024c",
			"\1\u024d",
			"\1\u024e",
			"\1\u024f",
			"\1\u0250",
			"\1\u0251",
			"",
			"\1\u0252",
			"",
			"\1\u0253",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0255",
			"",
			"\1\u0256",
			"\1\u0257",
			"\1\u0258\22\uffff\1\u0259",
			"\1\u025a",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\1\u025d",
			"\1\u025e",
			"\1\u025f",
			"\1\u0260",
			"\1\u0261",
			"\1\u0262",
			"\1\u0263",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0265",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0267",
			"\1\u0268",
			"\1\u0269",
			"\1\u026a",
			"\1\u026b",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\1\u026d",
			"\1\u026e",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0270",
			"\1\u0271",
			"\1\u0272",
			"\1\u0273",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0275",
			"\1\u0276",
			"\1\u0277",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0279",
			"\1\u027a",
			"",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u027c",
			"\1\u027d",
			"\1\u027e",
			"",
			"",
			"\1\u027f",
			"\1\u0280",
			"\1\u0281",
			"",
			"",
			"\1\u0282",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0286",
			"\1\u0287",
			"\1\u0288",
			"\1\u0289",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"",
			"",
			"\1\u028c",
			"\1\u028d",
			"\1\u028e",
			"\1\u028f",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0291",
			"\1\u0292",
			"\1\u0293",
			"",
			"\1\u0294",
			"\1\u0295",
			"\1\u0296",
			"\1\u0297",
			"\1\u0298",
			"\1\u0299",
			"",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u029b",
			"\1\u029c",
			"\1\u029d",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u02a0",
			"",
			"\1\u02a1",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u02a3",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u02a6",
			"",
			"\1\u02a7",
			"\1\u02a8",
			"",
			"\1\u02a9",
			"\1\u02aa",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\1\u02ad",
			"\1\u02ae",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\1\u02b0",
			"\1\u02b1",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u02b3",
			"\1\u02b4",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u02b6",
			"\1\u02b7",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"",
			"",
			"\1\u02b9",
			"\1\u02ba",
			"\1\u02bb",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"",
			"\1\u02bd",
			"\1\u02be",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u02c0",
			"",
			"\1\u02c1",
			"\1\u02c2",
			"\1\u02c3",
			"\1\u02c4",
			"\1\u02c5",
			"\1\u02c6",
			"\1\u02c7",
			"\1\u02c8",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\1\u02ca",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u02cc",
			"",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u02ce",
			"",
			"\1\u02cf",
			"",
			"",
			"\1\u02d0",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u02d2",
			"\1\u02d3",
			"\1\u02d4",
			"",
			"",
			"\1\u02d5",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\1\u02d7",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\1\u02d9",
			"\1\u02da",
			"",
			"\1\u02db",
			"\1\u02dc",
			"",
			"\1\u02dd",
			"\1\u02de",
			"\1\u02df",
			"",
			"\1\u02e0",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u02e3",
			"\1\u02e4",
			"\1\u02e5",
			"\1\u02e6",
			"\1\u02e7",
			"\1\u02e8",
			"\1\u02e9",
			"\1\u02ea",
			"",
			"\1\u02eb",
			"",
			"\1\u02ec",
			"",
			"\1\u02ed",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\u02ef\1\uffff\32\57",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\1\u02f5",
			"",
			"\1\u02f6",
			"\1\u02f7",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u02f9",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u02fb",
			"\1\u02fc",
			"\1\u02fd",
			"",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0301",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0303",
			"\1\u0304",
			"\1\u0305",
			"\1\u0306",
			"\1\u0307",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\1\u0309",
			"",
			"",
			"",
			"",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u030b",
			"\1\u030c",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u030f",
			"\1\u0310",
			"",
			"",
			"",
			"\1\u0311",
			"",
			"\1\u0312",
			"\1\u0313",
			"\1\u0314",
			"\1\u0315",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\1\u0317",
			"",
			"\1\u0318",
			"\1\u0319",
			"",
			"",
			"\1\u031a",
			"\1\u031b",
			"\1\u031c",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u031e",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0320",
			"",
			"\1\u0321",
			"\1\u0322",
			"\1\u0323",
			"\1\u0324",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0326",
			"",
			"\1\u0327",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\u032b\1\uffff\32\57",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u032f",
			"",
			"",
			"",
			"\1\u0330",
			"",
			"",
			"",
			"\1\u0331",
			"\1\u0332",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			"\1\u0334",
			"",
			"\12\57\7\uffff\32\57\4\uffff\1\57\1\uffff\32\57",
			""
	};
	// $ANTLR end "BREAK"
	static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
	// $ANTLR end "BREAKDATE"
	static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
	// $ANTLR end "BY"
	static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
	// $ANTLR end "CALC"
	static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
	// $ANTLR end "CARTESIAN_PER"
	static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
	// $ANTLR end "CASE"
	static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
	// $ANTLR end "CHARLENGTH"
	static final short[][] DFA13_transition;
	// $ANTLR end "CHARSET_MATCH"

	static {
		int numStates = DFA6_transitionS.length;
		DFA6_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
		}
	}
	// $ANTLR end "CHECK"

	static {
		int numStates = DFA13_transitionS.length;
		DFA13_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
		}
	}
	// $ANTLR end "CODELIST_MATCH"

	protected DFA6 dfa6 = new DFA6(this);
	// $ANTLR end "COMPLCHECK"
	protected DFA13 dfa13 = new DFA13(this);
	// $ANTLR end "CONCAT"
	List<RecognitionException> exceptions = new ArrayList<RecognitionException>();
	// $ANTLR end "COND"

	public ValidationMlLexer() {
	}
	// $ANTLR end "COUNT"

	public ValidationMlLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	// $ANTLR end "COUNT_DISTINCT"

	public ValidationMlLexer(CharStream input, RecognizerSharedState state) {
		super(input, state);
	}
	// $ANTLR end "CURRCHANGE"

	public List<RecognitionException> getExceptions() {
		return exceptions;
	}
	// $ANTLR end "CURRENT_DATE"

	@Override
	public void reportError(RecognitionException e) {
		super.reportError(e);
		exceptions.add(e);
	}
	// $ANTLR end "DATASET_LEVEL"

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[]{};
	}
	// $ANTLR end "DESC"

	@Override
	public String getGrammarFileName() {
		return "/Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g";
	}
	// $ANTLR end "DIFF"

	// $ANTLR start "ABS"
	public final void mABS() throws RecognitionException {
		try {
			int _type = ABS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:19:5: ( 'abs' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:19:7: 'abs'
			{
				match("abs");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIFFPERC"

	// $ANTLR start "AGGREGATE"
	public final void mAGGREGATE() throws RecognitionException {
		try {
			int _type = AGGREGATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:20:11: ( 'aggregate' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:20:13: 'aggregate'
			{
				match("aggregate");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIMENSION"

	// $ANTLR start "ALL"
	public final void mALL() throws RecognitionException {
		try {
			int _type = ALL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:21:5: ( 'all' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:21:7: 'all'
			{
				match("all");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DISCREPANCY"

	// $ANTLR start "AND"
	public final void mAND() throws RecognitionException {
		try {
			int _type = AND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:22:5: ( 'and' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:22:7: 'and'
			{
				match("and");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIVDIV"

	// $ANTLR start "AS"
	public final void mAS() throws RecognitionException {
		try {
			int _type = AS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:23:4: ( 'as' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:23:6: 'as'
			{
				match("as");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIVFUN"

	// $ANTLR start "ASC"
	public final void mASC() throws RecognitionException {
		try {
			int _type = ASC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:24:5: ( 'asc' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:24:7: 'asc'
			{
				match("asc");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIVIDE"

	// $ANTLR start "ASSIGN"
	public final void mASSIGN() throws RecognitionException {
		try {
			int _type = ASSIGN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:25:8: ( ':=' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:25:10: ':='
			{
				match(":=");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DROP"

	// $ANTLR start "ATTRCALC"
	public final void mATTRCALC() throws RecognitionException {
		try {
			int _type = ATTRCALC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:26:10: ( 'attrcalc' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:26:12: 'attrcalc'
			{
				match("attrcalc");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ELSE"

	// $ANTLR start "ATTRIBUTE"
	public final void mATTRIBUTE() throws RecognitionException {
		try {
			int _type = ATTRIBUTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:27:11: ( 'ATTRIBUTE' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:27:13: 'ATTRIBUTE'
			{
				match("ATTRIBUTE");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ELSEIF"

	// $ANTLR start "AVG"
	public final void mAVG() throws RecognitionException {
		try {
			int _type = AVG;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:28:5: ( 'avg' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:28:7: 'avg'
			{
				match("avg");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ENDPERIOD"

	// $ANTLR start "AVGPERIOD"
	public final void mAVGPERIOD() throws RecognitionException {
		try {
			int _type = AVGPERIOD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:29:11: ( 'ap' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:29:13: 'ap'
			{
				match("ap");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQ"

	// $ANTLR start "BETWEEN"
	public final void mBETWEEN() throws RecognitionException {
		try {
			int _type = BETWEEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:30:9: ( 'between' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:30:11: 'between'
			{
				match("between");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ERLEVEL"

	// $ANTLR start "BREAK"
	public final void mBREAK() throws RecognitionException {
		try {
			int _type = BREAK;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:31:7: ( 'break' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:31:9: 'break'
			{
				match("break");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ERRORCODE"

	// $ANTLR start "BREAKDATE"
	public final void mBREAKDATE() throws RecognitionException {
		try {
			int _type = BREAKDATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:32:11: ( 'break_date' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:32:13: 'break_date'
			{
				match("break_date");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EVAL"

	// $ANTLR start "BY"
	public final void mBY() throws RecognitionException {
		try {
			int _type = BY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:33:4: ( 'by' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:33:6: 'by'
			{
				match("by");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EX"

	// $ANTLR start "CALC"
	public final void mCALC() throws RecognitionException {
		try {
			int _type = CALC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:34:6: ( 'calc' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:34:8: 'calc'
			{
				match("calc");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXCHECK"

	// $ANTLR start "CARTESIAN_PER"
	public final void mCARTESIAN_PER() throws RecognitionException {
		try {
			int _type = CARTESIAN_PER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:35:15: ( ',' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:35:17: ','
			{
				match(',');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXCLUDE"

	// $ANTLR start "CASE"
	public final void mCASE() throws RecognitionException {
		try {
			int _type = CASE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:36:6: ( 'case' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:36:8: 'case'
			{
				match("case");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXISTS_IN"

	// $ANTLR start "CHARLENGTH"
	public final void mCHARLENGTH() throws RecognitionException {
		try {
			int _type = CHARLENGTH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:37:12: ( 'charlength' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:37:14: 'charlength'
			{
				match("charlength");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXISTS_IN_ALL"

	// $ANTLR start "CHARSET_MATCH"
	public final void mCHARSET_MATCH() throws RecognitionException {
		try {
			int _type = CHARSET_MATCH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:38:15: ( 'match_characters' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:38:17: 'match_characters'
			{
				match("match_characters");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXKEY"

	// $ANTLR start "CHECK"
	public final void mCHECK() throws RecognitionException {
		try {
			int _type = CHECK;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:39:7: ( 'check' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:39:9: 'check'
			{
				match("check");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXP"

	// $ANTLR start "CODELIST_MATCH"
	public final void mCODELIST_MATCH() throws RecognitionException {
		try {
			int _type = CODELIST_MATCH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:40:16: ( 'match_values' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:40:18: 'match_values'
			{
				match("match_values");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FILTER"

	// $ANTLR start "COMPLCHECK"
	public final void mCOMPLCHECK() throws RecognitionException {
		try {
			int _type = COMPLCHECK;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:41:12: ( 'complcheck' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:41:14: 'complcheck'
			{
				match("complcheck");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FIRST"

	// $ANTLR start "CONCAT"
	public final void mCONCAT() throws RecognitionException {
		try {
			int _type = CONCAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:42:8: ( 'concat' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:42:10: 'concat'
			{
				match("concat");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FROM_CURR"

	// $ANTLR start "COND"
	public final void mCOND() throws RecognitionException {
		try {
			int _type = COND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:43:6: ( 'cond' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:43:8: 'cond'
			{
				match("cond");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GE"

	// $ANTLR start "COUNT"
	public final void mCOUNT() throws RecognitionException {
		try {
			int _type = COUNT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:44:7: ( 'count' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:44:9: 'count'
			{
				match("count");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GET"

	// $ANTLR start "COUNT_DISTINCT"
	public final void mCOUNT_DISTINCT() throws RecognitionException {
		try {
			int _type = COUNT_DISTINCT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:45:16: ( 'count_distinct' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:45:18: 'count_distinct'
			{
				match("count_distinct");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GT"

	// $ANTLR start "CURRCHANGE"
	public final void mCURRCHANGE() throws RecognitionException {
		try {
			int _type = CURRCHANGE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:46:12: ( 'currchange' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:46:14: 'currchange'
			{
				match("currchange");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HIERARCHY"

	// $ANTLR start "CURRENT_DATE"
	public final void mCURRENT_DATE() throws RecognitionException {
		try {
			int _type = CURRENT_DATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:47:14: ( 'current_date' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:47:16: 'current_date'
			{
				match("current_date");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HMEETS"

	// $ANTLR start "DATASET_LEVEL"
	public final void mDATASET_LEVEL() throws RecognitionException {
		try {
			int _type = DATASET_LEVEL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:48:15: ( 'dataset_level' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:48:17: 'dataset_level'
			{
				match("dataset_level");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IF"

	// $ANTLR start "DESC"
	public final void mDESC() throws RecognitionException {
		try {
			int _type = DESC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:49:6: ( 'desc' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:49:8: 'desc'
			{
				match("desc");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IMBALANCE"

	// $ANTLR start "DIFF"
	public final void mDIFF() throws RecognitionException {
		try {
			int _type = DIFF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:50:6: ( 'symdiff' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:50:8: 'symdiff'
			{
				match("symdiff");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IN"

	// $ANTLR start "DIFFPERC"
	public final void mDIFFPERC() throws RecognitionException {
		try {
			int _type = DIFFPERC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:51:10: ( 'diffperc' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:51:12: 'diffperc'
			{
				match("diffperc");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INBALANCE"

	// $ANTLR start "DIMENSION"
	public final void mDIMENSION() throws RecognitionException {
		try {
			int _type = DIMENSION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:52:11: ( 'IDENTIFIER' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:52:13: 'IDENTIFIER'
			{
				match("IDENTIFIER");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INCLUDE"

	// $ANTLR start "DISCREPANCY"
	public final void mDISCREPANCY() throws RecognitionException {
		try {
			int _type = DISCREPANCY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:53:13: ( 'discrepancy' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:53:15: 'discrepancy'
			{
				match("discrepancy");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INDEXOF"

	// $ANTLR start "DIVDIV"
	public final void mDIVDIV() throws RecognitionException {
		try {
			int _type = DIVDIV;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:54:8: ( '//' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:54:10: '//'
			{
				match("//");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTDAY"

	// $ANTLR start "DIVFUN"
	public final void mDIVFUN() throws RecognitionException {
		try {
			int _type = DIVFUN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:55:8: ( 'div' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:55:10: 'div'
			{
				match("div");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTERSECT"

	// $ANTLR start "DIVIDE"
	public final void mDIVIDE() throws RecognitionException {
		try {
			int _type = DIVIDE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:56:8: ( '/' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:56:10: '/'
			{
				match('/');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTMONTH"

	// $ANTLR start "DROP"
	public final void mDROP() throws RecognitionException {
		try {
			int _type = DROP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:57:6: ( 'drop' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:57:8: 'drop'
			{
				match("drop");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTYEAR"

	// $ANTLR start "ELSE"
	public final void mELSE() throws RecognitionException {
		try {
			int _type = ELSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:58:6: ( 'else' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:58:8: 'else'
			{
				match("else");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ISNULL"

	// $ANTLR start "ELSEIF"
	public final void mELSEIF() throws RecognitionException {
		try {
			int _type = ELSEIF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:59:8: ( 'elseif' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:59:10: 'elseif'
			{
				match("elseif");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KEEP"

	// $ANTLR start "ENDPERIOD"
	public final void mENDPERIOD() throws RecognitionException {
		try {
			int _type = ENDPERIOD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:60:11: ( 'ep' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:60:13: 'ep'
			{
				match("ep");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KEY"

	// $ANTLR start "EQ"
	public final void mEQ() throws RecognitionException {
		try {
			int _type = EQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:61:4: ( '=' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:61:6: '='
			{
				match('=');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KEYS"

	// $ANTLR start "ERLEVEL"
	public final void mERLEVEL() throws RecognitionException {
		try {
			int _type = ERLEVEL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:62:9: ( 'erlevel' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:62:11: 'erlevel'
			{
				match("erlevel");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LAST"

	// $ANTLR start "ERRORCODE"
	public final void mERRORCODE() throws RecognitionException {
		try {
			int _type = ERRORCODE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:63:11: ( 'errorcode' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:63:13: 'errorcode'
			{
				match("errorcode");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LCASE"

	// $ANTLR start "EVAL"
	public final void mEVAL() throws RecognitionException {
		try {
			int _type = EVAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:64:6: ( 'eval' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:64:8: 'eval'
			{
				match("eval");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LE"

	// $ANTLR start "EX"
	public final void mEX() throws RecognitionException {
		try {
			int _type = EX;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:65:4: ( 'ex' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:65:6: 'ex'
			{
				match("ex");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LEFTC"

	// $ANTLR start "EXCHECK"
	public final void mEXCHECK() throws RecognitionException {
		try {
			int _type = EXCHECK;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:66:9: ( 'excheck' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:66:11: 'excheck'
			{
				match("excheck");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LEN"

	// $ANTLR start "EXCLUDE"
	public final void mEXCLUDE() throws RecognitionException {
		try {
			int _type = EXCLUDE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:67:9: ( 'exclude' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:67:11: 'exclude'
			{
				match("exclude");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LN"

	// $ANTLR start "EXISTS_IN"
	public final void mEXISTS_IN() throws RecognitionException {
		try {
			int _type = EXISTS_IN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:68:11: ( 'exists_in' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:68:13: 'exists_in'
			{
				match("exists_in");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LOG"

	// $ANTLR start "EXISTS_IN_ALL"
	public final void mEXISTS_IN_ALL() throws RecognitionException {
		try {
			int _type = EXISTS_IN_ALL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:69:15: ( 'exists_in_all' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:69:17: 'exists_in_all'
			{
				match("exists_in_all");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LT"

	// $ANTLR start "EXKEY"
	public final void mEXKEY() throws RecognitionException {
		try {
			int _type = EXKEY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:70:7: ( 'exkey' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:70:9: 'exkey'
			{
				match("exkey");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MATCHES_INVALID"

	// $ANTLR start "EXP"
	public final void mEXP() throws RecognitionException {
		try {
			int _type = EXP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:71:5: ( 'exp' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:71:7: 'exp'
			{
				match("exp");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MATCHES_VALID"

	// $ANTLR start "FILTER"
	public final void mFILTER() throws RecognitionException {
		try {
			int _type = FILTER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:72:8: ( 'filter' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:72:10: 'filter'
			{
				match("filter");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MATCHKEY"

	// $ANTLR start "FIRST"
	public final void mFIRST() throws RecognitionException {
		try {
			int _type = FIRST;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:73:7: ( 'first' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:73:9: 'first'
			{
				match("first");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MAX"

	// $ANTLR start "FROM_CURR"
	public final void mFROM_CURR() throws RecognitionException {
		try {
			int _type = FROM_CURR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:74:11: ( 'fromcurr' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:74:13: 'fromcurr'
			{
				match("fromcurr");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MEASURE"

	// $ANTLR start "GE"
	public final void mGE() throws RecognitionException {
		try {
			int _type = GE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:75:4: ( '>=' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:75:6: '>='
			{
				match(">=");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MEDIAN"

	// $ANTLR start "GET"
	public final void mGET() throws RecognitionException {
		try {
			int _type = GET;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:76:5: ( 'get' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:76:7: 'get'
			{
				match("get");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MERGE"

	// $ANTLR start "GT"
	public final void mGT() throws RecognitionException {
		try {
			int _type = GT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:77:4: ( '>' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:77:6: '>'
			{
				match('>');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MERGE_ON"

	// $ANTLR start "HIERARCHY"
	public final void mHIERARCHY() throws RecognitionException {
		try {
			int _type = HIERARCHY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:78:11: ( 'hierarchy' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:78:13: 'hierarchy'
			{
				match("hierarchy");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MIN"

	// $ANTLR start "HMEETS"
	public final void mHMEETS() throws RecognitionException {
		try {
			int _type = HMEETS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:79:8: ( 'hmeets' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:79:10: 'hmeets'
			{
				match("hmeets");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MINUS"

	// $ANTLR start "IF"
	public final void mIF() throws RecognitionException {
		try {
			int _type = IF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:80:4: ( 'if' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:80:6: 'if'
			{
				match("if");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MINUS2FUN"

	// $ANTLR start "IMBALANCE"
	public final void mIMBALANCE() throws RecognitionException {
		try {
			int _type = IMBALANCE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:81:11: ( 'imbalance' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:81:13: 'imbalance'
			{
				match("imbalance");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MINUSFUN"

	// $ANTLR start "IN"
	public final void mIN() throws RecognitionException {
		try {
			int _type = IN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:82:4: ( 'in' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:82:6: 'in'
			{
				match("in");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MINUSMINUS"

	// $ANTLR start "INBALANCE"
	public final void mINBALANCE() throws RecognitionException {
		try {
			int _type = INBALANCE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:83:11: ( 'inbalance' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:83:13: 'inbalance'
			{
				match("inbalance");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MISSING"

	// $ANTLR start "INCLUDE"
	public final void mINCLUDE() throws RecognitionException {
		try {
			int _type = INCLUDE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:84:9: ( 'include' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:84:11: 'include'
			{
				match("include");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MOD"

	// $ANTLR start "INDEXOF"
	public final void mINDEXOF() throws RecognitionException {
		try {
			int _type = INDEXOF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:85:9: ( 'indexof' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:85:11: 'indexof'
			{
				match("indexof");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MULTFUN"

	// $ANTLR start "INTDAY"
	public final void mINTDAY() throws RecognitionException {
		try {
			int _type = INTDAY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:86:8: ( 'intday' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:86:10: 'intday'
			{
				match("intday");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MULTIPLY"

	// $ANTLR start "INTERSECT"
	public final void mINTERSECT() throws RecognitionException {
		try {
			int _type = INTERSECT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:87:11: ( 'intersect' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:87:13: 'intersect'
			{
				match("intersect");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MULTMULT"

	// $ANTLR start "INTMONTH"
	public final void mINTMONTH() throws RecognitionException {
		try {
			int _type = INTMONTH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:88:10: ( 'intmonth' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:88:12: 'intmonth'
			{
				match("intmonth");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NE"

	// $ANTLR start "INTYEAR"
	public final void mINTYEAR() throws RecognitionException {
		try {
			int _type = INTYEAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:89:9: ( 'intyear' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:89:11: 'intyear'
			{
				match("intyear");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NEX"

	// $ANTLR start "ISNULL"
	public final void mISNULL() throws RecognitionException {
		try {
			int _type = ISNULL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:90:8: ( 'isnull' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:90:10: 'isnull'
			{
				match("isnull");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NODUPLICATES"

	// $ANTLR start "KEEP"
	public final void mKEEP() throws RecognitionException {
		try {
			int _type = KEEP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:91:6: ( 'keep' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:91:8: 'keep'
			{
				match("keep");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOT"

	// $ANTLR start "KEY"
	public final void mKEY() throws RecognitionException {
		try {
			int _type = KEY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:92:5: ( 'key' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:92:7: 'key'
			{
				match("key");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOT_EXISTS_IN"

	// $ANTLR start "KEYS"
	public final void mKEYS() throws RecognitionException {
		try {
			int _type = KEYS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:93:6: ( 'keys' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:93:8: 'keys'
			{
				match("keys");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOT_EXISTS_IN_ALL"

	// $ANTLR start "LAST"
	public final void mLAST() throws RecognitionException {
		try {
			int _type = LAST;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:94:6: ( 'last' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:94:8: 'last'
			{
				match("last");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOT_IN"

	// $ANTLR start "LCASE"
	public final void mLCASE() throws RecognitionException {
		try {
			int _type = LCASE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:95:7: ( 'lower' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:95:9: 'lower'
			{
				match("lower");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NROOT"

	// $ANTLR start "LE"
	public final void mLE() throws RecognitionException {
		try {
			int _type = LE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:96:4: ( '<=' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:96:6: '<='
			{
				match("<=");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NVL"

	// $ANTLR start "LEFTC"
	public final void mLEFTC() throws RecognitionException {
		try {
			int _type = LEFTC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:97:7: ( 'leftc' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:97:9: 'leftc'
			{
				match("leftc");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ON"

	// $ANTLR start "LEN"
	public final void mLEN() throws RecognitionException {
		try {
			int _type = LEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:98:5: ( 'length' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:98:7: 'length'
			{
				match("length");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OR"

	// $ANTLR start "LN"
	public final void mLN() throws RecognitionException {
		try {
			int _type = LN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:99:4: ( 'ln' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:99:6: 'ln'
			{
				match("ln");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ORDER"

	// $ANTLR start "LOG"
	public final void mLOG() throws RecognitionException {
		try {
			int _type = LOG;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:100:5: ( 'log' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:100:7: 'log'
			{
				match("log");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OVERLAP"

	// $ANTLR start "LT"
	public final void mLT() throws RecognitionException {
		try {
			int _type = LT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:101:4: ( '<' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:101:6: '<'
			{
				match('<');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PCSFILTER"

	// $ANTLR start "MATCHES_INVALID"
	public final void mMATCHES_INVALID() throws RecognitionException {
		try {
			int _type = MATCHES_INVALID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:102:17: ( 'is_invalid' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:102:19: 'is_invalid'
			{
				match("is_invalid");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PERCENT"

	// $ANTLR start "MATCHES_VALID"
	public final void mMATCHES_VALID() throws RecognitionException {
		try {
			int _type = MATCHES_VALID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:103:15: ( 'is_valid' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:103:17: 'is_valid'
			{
				match("is_valid");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PERCENTILE"

	// $ANTLR start "MATCHKEY"
	public final void mMATCHKEY() throws RecognitionException {
		try {
			int _type = MATCHKEY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:104:10: ( 'matchkey' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:104:12: 'matchkey'
			{
				match("matchkey");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PLUS"

	// $ANTLR start "MAX"
	public final void mMAX() throws RecognitionException {
		try {
			int _type = MAX;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:105:5: ( 'max' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:105:7: 'max'
			{
				match("max");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PLUS2FUN"

	// $ANTLR start "MEASURE"
	public final void mMEASURE() throws RecognitionException {
		try {
			int _type = MEASURE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:106:9: ( 'MEASURE' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:106:11: 'MEASURE'
			{
				match("MEASURE");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PLUSFUN"

	// $ANTLR start "MEDIAN"
	public final void mMEDIAN() throws RecognitionException {
		try {
			int _type = MEDIAN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:107:8: ( 'median' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:107:10: 'median'
			{
				match("median");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PLUSPLUS"

	// $ANTLR start "MERGE"
	public final void mMERGE() throws RecognitionException {
		try {
			int _type = MERGE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:108:7: ( 'merge' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:108:9: 'merge'
			{
				match("merge");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "POWER"

	// $ANTLR start "MERGE_ON"
	public final void mMERGE_ON() throws RecognitionException {
		try {
			int _type = MERGE_ON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:109:10: ( 'merge on' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:109:12: 'merge on'
			{
				match("merge on");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PUT"

	// $ANTLR start "MIN"
	public final void mMIN() throws RecognitionException {
		try {
			int _type = MIN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:110:5: ( 'min' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:110:7: 'min'
			{
				match("min");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RANK"

	// $ANTLR start "MINUS"
	public final void mMINUS() throws RecognitionException {
		try {
			int _type = MINUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:111:7: ( '-' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:111:9: '-'
			{
				match('-');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RELPERC"

	// $ANTLR start "MINUS2FUN"
	public final void mMINUS2FUN() throws RecognitionException {
		try {
			int _type = MINUS2FUN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:112:11: ( 'minus2' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:112:13: 'minus2'
			{
				match("minus2");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RENAME"

	// $ANTLR start "MINUSFUN"
	public final void mMINUSFUN() throws RecognitionException {
		try {
			int _type = MINUSFUN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:113:10: ( 'minus' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:113:12: 'minus'
			{
				match("minus");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RETURN"

	// $ANTLR start "MINUSMINUS"
	public final void mMINUSMINUS() throws RecognitionException {
		try {
			int _type = MINUSMINUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:114:12: ( '--' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:114:14: '--'
			{
				match("--");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ROLE"

	// $ANTLR start "MISSING"
	public final void mMISSING() throws RecognitionException {
		try {
			int _type = MISSING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:115:9: ( 'missing' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:115:11: 'missing'
			{
				match("missing");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ROUND"

	// $ANTLR start "MOD"
	public final void mMOD() throws RecognitionException {
		try {
			int _type = MOD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:116:5: ( 'mod' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:116:7: 'mod'
			{
				match("mod");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEVERITY"

	// $ANTLR start "MULTFUN"
	public final void mMULTFUN() throws RecognitionException {
		try {
			int _type = MULTFUN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:117:9: ( 'mult' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:117:11: 'mult'
			{
				match("mult");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STD"

	// $ANTLR start "MULTIPLY"
	public final void mMULTIPLY() throws RecognitionException {
		try {
			int _type = MULTIPLY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:118:10: ( '*' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:118:12: '*'
			{
				match('*');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SUBSTR"

	// $ANTLR start "MULTMULT"
	public final void mMULTMULT() throws RecognitionException {
		try {
			int _type = MULTMULT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:119:10: ( '**' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:119:12: '**'
			{
				match("**");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SUM"

	// $ANTLR start "NE"
	public final void mNE() throws RecognitionException {
		try {
			int _type = NE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:120:4: ( '<>' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:120:6: '<>'
			{
				match("<>");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TAVG"

	// $ANTLR start "NEX"
	public final void mNEX() throws RecognitionException {
		try {
			int _type = NEX;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:121:5: ( 'nex' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:121:7: 'nex'
			{
				match("nex");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TCOUNT"

	// $ANTLR start "NODUPLICATES"
	public final void mNODUPLICATES() throws RecognitionException {
		try {
			int _type = NODUPLICATES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:122:14: ( 'no_duplicates' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:122:16: 'no_duplicates'
			{
				match("no_duplicates");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "THEN"

	// $ANTLR start "NOT"
	public final void mNOT() throws RecognitionException {
		try {
			int _type = NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:123:5: ( 'not' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:123:7: 'not'
			{
				match("not");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "THRESHOLD"

	// $ANTLR start "NOT_EXISTS_IN"
	public final void mNOT_EXISTS_IN() throws RecognitionException {
		try {
			int _type = NOT_EXISTS_IN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:124:15: ( 'not_exists_in' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:124:17: 'not_exists_in'
			{
				match("not_exists_in");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TIMEFILTER"

	// $ANTLR start "NOT_EXISTS_IN_ALL"
	public final void mNOT_EXISTS_IN_ALL() throws RecognitionException {
		try {
			int _type = NOT_EXISTS_IN_ALL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:125:19: ( 'not_exists_in_all' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:125:21: 'not_exists_in_all'
			{
				match("not_exists_in_all");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TIME_BEHAVIOR"

	// $ANTLR start "NOT_IN"
	public final void mNOT_IN() throws RecognitionException {
		try {
			int _type = NOT_IN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:126:8: ( 'setdiff' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:126:10: 'setdiff'
			{
				match("setdiff");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TMAX"

	// $ANTLR start "NROOT"
	public final void mNROOT() throws RecognitionException {
		try {
			int _type = NROOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:127:7: ( 'nroot' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:127:9: 'nroot'
			{
				match("nroot");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TMEDIAN"

	// $ANTLR start "NVL"
	public final void mNVL() throws RecognitionException {
		try {
			int _type = NVL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:128:5: ( 'nvl' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:128:7: 'nvl'
			{
				match("nvl");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TMIN"

	// $ANTLR start "ON"
	public final void mON() throws RecognitionException {
		try {
			int _type = ON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:129:4: ( 'on' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:129:6: 'on'
			{
				match("on");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TO"

	// $ANTLR start "OR"
	public final void mOR() throws RecognitionException {
		try {
			int _type = OR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:130:4: ( 'or' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:130:6: 'or'
			{
				match("or");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TO_CURR"

	// $ANTLR start "ORDER"
	public final void mORDER() throws RecognitionException {
		try {
			int _type = ORDER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:131:7: ( 'order' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:131:9: 'order'
			{
				match("order");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRIM"

	// $ANTLR start "OVERLAP"
	public final void mOVERLAP() throws RecognitionException {
		try {
			int _type = OVERLAP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:132:9: ( 'overlap' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:132:11: 'overlap'
			{
				match("overlap");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRUNC"

	// $ANTLR start "PCSFILTER"
	public final void mPCSFILTER() throws RecognitionException {
		try {
			int _type = PCSFILTER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:133:11: ( 'pcsfilter' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:133:13: 'pcsfilter'
			{
				match("pcsfilter");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TSTD"

	// $ANTLR start "PERCENT"
	public final void mPERCENT() throws RecognitionException {
		try {
			int _type = PERCENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:134:9: ( '%' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:134:11: '%'
			{
				match('%');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TSUM"

	// $ANTLR start "PERCENTILE"
	public final void mPERCENTILE() throws RecognitionException {
		try {
			int _type = PERCENTILE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:135:12: ( 'percentile' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:135:14: 'percentile'
			{
				match("percentile");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TYPE"

	// $ANTLR start "PLUS"
	public final void mPLUS() throws RecognitionException {
		try {
			int _type = PLUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:136:6: ( '+' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:136:8: '+'
			{
				match('+');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UCASE"

	// $ANTLR start "PLUS2FUN"
	public final void mPLUS2FUN() throws RecognitionException {
		try {
			int _type = PLUS2FUN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:137:10: ( 'plus2' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:137:12: 'plus2'
			{
				match("plus2");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UMEETS"

	// $ANTLR start "PLUSFUN"
	public final void mPLUSFUN() throws RecognitionException {
		try {
			int _type = PLUSFUN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:138:9: ( 'plus' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:138:11: 'plus'
			{
				match("plus");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UNION"

	// $ANTLR start "PLUSPLUS"
	public final void mPLUSPLUS() throws RecognitionException {
		try {
			int _type = PLUSPLUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:139:10: ( '++' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:139:12: '++'
			{
				match("++");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "USING"

	// $ANTLR start "POWER"
	public final void mPOWER() throws RecognitionException {
		try {
			int _type = POWER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:140:7: ( 'power' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:140:9: 'power'
			{
				match("power");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VIRAL"

	// $ANTLR start "PUT"
	public final void mPUT() throws RecognitionException {
		try {
			int _type = PUT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:141:5: ( 'put' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:141:7: 'put'
			{
				match("put");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WITH"

	// $ANTLR start "RANK"
	public final void mRANK() throws RecognitionException {
		try {
			int _type = RANK;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:142:6: ( 'rank' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:142:8: 'rank'
			{
				match("rank");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "XOR"

	// $ANTLR start "RELPERC"
	public final void mRELPERC() throws RecognitionException {
		try {
			int _type = RELPERC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:143:9: ( 'relperc' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:143:11: 'relperc'
			{
				match("relperc");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__425"

	// $ANTLR start "RENAME"
	public final void mRENAME() throws RecognitionException {
		try {
			int _type = RENAME;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:144:8: ( 'rename' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:144:10: 'rename'
			{
				match("rename");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__426"

	// $ANTLR start "RETURN"
	public final void mRETURN() throws RecognitionException {
		try {
			int _type = RETURN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:145:8: ( 'return' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:145:10: 'return'
			{
				match("return");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__427"

	// $ANTLR start "ROLE"
	public final void mROLE() throws RecognitionException {
		try {
			int _type = ROLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:146:6: ( 'role' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:146:8: 'role'
			{
				match("role");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__428"

	// $ANTLR start "ROUND"
	public final void mROUND() throws RecognitionException {
		try {
			int _type = ROUND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:147:7: ( 'round' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:147:9: 'round'
			{
				match("round");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__429"

	// $ANTLR start "SEVERITY"
	public final void mSEVERITY() throws RecognitionException {
		try {
			int _type = SEVERITY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:148:10: ( 'severity' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:148:12: 'severity'
			{
				match("severity");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__430"

	// $ANTLR start "STD"
	public final void mSTD() throws RecognitionException {
		try {
			int _type = STD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:149:5: ( 'std' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:149:7: 'std'
			{
				match("std");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__431"

	// $ANTLR start "SUBSTR"
	public final void mSUBSTR() throws RecognitionException {
		try {
			int _type = SUBSTR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:150:8: ( 'substr' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:150:10: 'substr'
			{
				match("substr");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__432"

	// $ANTLR start "SUM"
	public final void mSUM() throws RecognitionException {
		try {
			int _type = SUM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:151:5: ( 'sum' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:151:7: 'sum'
			{
				match("sum");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__433"

	// $ANTLR start "TAVG"
	public final void mTAVG() throws RecognitionException {
		try {
			int _type = TAVG;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:152:6: ( 'tavg' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:152:8: 'tavg'
			{
				match("tavg");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__434"

	// $ANTLR start "TCOUNT"
	public final void mTCOUNT() throws RecognitionException {
		try {
			int _type = TCOUNT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:153:8: ( 'tcount' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:153:10: 'tcount'
			{
				match("tcount");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTEGER_CONSTANT"

	// $ANTLR start "THEN"
	public final void mTHEN() throws RecognitionException {
		try {
			int _type = THEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:154:6: ( 'then' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:154:8: 'then'
			{
				match("then");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FLOAT_CONSTANT"

	// $ANTLR start "THRESHOLD"
	public final void mTHRESHOLD() throws RecognitionException {
		try {
			int _type = THRESHOLD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:155:11: ( 'threshold' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:155:13: 'threshold'
			{
				match("threshold");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FLOATEXP"

	// $ANTLR start "TIMEFILTER"
	public final void mTIMEFILTER() throws RecognitionException {
		try {
			int _type = TIMEFILTER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:156:12: ( 'timefilter' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:156:14: 'timefilter'
			{
				match("timefilter");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TIME_CLAUSE"

	// $ANTLR start "TIME_BEHAVIOR"
	public final void mTIME_BEHAVIOR() throws RecognitionException {
		try {
			int _type = TIME_BEHAVIOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:157:15: ( 'time_behavior' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:157:17: 'time_behavior'
			{
				match("time_behavior");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BOOLEAN_CONSTANT"

	// $ANTLR start "TMAX"
	public final void mTMAX() throws RecognitionException {
		try {
			int _type = TMAX;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:158:6: ( 'tmax' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:158:8: 'tmax'
			{
				match("tmax");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NULL_CONSTANT"

	// $ANTLR start "TMEDIAN"
	public final void mTMEDIAN() throws RecognitionException {
		try {
			int _type = TMEDIAN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:159:9: ( 'tmedian' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:159:11: 'tmedian'
			{
				match("tmedian");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING_CONSTANT"

	// $ANTLR start "TMIN"
	public final void mTMIN() throws RecognitionException {
		try {
			int _type = TMIN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:160:6: ( 'tmin' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:160:8: 'tmin'
			{
				match("tmin");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IDENTIFIER"

	// $ANTLR start "TO"
	public final void mTO() throws RecognitionException {
		try {
			int _type = TO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:161:4: ( 'to' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:161:6: 'to'
			{
				match("to");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LETTER"

	// $ANTLR start "TO_CURR"
	public final void mTO_CURR() throws RecognitionException {
		try {
			int _type = TO_CURR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:162:9: ( 'tocurr' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:162:11: 'tocurr'
			{
				match("tocurr");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "TRIM"
	public final void mTRIM() throws RecognitionException {
		try {
			int _type = TRIM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:163:6: ( 'trim' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:163:8: 'trim'
			{
				match("trim");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EOL"

	// $ANTLR start "TRUNC"
	public final void mTRUNC() throws RecognitionException {
		try {
			int _type = TRUNC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:164:7: ( 'trunc' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:164:9: 'trunc'
			{
				match("trunc");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ML_COMMENT"

	// $ANTLR start "TSTD"
	public final void mTSTD() throws RecognitionException {
		try {
			int _type = TSTD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:165:6: ( 'tstd' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:165:8: 'tstd'
			{
				match("tstd");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "TSUM"
	public final void mTSUM() throws RecognitionException {
		try {
			int _type = TSUM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:166:6: ( 'tsum' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:166:8: 'tsum'
			{
				match("tsum");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "TYPE"
	public final void mTYPE() throws RecognitionException {
		try {
			int _type = TYPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:167:6: ( 'type' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:167:8: 'type'
			{
				match("type");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "UCASE"
	public final void mUCASE() throws RecognitionException {
		try {
			int _type = UCASE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:168:7: ( 'upper' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:168:9: 'upper'
			{
				match("upper");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "UMEETS"
	public final void mUMEETS() throws RecognitionException {
		try {
			int _type = UMEETS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:169:8: ( 'umeets' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:169:10: 'umeets'
			{
				match("umeets");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "UNION"
	public final void mUNION() throws RecognitionException {
		try {
			int _type = UNION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:170:7: ( 'union' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:170:9: 'union'
			{
				match("union");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "USING"
	public final void mUSING() throws RecognitionException {
		try {
			int _type = USING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:171:7: ( 'using' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:171:9: 'using'
			{
				match("using");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "VIRAL"
	public final void mVIRAL() throws RecognitionException {
		try {
			int _type = VIRAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:172:7: ( 'viral' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:172:9: 'viral'
			{
				match("viral");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "WITH"
	public final void mWITH() throws RecognitionException {
		try {
			int _type = WITH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:173:6: ( 'with' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:173:8: 'with'
			{
				match("with");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "XOR"
	public final void mXOR() throws RecognitionException {
		try {
			int _type = XOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:174:5: ( 'xor' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:174:7: 'xor'
			{
				match("xor");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "T__425"
	public final void mT__425() throws RecognitionException {
		try {
			int _type = T__425;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:175:8: ( '#' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:175:10: '#'
			{
				match('#');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "T__426"
	public final void mT__426() throws RecognitionException {
		try {
			int _type = T__426;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:176:8: ( '(' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:176:10: '('
			{
				match('(');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "T__427"
	public final void mT__427() throws RecognitionException {
		try {
			int _type = T__427;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:177:8: ( ')' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:177:10: ')'
			{
				match(')');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "T__428"
	public final void mT__428() throws RecognitionException {
		try {
			int _type = T__428;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:178:8: ( 'INF' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:178:10: 'INF'
			{
				match("INF");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "T__429"
	public final void mT__429() throws RecognitionException {
		try {
			int _type = T__429;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:179:8: ( 'N' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:179:10: 'N'
			{
				match('N');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "T__430"
	public final void mT__430() throws RecognitionException {
		try {
			int _type = T__430;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:180:8: ( 'SYSTIMESTAMP' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:180:10: 'SYSTIMESTAMP'
			{
				match("SYSTIMESTAMP");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "T__431"
	public final void mT__431() throws RecognitionException {
		try {
			int _type = T__431;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:181:8: ( 'Y' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:181:10: 'Y'
			{
				match('Y');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "T__432"
	public final void mT__432() throws RecognitionException {
		try {
			int _type = T__432;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:182:8: ( '[' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:182:10: '['
			{
				match('[');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "T__433"
	public final void mT__433() throws RecognitionException {
		try {
			int _type = T__433;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:183:8: ( ']' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:183:10: ']'
			{
				match(']');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "T__434"
	public final void mT__434() throws RecognitionException {
		try {
			int _type = T__434;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:184:8: ( 'prod' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:184:10: 'prod'
			{
				match("prod");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "INTEGER_CONSTANT"
	public final void mINTEGER_CONSTANT() throws RecognitionException {
		try {
			int _type = INTEGER_CONSTANT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1528:3: ( ( '0' .. '9' )+ )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1529:3: ( '0' .. '9' )+
			{
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1529:3: ( '0' .. '9' )+
				int cnt1 = 0;
				loop1:
				while (true) {
					int alt1 = 2;
					int LA1_0 = input.LA(1);
					if (((LA1_0 >= '0' && LA1_0 <= '9'))) {
						alt1 = 1;
					}

					switch (alt1) {
						case 1:
							// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:
						{
							if ((input.LA(1) >= '0' && input.LA(1) <= '9')) {
								input.consume();
							} else {
								MismatchedSetException mse = new MismatchedSetException(null, input);
								recover(mse);
								throw mse;
							}
						}
						break;

						default:
							if (cnt1 >= 1) break loop1;
							EarlyExitException eee = new EarlyExitException(1, input);
							throw eee;
					}
					cnt1++;
				}

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "FLOAT_CONSTANT"
	public final void mFLOAT_CONSTANT() throws RecognitionException {
		try {
			int _type = FLOAT_CONSTANT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1533:3: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( FLOATEXP )? | ( '0' .. '9' )+ FLOATEXP )
			int alt6 = 2;
			alt6 = dfa6.predict(input);
			switch (alt6) {
				case 1:
					// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1534:3: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( FLOATEXP )?
				{
					// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1534:3: ( '0' .. '9' )+
					int cnt2 = 0;
					loop2:
					while (true) {
						int alt2 = 2;
						int LA2_0 = input.LA(1);
						if (((LA2_0 >= '0' && LA2_0 <= '9'))) {
							alt2 = 1;
						}

						switch (alt2) {
							case 1:
								// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:
							{
								if ((input.LA(1) >= '0' && input.LA(1) <= '9')) {
									input.consume();
								} else {
									MismatchedSetException mse = new MismatchedSetException(null, input);
									recover(mse);
									throw mse;
								}
							}
							break;

							default:
								if (cnt2 >= 1) break loop2;
								EarlyExitException eee = new EarlyExitException(2, input);
								throw eee;
						}
						cnt2++;
					}

					match('.');
					// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1534:19: ( '0' .. '9' )*
					loop3:
					while (true) {
						int alt3 = 2;
						int LA3_0 = input.LA(1);
						if (((LA3_0 >= '0' && LA3_0 <= '9'))) {
							alt3 = 1;
						}

						switch (alt3) {
							case 1:
								// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:
							{
								if ((input.LA(1) >= '0' && input.LA(1) <= '9')) {
									input.consume();
								} else {
									MismatchedSetException mse = new MismatchedSetException(null, input);
									recover(mse);
									throw mse;
								}
							}
							break;

							default:
								break loop3;
						}
					}

					// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1534:31: ( FLOATEXP )?
					int alt4 = 2;
					int LA4_0 = input.LA(1);
					if ((LA4_0 == 'E' || LA4_0 == 'e')) {
						alt4 = 1;
					}
					switch (alt4) {
						case 1:
							// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1534:31: FLOATEXP
						{
							mFLOATEXP();

						}
						break;

					}

				}
				break;
				case 2:
					// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1535:5: ( '0' .. '9' )+ FLOATEXP
				{
					// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1535:5: ( '0' .. '9' )+
					int cnt5 = 0;
					loop5:
					while (true) {
						int alt5 = 2;
						int LA5_0 = input.LA(1);
						if (((LA5_0 >= '0' && LA5_0 <= '9'))) {
							alt5 = 1;
						}

						switch (alt5) {
							case 1:
								// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:
							{
								if ((input.LA(1) >= '0' && input.LA(1) <= '9')) {
									input.consume();
								} else {
									MismatchedSetException mse = new MismatchedSetException(null, input);
									recover(mse);
									throw mse;
								}
							}
							break;

							default:
								if (cnt5 >= 1) break loop5;
								EarlyExitException eee = new EarlyExitException(5, input);
								throw eee;
						}
						cnt5++;
					}

					mFLOATEXP();

				}
				break;

			}
			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "FLOATEXP"
	public final void mFLOATEXP() throws RecognitionException {
		try {
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1541:3: ( ( 'e' | 'E' ) ( PLUS | MINUS )? ( '0' .. '9' )+ )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1542:3: ( 'e' | 'E' ) ( PLUS | MINUS )? ( '0' .. '9' )+
			{
				if (input.LA(1) == 'E' || input.LA(1) == 'e') {
					input.consume();
				} else {
					MismatchedSetException mse = new MismatchedSetException(null, input);
					recover(mse);
					throw mse;
				}
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1546:3: ( PLUS | MINUS )?
				int alt7 = 2;
				int LA7_0 = input.LA(1);
				if ((LA7_0 == '+' || LA7_0 == '-')) {
					alt7 = 1;
				}
				switch (alt7) {
					case 1:
						// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:
					{
						if (input.LA(1) == '+' || input.LA(1) == '-') {
							input.consume();
						} else {
							MismatchedSetException mse = new MismatchedSetException(null, input);
							recover(mse);
							throw mse;
						}
					}
					break;

				}

				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1550:3: ( '0' .. '9' )+
				int cnt8 = 0;
				loop8:
				while (true) {
					int alt8 = 2;
					int LA8_0 = input.LA(1);
					if (((LA8_0 >= '0' && LA8_0 <= '9'))) {
						alt8 = 1;
					}

					switch (alt8) {
						case 1:
							// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:
						{
							if ((input.LA(1) >= '0' && input.LA(1) <= '9')) {
								input.consume();
							} else {
								MismatchedSetException mse = new MismatchedSetException(null, input);
								recover(mse);
								throw mse;
							}
						}
						break;

						default:
							if (cnt8 >= 1) break loop8;
							EarlyExitException eee = new EarlyExitException(8, input);
							throw eee;
					}
					cnt8++;
				}

			}

		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "TIME_CLAUSE"
	public final void mTIME_CLAUSE() throws RecognitionException {
		try {
			int _type = TIME_CLAUSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1553:3: ( ( 'T' | 't' ) )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:
			{
				if (input.LA(1) == 'T' || input.LA(1) == 't') {
					input.consume();
				} else {
					MismatchedSetException mse = new MismatchedSetException(null, input);
					recover(mse);
					throw mse;
				}
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "BOOLEAN_CONSTANT"
	public final void mBOOLEAN_CONSTANT() throws RecognitionException {
		try {
			int _type = BOOLEAN_CONSTANT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1561:3: ( 'true' | 'false' )
			int alt9 = 2;
			int LA9_0 = input.LA(1);
			if ((LA9_0 == 't')) {
				alt9 = 1;
			} else if ((LA9_0 == 'f')) {
				alt9 = 2;
			} else {
				NoViableAltException nvae =
						new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1:
					// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1562:3: 'true'
				{
					match("true");

				}
				break;
				case 2:
					// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1563:5: 'false'
				{
					match("false");

				}
				break;

			}
			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "NULL_CONSTANT"
	public final void mNULL_CONSTANT() throws RecognitionException {
		try {
			int _type = NULL_CONSTANT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1567:3: ( 'null' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1568:3: 'null'
			{
				match("null");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "STRING_CONSTANT"
	public final void mSTRING_CONSTANT() throws RecognitionException {
		try {
			int _type = STRING_CONSTANT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1572:3: ( '\"' (~ '\"' )* '\"' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1573:3: '\"' (~ '\"' )* '\"'
			{
				match('\"');
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1573:7: (~ '\"' )*
				loop10:
				while (true) {
					int alt10 = 2;
					int LA10_0 = input.LA(1);
					if (((LA10_0 >= '\u0000' && LA10_0 <= '!') || (LA10_0 >= '#' && LA10_0 <= '\uFFFF'))) {
						alt10 = 1;
					}

					switch (alt10) {
						case 1:
							// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:
						{
							if ((input.LA(1) >= '\u0000' && input.LA(1) <= '!') || (input.LA(1) >= '#' && input.LA(1) <= '\uFFFF')) {
								input.consume();
							} else {
								MismatchedSetException mse = new MismatchedSetException(null, input);
								recover(mse);
								throw mse;
							}
						}
						break;

						default:
							break loop10;
					}
				}

				match('\"');
			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "IDENTIFIER"
	public final void mIDENTIFIER() throws RecognitionException {
		try {
			int _type = IDENTIFIER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1577:3: ( LETTER ( LETTER | '_' | '0' .. '9' )* )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1578:3: LETTER ( LETTER | '_' | '0' .. '9' )*
			{
				mLETTER();

				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1579:3: ( LETTER | '_' | '0' .. '9' )*
				loop11:
				while (true) {
					int alt11 = 2;
					int LA11_0 = input.LA(1);
					if (((LA11_0 >= '0' && LA11_0 <= '9') || (LA11_0 >= 'A' && LA11_0 <= 'Z') || LA11_0 == '_' || (LA11_0 >= 'a' && LA11_0 <= 'z'))) {
						alt11 = 1;
					}

					switch (alt11) {
						case 1:
							// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:
						{
							if ((input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
								input.consume();
							} else {
								MismatchedSetException mse = new MismatchedSetException(null, input);
								recover(mse);
								throw mse;
							}
						}
						break;

						default:
							break loop11;
					}
				}

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "LETTER"
	public final void mLETTER() throws RecognitionException {
		try {
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1595:3: ( 'A' .. 'Z' | 'a' .. 'z' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:
			{
				if ((input.LA(1) >= 'A' && input.LA(1) <= 'Z') || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
					input.consume();
				} else {
					MismatchedSetException mse = new MismatchedSetException(null, input);
					recover(mse);
					throw mse;
				}
			}

		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1600:3: ( ( ' ' | '\\r' | '\\t' | '\\u000C' ) )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1601:3: ( ' ' | '\\r' | '\\t' | '\\u000C' )
			{
				if (input.LA(1) == '\t' || (input.LA(1) >= '\f' && input.LA(1) <= '\r') || input.LA(1) == ' ') {
					input.consume();
				} else {
					MismatchedSetException mse = new MismatchedSetException(null, input);
					recover(mse);
					throw mse;
				}

				_channel = HIDDEN;

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "EOL"
	public final void mEOL() throws RecognitionException {
		try {
			int _type = EOL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1636:3: ( ( '\\r' | '\\n' ) )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1637:3: ( '\\r' | '\\n' )
			{
				if (input.LA(1) == '\n' || input.LA(1) == '\r') {
					input.consume();
				} else {
					MismatchedSetException mse = new MismatchedSetException(null, input);
					recover(mse);
					throw mse;
				}

				_channel = HIDDEN;

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	// $ANTLR start "ML_COMMENT"
	public final void mML_COMMENT() throws RecognitionException {
		try {
			int _type = ML_COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1648:3: ( '/*' ( . )* '*/' )
			// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1649:3: '/*' ( . )* '*/'
			{
				match("/*");

				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1649:8: ( . )*
				loop12:
				while (true) {
					int alt12 = 2;
					int LA12_0 = input.LA(1);
					if ((LA12_0 == '*')) {
						int LA12_1 = input.LA(2);
						if ((LA12_1 == '/')) {
							alt12 = 2;
						} else if (((LA12_1 >= '\u0000' && LA12_1 <= '.') || (LA12_1 >= '0' && LA12_1 <= '\uFFFF'))) {
							alt12 = 1;
						}

					} else if (((LA12_0 >= '\u0000' && LA12_0 <= ')') || (LA12_0 >= '+' && LA12_0 <= '\uFFFF'))) {
						alt12 = 1;
					}

					switch (alt12) {
						case 1:
							// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1649:9: .
						{
							matchAny();
						}
						break;

						default:
							break loop12;
					}
				}

				match("*/");


				_channel = HIDDEN;

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
			// do for sure before leaving
		}
	}

	@Override
	public void mTokens() throws RecognitionException {
		// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:8: ( ABS | AGGREGATE | ALL | AND | AS | ASC | ASSIGN | ATTRCALC | ATTRIBUTE | AVG | AVGPERIOD | BETWEEN | BREAK | BREAKDATE | BY | CALC | CARTESIAN_PER | CASE | CHARLENGTH | CHARSET_MATCH | CHECK | CODELIST_MATCH | COMPLCHECK | CONCAT | COND | COUNT | COUNT_DISTINCT | CURRCHANGE | CURRENT_DATE | DATASET_LEVEL | DESC | DIFF | DIFFPERC | DIMENSION | DISCREPANCY | DIVDIV | DIVFUN | DIVIDE | DROP | ELSE | ELSEIF | ENDPERIOD | EQ | ERLEVEL | ERRORCODE | EVAL | EX | EXCHECK | EXCLUDE | EXISTS_IN | EXISTS_IN_ALL | EXKEY | EXP | FILTER | FIRST | FROM_CURR | GE | GET | GT | HIERARCHY | HMEETS | IF | IMBALANCE | IN | INBALANCE | INCLUDE | INDEXOF | INTDAY | INTERSECT | INTMONTH | INTYEAR | ISNULL | KEEP | KEY | KEYS | LAST | LCASE | LE | LEFTC | LEN | LN | LOG | LT | MATCHES_INVALID | MATCHES_VALID | MATCHKEY | MAX | MEASURE | MEDIAN | MERGE | MERGE_ON | MIN | MINUS | MINUS2FUN | MINUSFUN | MINUSMINUS | MISSING | MOD | MULTFUN | MULTIPLY | MULTMULT | NE | NEX | NODUPLICATES | NOT | NOT_EXISTS_IN | NOT_EXISTS_IN_ALL | NOT_IN | NROOT | NVL | ON | OR | ORDER | OVERLAP | PCSFILTER | PERCENT | PERCENTILE | PLUS | PLUS2FUN | PLUSFUN | PLUSPLUS | POWER | PUT | RANK | RELPERC | RENAME | RETURN | ROLE | ROUND | SEVERITY | STD | SUBSTR | SUM | TAVG | TCOUNT | THEN | THRESHOLD | TIMEFILTER | TIME_BEHAVIOR | TMAX | TMEDIAN | TMIN | TO | TO_CURR | TRIM | TRUNC | TSTD | TSUM | TYPE | UCASE | UMEETS | UNION | USING | VIRAL | WITH | XOR | T__425 | T__426 | T__427 | T__428 | T__429 | T__430 | T__431 | T__432 | T__433 | T__434 | INTEGER_CONSTANT | FLOAT_CONSTANT | TIME_CLAUSE | BOOLEAN_CONSTANT | NULL_CONSTANT | STRING_CONSTANT | IDENTIFIER | WS | EOL | ML_COMMENT )
		int alt13 = 176;
		alt13 = dfa13.predict(input);
		switch (alt13) {
			case 1:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:10: ABS
			{
				mABS();

			}
			break;
			case 2:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:14: AGGREGATE
			{
				mAGGREGATE();

			}
			break;
			case 3:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:24: ALL
			{
				mALL();

			}
			break;
			case 4:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:28: AND
			{
				mAND();

			}
			break;
			case 5:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:32: AS
			{
				mAS();

			}
			break;
			case 6:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:35: ASC
			{
				mASC();

			}
			break;
			case 7:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:39: ASSIGN
			{
				mASSIGN();

			}
			break;
			case 8:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:46: ATTRCALC
			{
				mATTRCALC();

			}
			break;
			case 9:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:55: ATTRIBUTE
			{
				mATTRIBUTE();

			}
			break;
			case 10:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:65: AVG
			{
				mAVG();

			}
			break;
			case 11:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:69: AVGPERIOD
			{
				mAVGPERIOD();

			}
			break;
			case 12:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:79: BETWEEN
			{
				mBETWEEN();

			}
			break;
			case 13:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:87: BREAK
			{
				mBREAK();

			}
			break;
			case 14:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:93: BREAKDATE
			{
				mBREAKDATE();

			}
			break;
			case 15:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:103: BY
			{
				mBY();

			}
			break;
			case 16:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:106: CALC
			{
				mCALC();

			}
			break;
			case 17:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:111: CARTESIAN_PER
			{
				mCARTESIAN_PER();

			}
			break;
			case 18:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:125: CASE
			{
				mCASE();

			}
			break;
			case 19:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:130: CHARLENGTH
			{
				mCHARLENGTH();

			}
			break;
			case 20:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:141: CHARSET_MATCH
			{
				mCHARSET_MATCH();

			}
			break;
			case 21:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:155: CHECK
			{
				mCHECK();

			}
			break;
			case 22:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:161: CODELIST_MATCH
			{
				mCODELIST_MATCH();

			}
			break;
			case 23:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:176: COMPLCHECK
			{
				mCOMPLCHECK();

			}
			break;
			case 24:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:187: CONCAT
			{
				mCONCAT();

			}
			break;
			case 25:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:194: COND
			{
				mCOND();

			}
			break;
			case 26:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:199: COUNT
			{
				mCOUNT();

			}
			break;
			case 27:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:205: COUNT_DISTINCT
			{
				mCOUNT_DISTINCT();

			}
			break;
			case 28:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:220: CURRCHANGE
			{
				mCURRCHANGE();

			}
			break;
			case 29:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:231: CURRENT_DATE
			{
				mCURRENT_DATE();

			}
			break;
			case 30:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:244: DATASET_LEVEL
			{
				mDATASET_LEVEL();

			}
			break;
			case 31:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:258: DESC
			{
				mDESC();

			}
			break;
			case 32:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:263: DIFF
			{
				mDIFF();

			}
			break;
			case 33:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:268: DIFFPERC
			{
				mDIFFPERC();

			}
			break;
			case 34:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:277: DIMENSION
			{
				mDIMENSION();

			}
			break;
			case 35:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:287: DISCREPANCY
			{
				mDISCREPANCY();

			}
			break;
			case 36:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:299: DIVDIV
			{
				mDIVDIV();

			}
			break;
			case 37:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:306: DIVFUN
			{
				mDIVFUN();

			}
			break;
			case 38:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:313: DIVIDE
			{
				mDIVIDE();

			}
			break;
			case 39:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:320: DROP
			{
				mDROP();

			}
			break;
			case 40:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:325: ELSE
			{
				mELSE();

			}
			break;
			case 41:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:330: ELSEIF
			{
				mELSEIF();

			}
			break;
			case 42:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:337: ENDPERIOD
			{
				mENDPERIOD();

			}
			break;
			case 43:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:347: EQ
			{
				mEQ();

			}
			break;
			case 44:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:350: ERLEVEL
			{
				mERLEVEL();

			}
			break;
			case 45:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:358: ERRORCODE
			{
				mERRORCODE();

			}
			break;
			case 46:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:368: EVAL
			{
				mEVAL();

			}
			break;
			case 47:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:373: EX
			{
				mEX();

			}
			break;
			case 48:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:376: EXCHECK
			{
				mEXCHECK();

			}
			break;
			case 49:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:384: EXCLUDE
			{
				mEXCLUDE();

			}
			break;
			case 50:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:392: EXISTS_IN
			{
				mEXISTS_IN();

			}
			break;
			case 51:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:402: EXISTS_IN_ALL
			{
				mEXISTS_IN_ALL();

			}
			break;
			case 52:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:416: EXKEY
			{
				mEXKEY();

			}
			break;
			case 53:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:422: EXP
			{
				mEXP();

			}
			break;
			case 54:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:426: FILTER
			{
				mFILTER();

			}
			break;
			case 55:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:433: FIRST
			{
				mFIRST();

			}
			break;
			case 56:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:439: FROM_CURR
			{
				mFROM_CURR();

			}
			break;
			case 57:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:449: GE
			{
				mGE();

			}
			break;
			case 58:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:452: GET
			{
				mGET();

			}
			break;
			case 59:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:456: GT
			{
				mGT();

			}
			break;
			case 60:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:459: HIERARCHY
			{
				mHIERARCHY();

			}
			break;
			case 61:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:469: HMEETS
			{
				mHMEETS();

			}
			break;
			case 62:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:476: IF
			{
				mIF();

			}
			break;
			case 63:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:479: IMBALANCE
			{
				mIMBALANCE();

			}
			break;
			case 64:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:489: IN
			{
				mIN();

			}
			break;
			case 65:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:492: INBALANCE
			{
				mINBALANCE();

			}
			break;
			case 66:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:502: INCLUDE
			{
				mINCLUDE();

			}
			break;
			case 67:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:510: INDEXOF
			{
				mINDEXOF();

			}
			break;
			case 68:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:518: INTDAY
			{
				mINTDAY();

			}
			break;
			case 69:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:525: INTERSECT
			{
				mINTERSECT();

			}
			break;
			case 70:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:535: INTMONTH
			{
				mINTMONTH();

			}
			break;
			case 71:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:544: INTYEAR
			{
				mINTYEAR();

			}
			break;
			case 72:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:552: ISNULL
			{
				mISNULL();

			}
			break;
			case 73:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:559: KEEP
			{
				mKEEP();

			}
			break;
			case 74:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:564: KEY
			{
				mKEY();

			}
			break;
			case 75:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:568: KEYS
			{
				mKEYS();

			}
			break;
			case 76:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:573: LAST
			{
				mLAST();

			}
			break;
			case 77:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:578: LCASE
			{
				mLCASE();

			}
			break;
			case 78:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:584: LE
			{
				mLE();

			}
			break;
			case 79:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:587: LEFTC
			{
				mLEFTC();

			}
			break;
			case 80:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:593: LEN
			{
				mLEN();

			}
			break;
			case 81:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:597: LN
			{
				mLN();

			}
			break;
			case 82:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:600: LOG
			{
				mLOG();

			}
			break;
			case 83:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:604: LT
			{
				mLT();

			}
			break;
			case 84:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:607: MATCHES_INVALID
			{
				mMATCHES_INVALID();

			}
			break;
			case 85:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:623: MATCHES_VALID
			{
				mMATCHES_VALID();

			}
			break;
			case 86:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:637: MATCHKEY
			{
				mMATCHKEY();

			}
			break;
			case 87:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:646: MAX
			{
				mMAX();

			}
			break;
			case 88:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:650: MEASURE
			{
				mMEASURE();

			}
			break;
			case 89:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:658: MEDIAN
			{
				mMEDIAN();

			}
			break;
			case 90:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:665: MERGE
			{
				mMERGE();

			}
			break;
			case 91:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:671: MERGE_ON
			{
				mMERGE_ON();

			}
			break;
			case 92:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:680: MIN
			{
				mMIN();

			}
			break;
			case 93:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:684: MINUS
			{
				mMINUS();

			}
			break;
			case 94:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:690: MINUS2FUN
			{
				mMINUS2FUN();

			}
			break;
			case 95:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:700: MINUSFUN
			{
				mMINUSFUN();

			}
			break;
			case 96:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:709: MINUSMINUS
			{
				mMINUSMINUS();

			}
			break;
			case 97:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:720: MISSING
			{
				mMISSING();

			}
			break;
			case 98:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:728: MOD
			{
				mMOD();

			}
			break;
			case 99:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:732: MULTFUN
			{
				mMULTFUN();

			}
			break;
			case 100:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:740: MULTIPLY
			{
				mMULTIPLY();

			}
			break;
			case 101:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:749: MULTMULT
			{
				mMULTMULT();

			}
			break;
			case 102:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:758: NE
			{
				mNE();

			}
			break;
			case 103:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:761: NEX
			{
				mNEX();

			}
			break;
			case 104:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:765: NODUPLICATES
			{
				mNODUPLICATES();

			}
			break;
			case 105:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:778: NOT
			{
				mNOT();

			}
			break;
			case 106:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:782: NOT_EXISTS_IN
			{
				mNOT_EXISTS_IN();

			}
			break;
			case 107:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:796: NOT_EXISTS_IN_ALL
			{
				mNOT_EXISTS_IN_ALL();

			}
			break;
			case 108:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:814: NOT_IN
			{
				mNOT_IN();

			}
			break;
			case 109:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:821: NROOT
			{
				mNROOT();

			}
			break;
			case 110:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:827: NVL
			{
				mNVL();

			}
			break;
			case 111:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:831: ON
			{
				mON();

			}
			break;
			case 112:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:834: OR
			{
				mOR();

			}
			break;
			case 113:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:837: ORDER
			{
				mORDER();

			}
			break;
			case 114:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:843: OVERLAP
			{
				mOVERLAP();

			}
			break;
			case 115:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:851: PCSFILTER
			{
				mPCSFILTER();

			}
			break;
			case 116:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:861: PERCENT
			{
				mPERCENT();

			}
			break;
			case 117:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:869: PERCENTILE
			{
				mPERCENTILE();

			}
			break;
			case 118:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:880: PLUS
			{
				mPLUS();

			}
			break;
			case 119:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:885: PLUS2FUN
			{
				mPLUS2FUN();

			}
			break;
			case 120:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:894: PLUSFUN
			{
				mPLUSFUN();

			}
			break;
			case 121:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:902: PLUSPLUS
			{
				mPLUSPLUS();

			}
			break;
			case 122:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:911: POWER
			{
				mPOWER();

			}
			break;
			case 123:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:917: PUT
			{
				mPUT();

			}
			break;
			case 124:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:921: RANK
			{
				mRANK();

			}
			break;
			case 125:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:926: RELPERC
			{
				mRELPERC();

			}
			break;
			case 126:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:934: RENAME
			{
				mRENAME();

			}
			break;
			case 127:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:941: RETURN
			{
				mRETURN();

			}
			break;
			case 128:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:948: ROLE
			{
				mROLE();

			}
			break;
			case 129:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:953: ROUND
			{
				mROUND();

			}
			break;
			case 130:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:959: SEVERITY
			{
				mSEVERITY();

			}
			break;
			case 131:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:968: STD
			{
				mSTD();

			}
			break;
			case 132:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:972: SUBSTR
			{
				mSUBSTR();

			}
			break;
			case 133:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:979: SUM
			{
				mSUM();

			}
			break;
			case 134:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:983: TAVG
			{
				mTAVG();

			}
			break;
			case 135:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:988: TCOUNT
			{
				mTCOUNT();

			}
			break;
			case 136:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:995: THEN
			{
				mTHEN();

			}
			break;
			case 137:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1000: THRESHOLD
			{
				mTHRESHOLD();

			}
			break;
			case 138:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1010: TIMEFILTER
			{
				mTIMEFILTER();

			}
			break;
			case 139:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1021: TIME_BEHAVIOR
			{
				mTIME_BEHAVIOR();

			}
			break;
			case 140:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1035: TMAX
			{
				mTMAX();

			}
			break;
			case 141:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1040: TMEDIAN
			{
				mTMEDIAN();

			}
			break;
			case 142:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1048: TMIN
			{
				mTMIN();

			}
			break;
			case 143:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1053: TO
			{
				mTO();

			}
			break;
			case 144:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1056: TO_CURR
			{
				mTO_CURR();

			}
			break;
			case 145:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1064: TRIM
			{
				mTRIM();

			}
			break;
			case 146:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1069: TRUNC
			{
				mTRUNC();

			}
			break;
			case 147:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1075: TSTD
			{
				mTSTD();

			}
			break;
			case 148:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1080: TSUM
			{
				mTSUM();

			}
			break;
			case 149:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1085: TYPE
			{
				mTYPE();

			}
			break;
			case 150:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1090: UCASE
			{
				mUCASE();

			}
			break;
			case 151:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1096: UMEETS
			{
				mUMEETS();

			}
			break;
			case 152:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1103: UNION
			{
				mUNION();

			}
			break;
			case 153:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1109: USING
			{
				mUSING();

			}
			break;
			case 154:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1115: VIRAL
			{
				mVIRAL();

			}
			break;
			case 155:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1121: WITH
			{
				mWITH();

			}
			break;
			case 156:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1126: XOR
			{
				mXOR();

			}
			break;
			case 157:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1130: T__425
			{
				mT__425();

			}
			break;
			case 158:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1137: T__426
			{
				mT__426();

			}
			break;
			case 159:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1144: T__427
			{
				mT__427();

			}
			break;
			case 160:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1151: T__428
			{
				mT__428();

			}
			break;
			case 161:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1158: T__429
			{
				mT__429();

			}
			break;
			case 162:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1165: T__430
			{
				mT__430();

			}
			break;
			case 163:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1172: T__431
			{
				mT__431();

			}
			break;
			case 164:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1179: T__432
			{
				mT__432();

			}
			break;
			case 165:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1186: T__433
			{
				mT__433();

			}
			break;
			case 166:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1193: T__434
			{
				mT__434();

			}
			break;
			case 167:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1200: INTEGER_CONSTANT
			{
				mINTEGER_CONSTANT();

			}
			break;
			case 168:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1217: FLOAT_CONSTANT
			{
				mFLOAT_CONSTANT();

			}
			break;
			case 169:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1232: TIME_CLAUSE
			{
				mTIME_CLAUSE();

			}
			break;
			case 170:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1244: BOOLEAN_CONSTANT
			{
				mBOOLEAN_CONSTANT();

			}
			break;
			case 171:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1261: NULL_CONSTANT
			{
				mNULL_CONSTANT();

			}
			break;
			case 172:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1275: STRING_CONSTANT
			{
				mSTRING_CONSTANT();

			}
			break;
			case 173:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1291: IDENTIFIER
			{
				mIDENTIFIER();

			}
			break;
			case 174:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1302: WS
			{
				mWS();

			}
			break;
			case 175:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1305: EOL
			{
				mEOL();

			}
			break;
			case 176:
				// /Users/hadrien/Projects/java-vtl/java-vtl-lexer/src/main/resources/kohl/hadrien/antlr3/ValidationMl.g:1:1309: ML_COMMENT
			{
				mML_COMMENT();

			}
			break;

		}
	}

	protected class DFA6 extends DFA {

		public DFA6(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 6;
			this.eot = DFA6_eot;
			this.eof = DFA6_eof;
			this.min = DFA6_min;
			this.max = DFA6_max;
			this.accept = DFA6_accept;
			this.special = DFA6_special;
			this.transition = DFA6_transition;
		}

		@Override
		public String getDescription() {
			return "1532:1: FLOAT_CONSTANT : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( FLOATEXP )? | ( '0' .. '9' )+ FLOATEXP );";
		}
	}

	protected class DFA13 extends DFA {

		public DFA13(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 13;
			this.eot = DFA13_eot;
			this.eof = DFA13_eof;
			this.min = DFA13_min;
			this.max = DFA13_max;
			this.accept = DFA13_accept;
			this.special = DFA13_special;
			this.transition = DFA13_transition;
		}

		@Override
		public String getDescription() {
			return "1:1: Tokens : ( ABS | AGGREGATE | ALL | AND | AS | ASC | ASSIGN | ATTRCALC | ATTRIBUTE | AVG | AVGPERIOD | BETWEEN | BREAK | BREAKDATE | BY | CALC | CARTESIAN_PER | CASE | CHARLENGTH | CHARSET_MATCH | CHECK | CODELIST_MATCH | COMPLCHECK | CONCAT | COND | COUNT | COUNT_DISTINCT | CURRCHANGE | CURRENT_DATE | DATASET_LEVEL | DESC | DIFF | DIFFPERC | DIMENSION | DISCREPANCY | DIVDIV | DIVFUN | DIVIDE | DROP | ELSE | ELSEIF | ENDPERIOD | EQ | ERLEVEL | ERRORCODE | EVAL | EX | EXCHECK | EXCLUDE | EXISTS_IN | EXISTS_IN_ALL | EXKEY | EXP | FILTER | FIRST | FROM_CURR | GE | GET | GT | HIERARCHY | HMEETS | IF | IMBALANCE | IN | INBALANCE | INCLUDE | INDEXOF | INTDAY | INTERSECT | INTMONTH | INTYEAR | ISNULL | KEEP | KEY | KEYS | LAST | LCASE | LE | LEFTC | LEN | LN | LOG | LT | MATCHES_INVALID | MATCHES_VALID | MATCHKEY | MAX | MEASURE | MEDIAN | MERGE | MERGE_ON | MIN | MINUS | MINUS2FUN | MINUSFUN | MINUSMINUS | MISSING | MOD | MULTFUN | MULTIPLY | MULTMULT | NE | NEX | NODUPLICATES | NOT | NOT_EXISTS_IN | NOT_EXISTS_IN_ALL | NOT_IN | NROOT | NVL | ON | OR | ORDER | OVERLAP | PCSFILTER | PERCENT | PERCENTILE | PLUS | PLUS2FUN | PLUSFUN | PLUSPLUS | POWER | PUT | RANK | RELPERC | RENAME | RETURN | ROLE | ROUND | SEVERITY | STD | SUBSTR | SUM | TAVG | TCOUNT | THEN | THRESHOLD | TIMEFILTER | TIME_BEHAVIOR | TMAX | TMEDIAN | TMIN | TO | TO_CURR | TRIM | TRUNC | TSTD | TSUM | TYPE | UCASE | UMEETS | UNION | USING | VIRAL | WITH | XOR | T__425 | T__426 | T__427 | T__428 | T__429 | T__430 | T__431 | T__432 | T__433 | T__434 | INTEGER_CONSTANT | FLOAT_CONSTANT | TIME_CLAUSE | BOOLEAN_CONSTANT | NULL_CONSTANT | STRING_CONSTANT | IDENTIFIER | WS | EOL | ML_COMMENT );";
		}
	}

}

package aspects;

import  java.lang.String;
import  java.util.ArrayList;
import  java.time.*;
import  imports.LowLevelEvent;
import 	imports.HighLevelEvent;
import  imports.Entity;
import  imports.Multiset;
import  imports.HleEntityPair;
import java.util.HashSet;
import java.util.Set;

import larva.*;
public aspect _asp_AnalyserTest0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_AnalyserTest0.initialize();
}
}
before ( HleEntityPair hleEntityPair,Channel _c) : (call(* Channel.receive(..)) && target(_c) && (if (_c.equals(_cls_AnalyserTest0.highLevelToScenario))) && args(hleEntityPair) && if (hleEntityPair .getHighLevelEvent ().containsAnchorEvent ("Closed Browser"))) {

synchronized(_asp_AnalyserTest0.lock){

_cls_AnalyserTest0 _cls_inst = _cls_AnalyserTest0._get_cls_AnalyserTest0_inst();
_cls_inst.hleEntityPair = hleEntityPair;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 6/*closingBrowser*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 6/*closingBrowser*/);
}
}
before ( HleEntityPair hleEntityPair,Channel _c) : (call(* Channel.receive(..)) && target(_c) && (if (_c.equals(_cls_AnalyserTest0.highLevelToScenario))) && args(hleEntityPair) && if (hleEntityPair .getHighLevelEvent ().containsAnchorEvent ("Search Browser"))) {

synchronized(_asp_AnalyserTest0.lock){

_cls_AnalyserTest0 _cls_inst = _cls_AnalyserTest0._get_cls_AnalyserTest0_inst();
_cls_inst.hleEntityPair = hleEntityPair;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 2/*searchingBrowser*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 2/*searchingBrowser*/);
}
}
before ( HleEntityPair hleEntityPair,Channel _c) : (call(* Channel.receive(..)) && target(_c) && (if (_c.equals(_cls_AnalyserTest0.highLevelToScenario))) && args(hleEntityPair) && if (hleEntityPair .getHighLevelEvent ().containsAnchorEvent ("Downloaded File"))) {

synchronized(_asp_AnalyserTest0.lock){

_cls_AnalyserTest0 _cls_inst = _cls_AnalyserTest0._get_cls_AnalyserTest0_inst();
_cls_inst.hleEntityPair = hleEntityPair;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 4/*downloadingFile*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 4/*downloadingFile*/);
}
}
before ( HleEntityPair hleEntityPair,Channel _c) : (call(* Channel.receive(..)) && target(_c) && (if (_c.equals(_cls_AnalyserTest0.highLevelToScenario))) && args(hleEntityPair) && if (hleEntityPair .getHighLevelEvent ().containsAnchorEvent ("Opened Browser"))) {

synchronized(_asp_AnalyserTest0.lock){

_cls_AnalyserTest0 _cls_inst = _cls_AnalyserTest0._get_cls_AnalyserTest0_inst();
_cls_inst.hleEntityPair = hleEntityPair;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 0/*openingBrowser*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 0/*openingBrowser*/);
}
}
}
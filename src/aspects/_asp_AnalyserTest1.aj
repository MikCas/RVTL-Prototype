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
public aspect _asp_AnalyserTest1 {

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_AnalyserTest1.initialize();
}
}
before ( HleEntityPair hleEntityPair1,Channel _c) : (call(* Channel.receive(..)) && target(_c) && (if (_c.equals(_cls_AnalyserTest0.highLevelToScenario))) && args(hleEntityPair1) && if (hleEntityPair1 .getHighLevelEvent ().containsAnchorEvent ("Closed Browser"))) {

synchronized(_asp_AnalyserTest0.lock){
Entity subject;
subject =hleEntityPair1 .getEntity ();

_cls_AnalyserTest1 _cls_inst = _cls_AnalyserTest1._get_cls_AnalyserTest1_inst( subject);
_cls_inst.hleEntityPair1 = hleEntityPair1;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 16/*closingBrowser*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 16/*closingBrowser*/);
}
}
before ( HleEntityPair hleEntityPair1,Channel _c) : (call(* Channel.receive(..)) && target(_c) && (if (_c.equals(_cls_AnalyserTest0.highLevelToScenario))) && args(hleEntityPair1) && if (hleEntityPair1 .getHighLevelEvent ().containsAnchorEvent ("Search Browser"))) {

synchronized(_asp_AnalyserTest0.lock){
Entity subject;
subject =hleEntityPair1 .getEntity ();

_cls_AnalyserTest1 _cls_inst = _cls_AnalyserTest1._get_cls_AnalyserTest1_inst( subject);
_cls_inst.hleEntityPair1 = hleEntityPair1;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 12/*searchingBrowser*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 12/*searchingBrowser*/);
}
}
before ( HleEntityPair hleEntityPair1,Channel _c) : (call(* Channel.receive(..)) && target(_c) && (if (_c.equals(_cls_AnalyserTest0.highLevelToScenario))) && args(hleEntityPair1) && if (hleEntityPair1 .getHighLevelEvent ().containsAnchorEvent ("Downloaded File"))) {

synchronized(_asp_AnalyserTest0.lock){
Entity subject;
subject =hleEntityPair1 .getEntity ();

_cls_AnalyserTest1 _cls_inst = _cls_AnalyserTest1._get_cls_AnalyserTest1_inst( subject);
_cls_inst.hleEntityPair1 = hleEntityPair1;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 14/*downloadingFile*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 14/*downloadingFile*/);
}
}
before ( HleEntityPair hleEntityPair1,Channel _c) : (call(* Channel.receive(..)) && target(_c) && (if (_c.equals(_cls_AnalyserTest0.highLevelToScenario))) && args(hleEntityPair1) && if (hleEntityPair1 .getHighLevelEvent ().containsAnchorEvent ("Opened Browser"))) {

synchronized(_asp_AnalyserTest0.lock){
Entity subject;
subject =hleEntityPair1 .getEntity ();

_cls_AnalyserTest1 _cls_inst = _cls_AnalyserTest1._get_cls_AnalyserTest1_inst( subject);
_cls_inst.hleEntityPair1 = hleEntityPair1;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 10/*openingBrowser*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 10/*openingBrowser*/);
}
}
before ( HleEntityPair hleEntityPair1,Channel _c) : (call(* Channel.receive(..)) && target(_c) && (if (_c.equals(_cls_AnalyserTest0.highLevelToScenario))) && args(hleEntityPair1)) {

synchronized(_asp_AnalyserTest0.lock){
Entity subject;
subject =hleEntityPair1 .getEntity ();

_cls_AnalyserTest1 _cls_inst = _cls_AnalyserTest1._get_cls_AnalyserTest1_inst( subject);
_cls_inst.hleEntityPair1 = hleEntityPair1;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 8/*channelHighLevelToScenario*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 8/*channelHighLevelToScenario*/);
}
}
}
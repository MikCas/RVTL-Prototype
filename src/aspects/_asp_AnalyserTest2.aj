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
public aspect _asp_AnalyserTest2 {

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_AnalyserTest2.initialize();
}
}
before ( Entity entity1,LowLevelEvent lowLevelEvent1) : (call(* *.lowLevelEventEntityReader(..)) && args(lowLevelEvent1,entity1) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*)) && if (lowLevelEvent1 .getMessage ().contains ("[4688 /"))) {

synchronized(_asp_AnalyserTest0.lock){
Entity entity;
entity =entity1 ;

_cls_AnalyserTest2 _cls_inst = _cls_AnalyserTest2._get_cls_AnalyserTest2_inst( entity);
_cls_inst.entity1 = entity1;
_cls_inst.lowLevelEvent1 = lowLevelEvent1;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 22/*testAnchorEvent*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 22/*testAnchorEvent*/);
}
}
before ( Entity entity1,LowLevelEvent lowLevelEvent1) : (call(* *.lowLevelEventEntityReader(..)) && args(lowLevelEvent1,entity1) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*)) && if (lowLevelEvent1 .getMessage ().contains ("[4688 /")&& lowLevelEvent1 .getMessage ().contains ("C:\\Program Files\\Mozilla Firefox\\firefox.exe"))) {

synchronized(_asp_AnalyserTest0.lock){
Entity entity;
entity =entity1 ;

_cls_AnalyserTest2 _cls_inst = _cls_AnalyserTest2._get_cls_AnalyserTest2_inst( entity);
_cls_inst.entity1 = entity1;
_cls_inst.lowLevelEvent1 = lowLevelEvent1;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 20/*openedBrowserAnchorEvent*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 20/*openedBrowserAnchorEvent*/);
}
}
before ( Entity entity1,LowLevelEvent lowLevelEvent1) : (call(* *.lowLevelEventEntityReader(..)) && args(lowLevelEvent1,entity1) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*)) && if (lowLevelEvent1 .getTimeStampDesc ().equals ("Last Visited Time")&& lowLevelEvent1 .getSourceLong ().equals ("Firefox History")&& lowLevelEvent1 .getMessage ().contains ("Transition: DOWNLOAD"))) {

synchronized(_asp_AnalyserTest0.lock){
Entity entity;
entity =entity1 ;

_cls_AnalyserTest2 _cls_inst = _cls_AnalyserTest2._get_cls_AnalyserTest2_inst( entity);
_cls_inst.entity1 = entity1;
_cls_inst.lowLevelEvent1 = lowLevelEvent1;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 26/*downloadedFileAnchorEvent*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 26/*downloadedFileAnchorEvent*/);
}
}
before ( Entity entity1,LowLevelEvent lowLevelEvent1) : (call(* *.lowLevelEventEntityReader(..)) && args(lowLevelEvent1,entity1) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*)) && if (lowLevelEvent1 .getMessage ().contains ("[4689 /")&& lowLevelEvent1 .getMessage ().contains ("C:\\Program Files\\Mozilla Firefox\\pingsender.exe"))) {

synchronized(_asp_AnalyserTest0.lock){
Entity entity;
entity =entity1 ;

_cls_AnalyserTest2 _cls_inst = _cls_AnalyserTest2._get_cls_AnalyserTest2_inst( entity);
_cls_inst.entity1 = entity1;
_cls_inst.lowLevelEvent1 = lowLevelEvent1;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 28/*closedBrowserAnchorEvent*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 28/*closedBrowserAnchorEvent*/);
}
}
before ( Entity entity1,LowLevelEvent lowLevelEvent1) : (call(* *.lowLevelEventEntityReader(..)) && args(lowLevelEvent1,entity1) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*)) && if (lowLevelEvent1 .getMessage ().contains ("[2102 /"))) {

synchronized(_asp_AnalyserTest0.lock){
Entity entity;
entity =entity1 ;

_cls_AnalyserTest2 _cls_inst = _cls_AnalyserTest2._get_cls_AnalyserTest2_inst( entity);
_cls_inst.entity1 = entity1;
_cls_inst.lowLevelEvent1 = lowLevelEvent1;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 32/*removedDeviceAnchorEvent*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 32/*removedDeviceAnchorEvent*/);
}
}
before ( Entity entity1,LowLevelEvent lowLevelEvent1) : (call(* *.lowLevelEventEntityReader(..)) && args(lowLevelEvent1,entity1) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*)) && if (lowLevelEvent1 .getTimeStampDesc ().equals ("Last Visited Time")&& lowLevelEvent1 .getSourceLong ().equals ("Firefox History"))) {

synchronized(_asp_AnalyserTest0.lock){
Entity entity;
entity =entity1 ;

_cls_AnalyserTest2 _cls_inst = _cls_AnalyserTest2._get_cls_AnalyserTest2_inst( entity);
_cls_inst.entity1 = entity1;
_cls_inst.lowLevelEvent1 = lowLevelEvent1;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 24/*searchBrowserAnchorEvent*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 24/*searchBrowserAnchorEvent*/);
}
}
before ( Entity entity1,LowLevelEvent lowLevelEvent1) : (call(* *.lowLevelEventEntityReader(..)) && args(lowLevelEvent1,entity1) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_AnalyserTest0.lock){
Entity entity;
entity =entity1 ;

_cls_AnalyserTest2 _cls_inst = _cls_AnalyserTest2._get_cls_AnalyserTest2_inst( entity);
_cls_inst.entity1 = entity1;
_cls_inst.lowLevelEvent1 = lowLevelEvent1;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 18/*readCurrentLowLevelEventMatchedEntity*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 18/*readCurrentLowLevelEventMatchedEntity*/);
}
}
before ( Entity entity1,LowLevelEvent lowLevelEvent1) : (call(* *.lowLevelEventEntityReader(..)) && args(lowLevelEvent1,entity1) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*)) && if (lowLevelEvent1 .getMessage ().contains ("[2003 /"))) {

synchronized(_asp_AnalyserTest0.lock){
Entity entity;
entity =entity1 ;

_cls_AnalyserTest2 _cls_inst = _cls_AnalyserTest2._get_cls_AnalyserTest2_inst( entity);
_cls_inst.entity1 = entity1;
_cls_inst.lowLevelEvent1 = lowLevelEvent1;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 30/*detectedDeviceAnchorEvent*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 30/*detectedDeviceAnchorEvent*/);
}
}
}
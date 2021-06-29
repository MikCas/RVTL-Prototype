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
import larva.Clock;
public aspect _asp_AnalyserTest3 {

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_AnalyserTest3.initialize();
}
}
before ( Entity entity2,LowLevelEvent lowLevelEvent2) : (call(* *.lowLevelEventEntityReader(..)) && args(lowLevelEvent2,entity2) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_AnalyserTest0.lock){
Integer id;
Entity entity;
id =lowLevelEvent2 .getId ();
entity =entity2 ;

_cls_AnalyserTest3 _cls_inst = _cls_AnalyserTest3._get_cls_AnalyserTest3_inst( id,entity);
_cls_inst.entity2 = entity2;
_cls_inst.lowLevelEvent2 = lowLevelEvent2;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 34/*readCurrentlowLevelEventMatchedIdEntity*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 34/*readCurrentlowLevelEventMatchedIdEntity*/);
}
}
before ( Clock _c, long millis) : (call(* Clock.event(long)) && args(millis) && target(_c)  && (if (_c.name.equals("TPPMClock"))) && (if (millis == 10000)) && !cflow(adviceexecution())) {

synchronized(_asp_AnalyserTest0.lock){
Integer id;
Entity entity;
id =null ;
entity =null ;

synchronized(_c){
 if (_c != null && _c._inst != null) {
_c._inst._call(thisJoinPoint.getSignature().toString(), 36/*TPPMClockAt10*/);
//_c._inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 36/*TPPMClockAt10*/);
}
}
}
}
}
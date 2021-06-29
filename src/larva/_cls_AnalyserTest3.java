package larva;


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

import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_AnalyserTest3 implements _callable{

public static LinkedHashMap<_cls_AnalyserTest3,_cls_AnalyserTest3> _cls_AnalyserTest3_instances;

_cls_AnalyserTest2 parent;
public static Entity entity2;
public static LowLevelEvent lowLevelEvent2;
public Integer id;
int no_automata;

int _state_id_HLEConstruction;
 public ArrayList <LowLevelEvent >lowLevelEventSubTimeline =new ArrayList <LowLevelEvent >();
 public Multiset <Entity >entityMultiSet =new Multiset ();
 public Multiset <String >anchorEvents =new Multiset ();
public Clock TPPMClock;
 public boolean matchedWithTestEvent =false ;

public static void initialize(){
//note that this initialisation does not include user-defined declarations in the Variables section


_cls_AnalyserTest3_instances = new LinkedHashMap<_cls_AnalyserTest3,_cls_AnalyserTest3>();
}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_AnalyserTest3( Integer id,Entity entity) {
parent = _cls_AnalyserTest2._get_cls_AnalyserTest2_inst( entity);
TPPMClock = new Clock(this,"TPPMClock");
TPPMClock.register(10000l);
this.id = id;
}

public void initialisation() {
no_automata = 1;
//initialise automata
_state_id_HLEConstruction = 7;
   TPPMClock.reset(RunningClock.now);
}

public static _cls_AnalyserTest3 _get_cls_AnalyserTest3_inst( Integer id,Entity entity) { synchronized(_cls_AnalyserTest3_instances){
_cls_AnalyserTest3 _inst = new _cls_AnalyserTest3( id,entity);
if (_cls_AnalyserTest3_instances.containsKey(_inst))
{
_cls_AnalyserTest3 tmp = _cls_AnalyserTest3_instances.get(_inst);
 return _cls_AnalyserTest3_instances.get(_inst);
}
else
{
 _inst.initialisation();
 _cls_AnalyserTest3_instances.put(_inst,_inst);
 return _inst;
}
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_AnalyserTest3)
 && (id == null || id.equals(((_cls_AnalyserTest3)o).id))
 && (parent == null || parent.equals(((_cls_AnalyserTest3)o).parent)))
{return true;}
else
{return false;}
}

public int hashCode() {
return (id==null?1:id.hashCode()) *(parent==null?1:parent.hashCode()) *1;
}

public void _call(String _info, int... _event){
synchronized(_cls_AnalyserTest3_instances){
_performLogic_HLEConstruction(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_AnalyserTest3[] a = new _cls_AnalyserTest3[1];
synchronized(_cls_AnalyserTest3_instances){
a = _cls_AnalyserTest3_instances.keySet().toArray(a);}
for (_cls_AnalyserTest3 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_AnalyserTest3_instances){
_cls_AnalyserTest3_instances.remove(this);}
synchronized(TPPMClock){
TPPMClock.off();
TPPMClock._inst = null;
TPPMClock = null;}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}


public void _performLogic_HLEConstruction(String _info, int... _event) {

_cls_AnalyserTest0.pw.println("[HLEConstruction]AUTOMATON::> HLEConstruction("+id + " " + ") STATE::>"+ _string_HLEConstruction(_state_id_HLEConstruction, 0));
_cls_AnalyserTest0.pw.flush();

if (0==1){}
else if (_state_id_HLEConstruction==6){
		if (1==0){}
		else if ((_occurredEvent(_event,36/*TPPMClockAt10*/))){
		HighLevelEvent hle =new HighLevelEvent (parent.parent.highLevelId ,lowLevelEventSubTimeline ,entityMultiSet ,anchorEvents );
_cls_AnalyserTest0.pw .println ("HLE "+parent.parent.highLevelId +" CREATED\n");
_cls_AnalyserTest0.pw .println ("SUBTIMELINE\n");
for (LowLevelEvent lle :hle .getLowLevelEventSubTimeline ()){_cls_AnalyserTest0.pw .println ("\tevent "+lle .getId ());
}_cls_AnalyserTest0.pw .println ();
_cls_AnalyserTest0.pw .println ("ENTITY SET");
_cls_AnalyserTest0.pw .println ("\t"+hle .getEntityMultiSet ());
_cls_AnalyserTest0.pw .println ();
_cls_AnalyserTest0.pw .println ("ANCHOR EVENTS");
_cls_AnalyserTest0.pw .println ("\t"+hle .getAnchorEvents ());
for (Entity e :hle .getEntityMultiSet ().elementSet ()){if (e .getEntityType ()==true &&!hle .getAnchorEvents ().isEmpty ()){HleEntityPair hleep =new HleEntityPair (hle ,e );
parent.parent.highLevelToScenario .send (hleep );
}}parent.parent.highLevelId ++;

		_state_id_HLEConstruction = 5;//moving to state accept
		_goto_HLEConstruction(_info);
           _killThis(); //discard this automaton since an accepting state has been reached
		}
		else if ((_occurredEvent(_event,24/*searchBrowserAnchorEvent*/))){
		anchorEvents .add ("Search Browser");
matchedWithTestEvent =true ;

		_state_id_HLEConstruction = 6;//moving to state normal
		_goto_HLEConstruction(_info);
		}
		else if ((_occurredEvent(_event,20/*openedBrowserAnchorEvent*/))){
		anchorEvents .add ("Opened Browser");
matchedWithTestEvent =true ;

		_state_id_HLEConstruction = 6;//moving to state normal
		_goto_HLEConstruction(_info);
		}
		else if ((_occurredEvent(_event,26/*downloadedFileAnchorEvent*/))){
		anchorEvents .add ("Downloaded File");
matchedWithTestEvent =true ;

		_state_id_HLEConstruction = 6;//moving to state normal
		_goto_HLEConstruction(_info);
		}
		else if ((_occurredEvent(_event,28/*closedBrowserAnchorEvent*/))){
		anchorEvents .add ("Closed Browser");
matchedWithTestEvent =true ;

		_state_id_HLEConstruction = 6;//moving to state normal
		_goto_HLEConstruction(_info);
		}
		else if ((_occurredEvent(_event,18/*readCurrentLowLevelEventMatchedEntity*/))){
		lowLevelEventSubTimeline .add (parent.lowLevelEvent1 );
for (Entity e :parent.lowLevelEvent1 .getEntities ()){entityMultiSet .add (e );
}
		_state_id_HLEConstruction = 6;//moving to state normal
		_goto_HLEConstruction(_info);
		}
}
else if (_state_id_HLEConstruction==7){
		if (1==0){}
		else if ((_occurredEvent(_event,34/*readCurrentlowLevelEventMatchedIdEntity*/))){
		
		_state_id_HLEConstruction = 6;//moving to state normal
		_goto_HLEConstruction(_info);
		}
}
}

public void _goto_HLEConstruction(String _info){
 String state_format = _string_HLEConstruction(_state_id_HLEConstruction, 1);
_cls_AnalyserTest0.pw.println("[HLEConstruction]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + state_format);
_cls_AnalyserTest0.pw.flush();
}

public String _string_HLEConstruction(int _state_id, int _mode){
switch(_state_id){
case 6: if (_mode == 0) return "normal"; else return "normal";
case 7: if (_mode == 0) return "start"; else return "start";
case 5: if (_mode == 0) return "accept"; else return "(((SYSTEM REACHED AN ACCEPTED STATE)))  accept";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}
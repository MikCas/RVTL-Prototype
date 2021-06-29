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

public class _cls_AnalyserTest1 implements _callable{

public static LinkedHashMap<_cls_AnalyserTest1,_cls_AnalyserTest1> _cls_AnalyserTest1_instances;

_cls_AnalyserTest0 parent;
public static HleEntityPair hleEntityPair1;
public Entity subject;
int no_automata;

int _state_id_scenarioConstruction;
 public ArrayList <HighLevelEvent >highLevelTimeline =new ArrayList <HighLevelEvent >();

public static void initialize(){
//note that this initialisation does not include user-defined declarations in the Variables section


_cls_AnalyserTest1_instances = new LinkedHashMap<_cls_AnalyserTest1,_cls_AnalyserTest1>();
}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_AnalyserTest1( Entity subject) {
parent = _cls_AnalyserTest0._get_cls_AnalyserTest0_inst();
this.subject = subject;
}

public void initialisation() {
no_automata = 1;
//initialise automata
_state_id_scenarioConstruction = 4;
}

public static _cls_AnalyserTest1 _get_cls_AnalyserTest1_inst( Entity subject) { synchronized(_cls_AnalyserTest1_instances){
_cls_AnalyserTest1 _inst = new _cls_AnalyserTest1( subject);
if (_cls_AnalyserTest1_instances.containsKey(_inst))
{
_cls_AnalyserTest1 tmp = _cls_AnalyserTest1_instances.get(_inst);
 return _cls_AnalyserTest1_instances.get(_inst);
}
else
{
 _inst.initialisation();
 _cls_AnalyserTest1_instances.put(_inst,_inst);
 return _inst;
}
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_AnalyserTest1)
 && (subject == null || subject.equals(((_cls_AnalyserTest1)o).subject))
 && (parent == null || parent.equals(((_cls_AnalyserTest1)o).parent)))
{return true;}
else
{return false;}
}

public int hashCode() {
return (subject==null?1:subject.hashCode()) *(parent==null?1:parent.hashCode()) *1;
}

public void _call(String _info, int... _event){
synchronized(_cls_AnalyserTest1_instances){
_performLogic_scenarioConstruction(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_AnalyserTest1[] a = new _cls_AnalyserTest1[1];
synchronized(_cls_AnalyserTest1_instances){
a = _cls_AnalyserTest1_instances.keySet().toArray(a);}
for (_cls_AnalyserTest1 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_AnalyserTest1_instances){
_cls_AnalyserTest1_instances.remove(this);}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}


public void _performLogic_scenarioConstruction(String _info, int... _event) {

_cls_AnalyserTest0.pw.println("[scenarioConstruction]AUTOMATON::> scenarioConstruction("+subject + " " + ") STATE::>"+ _string_scenarioConstruction(_state_id_scenarioConstruction, 0));
_cls_AnalyserTest0.pw.flush();

if (0==1){}
else if (_state_id_scenarioConstruction==4){
		if (1==0){}
		else if ((_occurredEvent(_event,6/*closingBrowser*/))){
		_cls_AnalyserTest0.pw .println ();
HighLevelEvent highLevelEvent =hleEntityPair1 .getHighLevelEvent ();
Entity entity =hleEntityPair1 .getEntity ();
_cls_AnalyserTest0.pw .println ("Channel received high level event "+highLevelEvent .getId ()+" With Entity "+entity .getEntityValue ());
highLevelTimeline .add (highLevelEvent );
_cls_AnalyserTest0.pw .println ();
_cls_AnalyserTest0.pw .println ("SCENARIO");
_cls_AnalyserTest0.pw .println ("\t"+highLevelTimeline );

		_state_id_scenarioConstruction = 0;//moving to state browserClosed
		_goto_scenarioConstruction(_info);
           _killThis(); //discard this automaton since an accepting state has been reached
		}
		else if ((_occurredEvent(_event,2/*searchingBrowser*/))){
		_cls_AnalyserTest0.pw .println ();
HighLevelEvent highLevelEvent =hleEntityPair1 .getHighLevelEvent ();
Entity entity =hleEntityPair1 .getEntity ();
_cls_AnalyserTest0.pw .println ("Channel received high level event "+highLevelEvent .getId ()+" With Entity "+entity .getEntityValue ());
highLevelTimeline .add (highLevelEvent );

		_state_id_scenarioConstruction = 2;//moving to state browserSearched

		_goto_scenarioConstruction(_info);
		}
}
else if (_state_id_scenarioConstruction==2){
		if (1==0){}
		else if ((_occurredEvent(_event,2/*searchingBrowser*/))){
		_cls_AnalyserTest0.pw .println ();
HighLevelEvent highLevelEvent =hleEntityPair1 .getHighLevelEvent ();
Entity entity =hleEntityPair1 .getEntity ();
_cls_AnalyserTest0.pw .println ("Channel received high level event "+highLevelEvent .getId ()+" With Entity "+entity .getEntityValue ());
highLevelTimeline .add (highLevelEvent );

		_state_id_scenarioConstruction = 2;//moving to state browserSearched

		_goto_scenarioConstruction(_info);
		}
		else if ((_occurredEvent(_event,4/*downloadingFile*/))){
		_cls_AnalyserTest0.pw .println ();
HighLevelEvent highLevelEvent =hleEntityPair1 .getHighLevelEvent ();
Entity entity =hleEntityPair1 .getEntity ();
_cls_AnalyserTest0.pw .println ("Channel received high level event "+highLevelEvent .getId ()+" With Entity "+entity .getEntityValue ());
highLevelTimeline .add (highLevelEvent );

		_state_id_scenarioConstruction = 2;//moving to state browserSearched

		_goto_scenarioConstruction(_info);
		}
		else if ((_occurredEvent(_event,6/*closingBrowser*/))){
		_cls_AnalyserTest0.pw .println ();
HighLevelEvent highLevelEvent =hleEntityPair1 .getHighLevelEvent ();
Entity entity =hleEntityPair1 .getEntity ();
_cls_AnalyserTest0.pw .println ("Channel received high level event "+highLevelEvent .getId ()+" With Entity "+entity .getEntityValue ());
highLevelTimeline .add (highLevelEvent );
_cls_AnalyserTest0.pw .println ();
_cls_AnalyserTest0.pw .println ("SCENARIO");
_cls_AnalyserTest0.pw .println ("\t"+highLevelTimeline );

		_state_id_scenarioConstruction = 0;//moving to state browserClosed
		_goto_scenarioConstruction(_info);
           _killThis(); //discard this automaton since an accepting state has been reached
		}
}
}

public void _goto_scenarioConstruction(String _info){
 String state_format = _string_scenarioConstruction(_state_id_scenarioConstruction, 1);
_cls_AnalyserTest0.pw.println("[scenarioConstruction]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + state_format);
_cls_AnalyserTest0.pw.flush();
}

public String _string_scenarioConstruction(int _state_id, int _mode){
switch(_state_id){
case 0: if (_mode == 0) return "browserClosed"; else return "(((SYSTEM REACHED AN ACCEPTED STATE)))  browserClosed";
case 3: if (_mode == 0) return "notBrowsing"; else return "notBrowsing";
case 4: if (_mode == 0) return "browserOpen"; else return "browserOpen";
case 2: if (_mode == 0) return "browserSearched"; else return "browserSearched";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}
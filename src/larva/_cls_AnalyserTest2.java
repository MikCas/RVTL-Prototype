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

public class _cls_AnalyserTest2 implements _callable{

public static LinkedHashMap<_cls_AnalyserTest2,_cls_AnalyserTest2> _cls_AnalyserTest2_instances;

_cls_AnalyserTest0 parent;
public static Entity entity1;
public static LowLevelEvent lowLevelEvent1;
public Entity entity;
int no_automata;


public static void initialize(){
//note that this initialisation does not include user-defined declarations in the Variables section


_cls_AnalyserTest2_instances = new LinkedHashMap<_cls_AnalyserTest2,_cls_AnalyserTest2>();
}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_AnalyserTest2( Entity entity) {
parent = _cls_AnalyserTest0._get_cls_AnalyserTest0_inst();
this.entity = entity;
}

public void initialisation() {
no_automata = 0;
//initialise automata
}

public static _cls_AnalyserTest2 _get_cls_AnalyserTest2_inst( Entity entity) { synchronized(_cls_AnalyserTest2_instances){
_cls_AnalyserTest2 _inst = new _cls_AnalyserTest2( entity);
if (_cls_AnalyserTest2_instances.containsKey(_inst))
{
_cls_AnalyserTest2 tmp = _cls_AnalyserTest2_instances.get(_inst);
 return _cls_AnalyserTest2_instances.get(_inst);
}
else
{
 _inst.initialisation();
 _cls_AnalyserTest2_instances.put(_inst,_inst);
 return _inst;
}
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_AnalyserTest2)
 && (entity == null || entity.equals(((_cls_AnalyserTest2)o).entity))
 && (parent == null || parent.equals(((_cls_AnalyserTest2)o).parent)))
{return true;}
else
{return false;}
}

public int hashCode() {
return (entity==null?1:entity.hashCode()) *(parent==null?1:parent.hashCode()) *1;
}

public void _call(String _info, int... _event){
synchronized(_cls_AnalyserTest2_instances){
}
}

public void _call_all_filtered(String _info, int... _event){

_cls_AnalyserTest3[] a3 = new _cls_AnalyserTest3[1];
synchronized(_cls_AnalyserTest3._cls_AnalyserTest3_instances){
a3 = _cls_AnalyserTest3._cls_AnalyserTest3_instances.keySet().toArray(a3);}
for (_cls_AnalyserTest3 _inst : a3)
if (_inst != null
 && (entity == null || entity.equals(_inst.parent.entity))){
_inst._call(_info, _event); 
_inst._call_all_filtered(_info, _event);
}
}

public static void _call_all(String _info, int... _event){

_cls_AnalyserTest2[] a = new _cls_AnalyserTest2[1];
synchronized(_cls_AnalyserTest2_instances){
a = _cls_AnalyserTest2_instances.keySet().toArray(a);}
for (_cls_AnalyserTest2 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_AnalyserTest2_instances){
_cls_AnalyserTest2_instances.remove(this);}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}


public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}
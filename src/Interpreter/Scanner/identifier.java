package Interpreter.Scanner;

public class identifier {

   public identifier(String name,String dataType,String currentValue){
      this.name = name;
      this.dataType = dataType;
      this.currentValue = currentValue;
   }
   public identifier(String name){
      this.name = name;
   }

@Override
public String toString (){
      return "name:"+this.name+ " Type:"+this.dataType+" value:"+this.currentValue;
}

   public String name;
   public String dataType ="uninitialized";
   public String currentValue ="uninitialized";
}

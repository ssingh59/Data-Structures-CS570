//Name: SHWETA SINGH
//CWID: 10457493
//Class: 570A (HW1 Binary Number little-endian format)

//Imports
import java.util.Arrays;

public class BinaryNumber {

 private int data[];
 private boolean overflow;


 //main method -UML
 public static void main(String[] args) {

  BinaryNumber binaryNumberIntObj = new BinaryNumber(4);
  BinaryNumber binarynumberStrObj = new BinaryNumber("0001");
  System.out.println("\nThe length of binary number is: " + binarynumberStrObj.getLength());
  System.out.println("Array Item at given index is: " + binarynumberStrObj.getDigit(2));
  System.out.println("Binary number to decimal notation is: " + binarynumberStrObj.toDecimal());
  binarynumberStrObj.shiftR(3);
  BinaryNumber aBinaryNumber = new BinaryNumber("0010");
  binarynumberStrObj.add(aBinaryNumber);
  System.out.println("\nBinary number to string method result is: " + binarynumberStrObj.toString());
  binarynumberStrObj.clearOverflow();

 }

 //New data array will be created containing all zeroes.
 public BinaryNumber(int length) {
	 
  overflow = false;
  data = new int[length];

  
  if(length!= 0  && (length==(int)length )) {
	  
	  System.out.print("\nNew Binary number created of length " + length + " is: ");
	  for (int i = 0; i <= (data.length) - 1; i++) {
	   data[i] = 0;
	   System.out.print(data[i]);
	   
	  }
  }

  else {
	  System.out.print("Length cannot be zero and should be of type integer");
  }
	  
 }

 //This will check if input consists of 0 and 1
 //Data will be printed after number is created
 public BinaryNumber(String str) {

  boolean binaryInput = false;
  data = new int[str.length()];
  
  if (str.matches("^[01]+$")) {
	  
   binaryInput = true;
   System.out.print("\nBinary number created from input string is: ");
   for (int i = 0; i < str.length(); i++) {
    data[i] = Character.getNumericValue(str.charAt(i));
    System.out.print(data[i]);
    
   }
  } 
  else {
   System.out.println("Please provide valid string with 1's and 0's only.");
   binaryInput = false;
  }
 }

 //This will return the length of the array
 public int getLength() {
  return data.length;
 }


 //this will return the data at the specified index in array
 public int getDigit(int index) {
	 
  if (index >= 0 && index < data.length) {
   // System.out.println("Index is correct");
   return data[index];
   
  } 
  
  else {
   System.out.println("Array Index is out of bounds.");
   return -1;
  }
 }


 //This method will shift the binary number to right and add zeroes
 public void shiftR(int amount) {

  int dataLen = data.length;
  int resultLen = amount + dataLen;
  int[] result = new int[resultLen];
  int j = 0;

  if(amount!= 0  && (amount==(int)amount )) {
	  
  
  for (int k = amount; k < resultLen; k++) {
	  
   result[k] = data[j];
   j++;
   
  }
  
  System.out.print("Shift right of binary number resulted to: ");
  
  for (int i = 0; i < result.length; i++) {
   System.out.print(result[i]);
  }
  
  System.out.println("");
  }
  else {
	  System.out.println("Amount cannot be zero, Should be of type integer.");
  }
 }


 //This method will add the two binary number, carry will be considered as overflow
 //if length of two binary number is not equal, message will be printed
 public void add(BinaryNumber aBinaryNumber) {

  clearOverflow();
  int carry = 0;
  int[] addResult = new int[aBinaryNumber.getLength()]; //store binary add result of same length
  overflow = false;
  int sum = 0;

  if (aBinaryNumber.getLength() != 0 && data.length == aBinaryNumber.getLength()) {
   System.out.print("The sum of two binary number is: ");
   for (int i = 0; i < addResult.length; i++) {

    sum = carry + data[i] + aBinaryNumber.getDigit(i);

    switch (sum) {

     case 0:
      addResult[i] = sum % 2; //comes as zero
      System.out.print(addResult[i]);
      overflow = false;
      break;

     case 1:
      addResult[i] = sum % 2; //comes as one
      System.out.print(addResult[i]);
      carry = 0;
      overflow = false;
      break;

     case 2:
      addResult[i] = sum % 2; //comes as zero
      System.out.print(addResult[i]);
      carry = 1;
      overflow = true;
      break;

     case 3:
      addResult[i] = sum % 2; //comeas as three
      System.out.print(addResult[i]);
      carry = 1;
      overflow = true;
      break;
    }
   }
   if (overflow) {

    System.out.print(carry);
   }
  } else System.out.println("Please enter new Binary number of same length.");
 }


 //This method will print array to string if no overflow, else will print Overflow
 public String toString() {
	 
  //overflow is true
  if (overflow) {
   return "Overflow";
  } 
  
  else {
   //overflow is false
   System.out.println(" ");
  return Arrays.toString(data).replaceAll("\\[|\\]|,|\\s", "");
  }
 }

 //This method will convert binary number to decimal notation
 public int toDecimal() {
	 
  String str = Arrays.toString(data).replaceAll("\\[|\\]|,|\\s", "");
  return Integer.parseInt(str, 2);
  
 }


 //This will set the boolean to false
 public void clearOverflow() {
	 
  overflow = false;
  System.out.println("\nFlag reset to false.");

 }
}
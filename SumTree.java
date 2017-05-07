//Brian Walton
import mpi.*;

class SumTree{
    static public void main(String[] args) throws MPIException {

	MPI.Init(args);

	int Sum = 5;
	int SqSum = 0;
	int Tag = 0;
	int myID = MPI.COMM_WORLD.getRank();
	int Pcount = MPI.COMM_WORLD.getSize() ;
  int target = ((myID)-1)/2;
  int rec1 = ((myID)*2 +1);
  int rec2 = ((myID)*2 +2);

	int message[] = new int [1];
	message[0] = Sum;

    if(rec1 < Pcount){
   MPI.COMM_WORLD.recv(message, 1, MPI.INT, rec1, Tag);
   Sum += message[0];
   System.out.println("ID: " + myID + " Recv Sum: " + message[0] + " From ID: " + rec1 + " My Sum is now: " + Sum);
 }
  if(rec2 < Pcount){
   MPI.COMM_WORLD.recv(message, 1, MPI.INT, rec2, Tag);
   Sum += message[0];
   System.out.println("ID: " + myID + " Recv Sum: " + message[0] + " From ID: " + rec2 + " My Sum is now: " + Sum);
}
  	message[0] = Sum;
  if(myID!=0){
   MPI.COMM_WORLD.send(message, 1, MPI.INT, target, Tag);
 }else{
   System.out.println("ID: " + myID + " My Sum is: " + Sum);
 }
   System.out.println("ID: " + myID + " Sent Sum: " + Sum + " To ID:"+ target +"");
	MPI.Finalize();
    }
}

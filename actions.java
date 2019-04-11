package problem1;

class actions
{
  private int operation;
  private char letter;
  
  public void setOperation(int op)
  {
    this.operation = op;
  }
  
  public void setLetter(char let)
  {
    this.letter = let;
  }
  
  public int getOperation()
  {
    return this.operation;
  }
  
  public char getLetter()
  {
    return this.letter;
  }
}

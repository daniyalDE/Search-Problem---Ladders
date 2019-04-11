package problem1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem1
{
  public static void main(String[] args)
    throws FileNotFoundException, IOException
  {
    int ladder_hop_counter = 0;
    
    List<actions> action_list = new ArrayList();
    List<String> word_list = new ArrayList();
    List<String> ladder_list = new ArrayList();
    FileReader in = null;
    BufferedReader reader = null;
    try
    {
      in = new FileReader("wordList.txt");
      reader = new BufferedReader(in);
      String line;
      while ((line = reader.readLine()) != null) {
        word_list.add(line);
      }
    }
    finally
    {
      if (in != null) {
        in.close();
      }
    }
    String line;
    String start_word = args[0];
    
    String goal_word = args[1];
    for (int i = 0; i < start_word.length(); i++) {
      if (goal_word.indexOf(start_word.charAt(i)) < 0)
      {
        actions letter_op = new actions();
        letter_op.setLetter(start_word.charAt(i));
        letter_op.setOperation(0);
        action_list.add(letter_op);
      }
    }
    for (int i = 0; i < goal_word.length(); i++)
    {
      int dup_counter_goal = 0;
      int dup_counter_start = 0;
      if (start_word.indexOf(goal_word.charAt(i)) < 0)
      {
        actions letter_op = new actions();
        letter_op.setLetter(goal_word.charAt(i));
        letter_op.setOperation(1);
        action_list.add(letter_op);
      }
      else
      {
        int exists = 0;
        for (int c = 0; c < action_list.size(); c++) {
          if (goal_word.charAt(i) == ((actions)action_list.get(c)).getLetter()) {
            exists++;
          }
        }
        if (exists == 0)
        {
          for (int m = 0; m < goal_word.length(); m++) {
            if (goal_word.charAt(i) == goal_word.charAt(m)) {
              dup_counter_goal++;
            }
          }
          for (int n = 0; n < start_word.length(); n++) {
            if (goal_word.charAt(i) == start_word.charAt(n)) {
              dup_counter_start++;
            }
          }
          if (dup_counter_start < dup_counter_goal)
          {
            int add_count = dup_counter_goal - dup_counter_start;
            for (int h = 0; h < add_count; h++)
            {
              actions letter_op = new actions();
              letter_op.setLetter(goal_word.charAt(i));
              letter_op.setOperation(1);
              action_list.add(letter_op);
            }
          }
          else
          {
            int add_count = dup_counter_start - dup_counter_goal;
            for (int h = 0; h < add_count; h++)
            {
              actions letter_op = new actions();
              letter_op.setLetter(goal_word.charAt(i));
              letter_op.setOperation(0);
              action_list.add(letter_op);
            }
          }
        }
      }
    }
    ladder_list.add(start_word);
    while (!ladder_list.isEmpty())
    {
      String current_ladder = (String)ladder_list.get(0);
      String[] ladder_words = current_ladder.split("-");
      ladder_hop_counter = 0;
      
      String last_word = ladder_words[(ladder_words.length - 1)];
      if (last_word.equals(goal_word))
      {
        System.out.println((String)ladder_list.get(0));
        break;
      }
      List<actions> temp_actionlist = action_list;
      for (int i = 0; i < temp_actionlist.size(); i++)
      {
        int operation = ((actions)temp_actionlist.get(i)).getOperation();
        if (operation == 0)
        {
          StringBuilder sb = new StringBuilder(last_word);
          if (last_word.indexOf(((actions)temp_actionlist.get(i)).getLetter()) >= 0)
          {
            sb.deleteCharAt(last_word.indexOf(((actions)temp_actionlist.get(i)).getLetter()));
            String temp_startword = sb.toString();
            
            char[] chars = temp_startword.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            for (int j = 0; j < word_list.size(); j++)
            {
              String word = (String)word_list.get(j);
              if ((word.length() >= sorted.length() - 1) && (word.length() <= sorted.length() + 1))
              {
                char[] wordlist_char = word.toCharArray();
                Arrays.sort(wordlist_char);
                String wordlists_sorted = new String(wordlist_char);
                if (wordlists_sorted.equals(sorted)) {
                  if (!ladder_list.contains((String)ladder_list.get(0) + "-" + word)) {
                    ladder_list.add((String)ladder_list.get(0) + "-" + word);
                  }
                }
              }
            }
          }
        }
        else
        {
          StringBuilder sb = new StringBuilder(last_word);
          int dup_check_goal = 0;
          int dup_check_last = 0;
          for (int m = 0; m < goal_word.length(); m++) {
            if (((actions)temp_actionlist.get(i)).getLetter() == goal_word.charAt(m)) {
              dup_check_goal++;
            }
          }
          for (int n = 0; n < last_word.length(); n++) {
            if (((actions)temp_actionlist.get(i)).getLetter() == last_word.charAt(n)) {
              dup_check_last++;
            }
          }
          if ((last_word.indexOf(((actions)temp_actionlist.get(i)).getLetter()) < 0) || (dup_check_last < dup_check_goal))
          {
            sb.append(((actions)temp_actionlist.get(i)).getLetter());
            String temp_startword = sb.toString();
            
            char[] chars = temp_startword.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            for (int j = 0; j < word_list.size(); j++)
            {
              String word = (String)word_list.get(j);
              if ((word.length() >= sorted.length() - 1) && (word.length() <= sorted.length() + 1))
              {
                char[] wordlist_char = word.toCharArray();
                Arrays.sort(wordlist_char);
                String wordlists_sorted = new String(wordlist_char);
                if (wordlists_sorted.equals(sorted)) {
                  if (!ladder_list.contains((String)ladder_list.get(0) + "-" + word)) {
                    ladder_list.add((String)ladder_list.get(0) + "-" + word);
                  }
                }
              }
            }
          }
        }
      }
      ladder_list.remove(0);
    }
  }
}

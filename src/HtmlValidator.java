import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

public class HtmlValidator {

    public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
	Stack<HtmlTag> stack = new Stack<>();
	System.out.println("-- Input tags " + tags);
	while(!tags.isEmpty()) {
	    HtmlTag tag = tags.remove();
	    if(!tag.isSelfClosing()) {
		if(tag.isOpenTag()) {
		    stack.add(tag);
		} else if (stack.empty()){
		    return null;
		} else {
		    if(tag.getElement().equals(stack.peek().getElement())) {
			System.out.println("Popping " + stack.pop());
		    } else {
			System.out.println("-- close tag mismatch " + stack);
			return stack;
		    }

		}

	    }

	}
	System.out.println("-- returning stack " + stack);
	return stack;
    }
}


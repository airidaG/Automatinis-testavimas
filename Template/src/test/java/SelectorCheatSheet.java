public class SelectorCheatSheet {
    // --- XPath Selectors ---
// Basic XPath
// Select all elements of a tag
//driver.findElement(By.xpath("//tagname"));
//
//// Select elements with a specific attribute
//driver.findElement(By.xpath("//tagname[@attribute='value']"));
//
//// Select elements containing exact text
//driver.findElement(By.xpath("//tagname[text()='text']"));
//
//// Select elements containing partial text
//driver.findElement(By.xpath("//tagname[contains(text(), 'partialText')]"));
//
//// Select elements where attribute value starts with a specific value
//driver.findElement(By.xpath("//tagname[starts-with(@attribute, 'value')]"));
//
//// Select elements where attribute value ends with a specific value (XPath 2.0)
//driver.findElement(By.xpath("//tagname[ends-with(@attribute, 'value')]"));
//
//// Advanced XPath
//// Select elements with multiple attributes
//driver.findElement(By.xpath("//tagname[@attribute='value' and @anotherAttribute='anotherValue']"));
//
//// Select elements with multiple conditions
//driver.findElement(By.xpath("//tagname[attribute='value' or @anotherAttribute='anotherValue']"));
//
//// Select elements with a specific attribute
//driver.findElement(By.xpath("//tagname[@attribute]"));
//
//// Select child elements
//driver.findElement(By.xpath("//tagname[@attribute='value']/child::tagname"));
//
//// Select descendant elements
//driver.findElement(By.xpath("//tagname/descendant::tagname"));
//
//// Axes in XPath
//// Select the parent element
//driver.findElement(By.xpath("//tagname/parent::*"));
//
//// Select all ancestor elements
//driver.findElement(By.xpath("//tagname/ancestor::*"));
//
//// Select the next sibling element
//driver.findElement(By.xpath("//tagname/following-sibling::*"));
//
//// Select the previous sibling element
//driver.findElement(By.xpath("//tagname/preceding-sibling::*"));
//
//// Select all following elements
//driver.findElement(By.xpath("//tagname/following::*"));
//
//// Select all preceding elements
//driver.findElement(By.xpath("//tagname/preceding::*"));
//
//    // XPath Functions
//// Count elements matching a tag
//    int count = driver.findElements(By.xpath("count(//tagname)")).size();
//
//    // Get the string length of an element's content
//    int length = driver.findElement(By.xpath("string-length(//tagname)")).getText().length();
//
//// Normalize spaces in text
//driver.findElement(By.xpath("//div[normalize-space(text())='Hello']"));
//
//    // Extract a substring from text
//    String substring = driver.findElement(By.xpath("substring(//a, 1, 4)")).getText();
//
//
//// --- CSS Selectors ---
//// Basic CSS Selectors
//// Select all elements with a specific tag
//driver.findElement(By.cssSelector("tagname"));
//
//// Select element with a specific ID
//driver.findElement(By.cssSelector("#id"));
//
//// Select elements with a specific class
//driver.findElement(By.cssSelector(".classname"));
//
//// Select element with a specific tag and class
//driver.findElement(By.cssSelector("tagname.classname"));
//
//// Select element with a specific tag and ID
//driver.findElement(By.cssSelector("tagname#id"));
//
//// Attribute Selectors
//// Select elements with a specific attribute
//driver.findElement(By.cssSelector("[attribute]"));
//
//// Select elements with a specific attribute value
//driver.findElement(By.cssSelector("[attribute='value']"));
//
//// Select elements whose attribute contains a substring
//driver.findElement(By.cssSelector("[attribute*='partialValue']"));
//
//// Select elements whose attribute value starts with a specific value
//driver.findElement(By.cssSelector("[attribute^='value']"));
//
//// Select elements whose attribute value ends with a specific value
//driver.findElement(By.cssSelector("[attribute$='value']"));
//
//// Combinators in CSS
//// Select direct child elements
//driver.findElement(By.cssSelector("tagname1 > tagname2"));
//
//// Select the next sibling element
//driver.findElement(By.cssSelector("tagname1 + tagname2"));
//
//// Select all siblings after a specific element
//driver.findElement(By.cssSelector("tagname1 ~ tagname2"));
//
//// Pseudo-classes
//// Select nth child of an element
//driver.findElement(By.cssSelector("li:nth-child(2)"));
//
//// Select nth child of a specific type
//driver.findElement(By.cssSelector("p:nth-of-type(1)"));
//
//// Select the first child element
//driver.findElement(By.cssSelector("ul:first-child"));
//
//// Select the last child element
//driver.findElement(By.cssSelector("div:last-child"));
//
//// Select nth child from the end
//driver.findElement(By.cssSelector("p:nth-last-child(2)"));
//
//// Select elements on hover
//driver.findElement(By.cssSelector("button:hover"));
//
//// Select focused elements
//driver.findElement(By.cssSelector("input:focus"));
//
//// Select checked elements
//driver.findElement(By.cssSelector("input:checked"));
//
//// Select disabled elements
//driver.findElement(By.cssSelector("button:disabled"));
//
//// Pseudo-elements
//// Select content before an element
//driver.findElement(By.cssSelector("p::before"));
//
//// Select content after an element
//driver.findElement(By.cssSelector("p::after"));

}

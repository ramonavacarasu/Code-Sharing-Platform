
## [Hyperskill] Code Sharing Platform - Stage 4

#### Run Java Code on port 8889
 
##### Produces: "application/json;charset=UTF-8"
POST /api/code/new:  
-> take a JSON object with a field code and two other fields:
1. time field contains the time (in seconds) during which the snippet is accessible.
2. views field contains a number of views allowed for this snippet.
-> return a UUID of the snippet.
Code snippets should be accessible via UUID links.

GET /api/code/UUID - show what restrictions apply to the code piece. Use the keys time and views for that. A zero value (0) corresponds to the absence of the restriction.

GET /api/code/latest - return a JSON array with 10 most recently uploaded code snippets sorted from the newest to the oldest (it not returns any restricted snippets).


##### Produces: "text/html"
GET /code/new - return HTML that contains:
     
    1. <textarea id="code_snippet"> ... </textarea> with a code snippet;
    2. <input id="time_restriction" type="text"/> with the time restriction.
    3. <input id="views_restriction" type="text"/> with contain the views restriction
    Button <button id="send_snippet" type="submit" onclick="send()">Submit</button>.
    
GET /code/UUID:
-> return HTML with corresponding code snippet which contains the following elements:

    1. <span id="time_restriction"> ... </span> in case the time restriction is applied.
    2. <span id="views_restriction"> ... </span> in case the views restriction is applied.
-> if only one of the restrictions is applied, there it will be showed only one of the above elements.

GET /code/latest - return HTML that contains 10 most recently uploaded code snippets (it not returns any restricted snippets). 
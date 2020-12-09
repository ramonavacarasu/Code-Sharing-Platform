
## [Hyperskill] Code Sharing Platform - Stage 4 
GET /api/code/N - return JSON with the N-th uploaded code snippet.

GET /code/N - return HTML that contains the N-th uploaded code snippet.

POST /api/code/new - take a JSON object with a single field code, use it as the current code snippet, and return JSON with a single field id. ID is the unique number of the snippet that helps you can access it via the endpoint GET /code/N.

GET /code/new - return HTML that contains:
               
    Tags <textarea id="code_snippet"> ... </textarea> where you can paste a code snippet;
    Title Create;
    Button <button id="send_snippet" type="submit" onclick="send()">Submit</button>.


GET /api/code/latest - return a JSON array with 10 most recently uploaded code snippets sorted from the newest to the oldest.

GET /code/latest - return HTML that contains 10 most recently uploaded code snippets. Use the title Latest for this page.
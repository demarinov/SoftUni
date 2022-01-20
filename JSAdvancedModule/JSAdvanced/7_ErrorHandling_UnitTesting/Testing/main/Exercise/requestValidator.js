function validateRequest(validationRequest) {

    // {Method/URI/Version/Message}". 

    const validMethods = { 'GET': true, 'POST': true, 'DELETE': true, 'CONNECT': true };
    const versionTypes = {
        'HTTP/0.9':true, 
        'HTTP/1.0':true, 
        'HTTP/1.1':true, 
        'HTTP/2.0':true
    }; 

    const rgMessage = /^[^\<\>\\\&\'\"]+$/g;
    const rgUri = /^[a-zA-Z0-9\.\*]+$/g;

    if (validationRequest.method === undefined
        || validMethods[validationRequest.method] === undefined) {
        throw new Error('Invalid request header: Invalid Method');
    } else if (validationRequest.uri === undefined 
        || validationRequest.uri === ''
        || !rgUri.test(validationRequest.uri)) {
        throw new Error('Invalid request header: Invalid URI');
    } else if (validationRequest.version === undefined 
        ||  versionTypes[validationRequest.version] === undefined) {
        throw new Error('Invalid request header: Invalid Version');
    } else if (validationRequest.message === undefined 
        || (!rgMessage.test(validationRequest.message) 
        && validationRequest.message !== '')) {
        throw new Error('Invalid request header: Invalid Message');
    }

    return validationRequest;
}

function test() {

    try {
        let output = validateRequest({
            method: 'GET',
            uri: 'svn.public.catalog',
            version: 'HTTP/1.1',
            message: ''
        }
        );

        console.log(output);

        // output = validateRequest({
        //     method: 'OPTIONS',
        //     uri: 'git.master',
        //     version: 'HTTP/1.1',
        //     message: '-recursive'
        // }
        // );

        // console.log(output);

        output = validateRequest({
            method: 'GET',
            uri: 'a*skdks12',
            message: 'asc>',
            version: 'HTTP/1.1'
        }
        );

        console.log(output);

    } catch (ex) {
        console.log(ex.message);
    }

}

test();
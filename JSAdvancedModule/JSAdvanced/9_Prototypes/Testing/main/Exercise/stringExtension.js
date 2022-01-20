(function extendString() {

    // ensureStart(str) – the current string doesn't start with str, 
    // returns a new string with str appended to the begining, 
    // otherwise returns the original string
    String.prototype.ensureStart = function (str) {
        let newStr = this.toString();
        const rg = new RegExp("^"+str, 'g');

        if (!rg.test(this)) {
            return  str + newStr;
        }
        
        return newStr;
    };
    // ensureEnd(str) - the current string doesn't end with str, 
    // returns a new string with str appended  to the begining, 
    // otherwise returns the original string
    String.prototype.ensureEnd = function (str) {
        let newStr = this.toString();
        const rg = new RegExp(str+"$",'g');

        if (!rg.test(this)) {
            return  newStr+str;
        }
        
        return newStr;
    };
    // isEmpty() - return true if the string is empty, false otherwise
    String.prototype.isEmpty = function () {

        return this === null || this === undefined || this.length === 0;
    }
    // truncate(n) – returns the string truncated to n characters by removing 
    // words and appends an ellipsis (three periods) to the end. If a string is 
    // less than n characters long, return the same string. 
    // If it is longer, split the string where a space occurs and append an ellipsis 
    // to it so that the total length is less than or equal to n. 
    // If no space occurs anywhere in the string, return n - 3 characters and an ellipsis. 
    // If n is less than 4, return n amount of periods.
    String.prototype.truncate = function (n) {

        if (n < 4) {
            return '.'.repeat(n);
        }

        if (this.toString().length <= n) {
            return this.toString();
        } else if (this.length > n) {

            let strArray = this.split(' ');

            if (strArray.length === 1) {
                const len = n - 3;
                return this.substring(0, len) + '...';
            } 

            let newStr = strArray[0];

            for(let i = 1; i < strArray.length; i++) {

                newStr += ' ';
                newStr += strArray[i];

                if (newStr.length+3 > n) {
                    // to remove previous
                    const len = strArray[i].length;
                    newStr = newStr.substring(0, newStr.length - len-1);
                    break;
                }

                
            }

            newStr += '...';

            return newStr;

        }
    }

    // format(string, …params) - static method to replace placeholders with parameters. 
    // A placeholder is a number surrounded by curly braces. If parameter 
    // index cannot be found for a certain placeholder, 
    // do not modify it. Note static methods are attached to the String object 
    // instead of it’s prototype. See the examples for more info.

    String.format = function (str, ...params) {

            str = str.replace(/\{\d+\}/g, function(part) {
                let idx = Number(part.substring(1, part.length-1));   
                return params[idx] !== undefined ? params[idx]:part;
            });
        return str;
    };

    // test part

    // let str = 'my string';
    // str = str.ensureStart('my');
    // console.log(str);
    // str = str.ensureStart('hello ');
    // console.log(str);
    // str = str.truncate(16);
    // console.log(str);
    // str = str.truncate(14);
    // console.log(str);
    // str = str.truncate(8);
    // console.log(str);
    // str = str.truncate(4);
    // console.log(str);
    // str = str.truncate(2);
    // console.log(str);
    // str = String.format('The {0} {1} fox',
    //     'quick', 'brown');
    // console.log(str);    
    // str = String.format('jumps {0} {1}',
    //     'dog');
    // console.log(str);
})();


(function stringExtensionV2() {
    String.prototype.ensureStart = function (str) {
        if(! this.toString().startsWith(str)){
            return str + this.toString();
        }
        return this.toString();
    };

    String.prototype.ensureEnd = function (str) {
        if(! this.toString().endsWith(str)){
            return this.toString() + str;
        }
        return this.toString();
    };

    String.prototype.isEmpty = function () {
        return this.toString().localeCompare("") == 0;
    };

    String.prototype.truncate = function (n) {
        if(n <= 3){
            return ".".repeat(n);
        }
      if(this.toString().length <= n){
          return this.toString();
      } else {
          let lastIndex = this.toString().substr(0, n - 2).lastIndexOf(" ");
          if(lastIndex != -1){
              return this.toString().substr(0, lastIndex) + "...";
          } else {
              return this.toString().substr(0, n-3) + "...";
          }
      }
    };

    String.format = function (string, ...params) {
        for(let i=0; i<params.length; i++){
            let index = string.indexOf("{"+i+"}");
            while (index != -1) {
                string = string.replace("{"+i+"}", params[i]);
                index = string.indexOf("{"+i+"}");
            }
        }
        return string;
    }
})();
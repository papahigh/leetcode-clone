const http = require('node:http');

// send({
//     problemId: 1,
//     lang: "RUBY",
//     code: `
//                 def swap_first_and_last_words_solution(s)
//                   words = s.split
//
//                   if words.length <= 1
//                     return s
//                   end
//
//                   words[0], words[-1] = words[-1], words[0]
//
//                   return words.join(' ')
//                 end
//             `
// });

// send({
//     problemId: 1,
//     lang: "RUBY",
//     code: `
//                 def swap_first_and_last_words_solution(s)
//                     while true
//                       sleep 5
//                     end
//                 end
//             `
// });

send({
    problemId: 1,
    lang: "SWIFT",
    // language=swift
    code: `
        import Foundation
        
        public class Solution {
            public func swapFirstAndLastWords(_ s: String) -> String {
                let words = s.split(separator: " ")
        
                if words.count <= 1 {
                    return s
                }

                var wordsArray = Array(words)
                wordsArray.swapAt(0, wordsArray.count - 1)
        
                return wordsArray.joined(separator: " ")
            }
        }
    `
})

function send(payload) {
    const req_data = JSON.stringify(payload);

    const req_options = {
        host: 'localhost',
        port: '8080',
        path: '/api/submissions',
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Content-Length': Buffer.byteLength(req_data)
        }
    };

    const req = http.request(req_options, function (res) {
        res.setEncoding('utf8');
        res.on('data', function (res) {
            console.log(`GET -> http://localhost:8080/api/submissions/${JSON.parse(res).id}`);
        });
    });

    req.write(req_data);
    req.end();
}

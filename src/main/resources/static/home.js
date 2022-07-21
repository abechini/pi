(function() {

        var url = 'http://localhost:8081/request?name=';
        var msgIndex, key;
        var botui = new BotUI('final-project');
        var fileName;
        var methodName;
        var searchWord;
        var fileType;
        // Initial message
        botui.message.bot({
            size: 100,
            content: 'Welcome!'
        }).then(init);

        function init() {
            botui.message.bot({
                content: 'Hi! Please select the Reclamation below:'
            }).then(fileSelectFunc).then(function(res) {
                fileName = res.value
                fileType = res.text
                return botui.message.bot({
                    content: res.value + ' selected.'
                })
            }).then(function(res) {
                return botui.message.bot({
                    content: "Please select serach solutions :"
                })
            }).then(methodSelectFunc).then(function(res) {
                methodName = res.value
                return botui.message.bot({
                    content: res.value + ' selected.'
                })
            }).then(function(res) {
                return botui.message.bot({
                    content: 'What do you want to search? (Begin with \'search\')' + '<br /><br />' +
                    'What do you want to claim? (Begin with \'claim\')' + '<br /><br />' +
                        'Please follow the  rules below:<br />' +
                        '- To claim : Ex. claim  transaction issues <br />' +
                        '- To search  by particular year: Ex. search login error in 2020<br />' +
                        '- To search  by year range: Ex. search login error  from 2018 to 2020<br />'
                })
            })
                .then(function(res) {
                return botui.action.text({
                    action: {
                        size: 50,
                        placeholder: 'Ex. search loginErros in 2019  Or Claim transation errors'
                    }
                });
            })
                .then(function(res) {
                searchContent = res.value.toLowerCase();
                if (searchContent.search("search") == -1&& searchContent.search("claim")==-1) {
                    botui.message.bot({
                        content: 'You must type \'search\' or  \'claim\' in the beginning of your query. Please try again.'
                    }).then(init);
                }
                else {
                    sendFileMethodRequest(methodName);
                    botui.message.bot({
                        loading: true
                    })
                        .then(function (index) {
                            msgIndex = index;
                        }).then(function () {
                        return botui.message.bot({
                            delay: 3000,
                            content: 'Retry?'
                        })
                    }).then(function () {

                        return botui.action.button({
                            action: [{
                                icon: 'circle-thin',
                                text: 'Yes, continue',
                                value: "yes"
                            },
                                {
                                icon: "check",
                                text: "View search history",
                                value: "view"
                            }]
                        });
                    }).then(function (res) {
                        if (res.value === "yes") {
                            init();
                        } else if (res.value === "view") {
                            getSearchHistory();
                        } else if (res.value === "no") {
                            plotChart();
                        } else {
                            end();
                        }
                    })
                }
            });
        }

        // Get Search record history
        function getSearchHistory() {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", 'http://localhost:8081/getSearchHistory');
            xhr.onload = function () {
                var res_str = JSON.stringify(xhr.responseText);
                var result = JSON.parse(res_str);
                displayHistory(result);
            }
            xhr.send();
        }

        // Get current {method, content}, send different request
        function sendFileMethodRequest(methodName){
            if  (methodName === "MySQL") {
                getMySQLResult();
            } 
        }



        // Get MySQL result
        function getMySQLResult() {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'http://localhost:8081/getMySQL?filetype=' + fileType + '&searchMethod=' + methodName + '&searchContent=' + searchContent);
            xhr.onload = function() {
                var res_str = JSON.stringify(xhr.responseText);
                var result = JSON.parse(res_str);
                displayMessage(result);
            }
            xhr.send();
        }



        var fileSelectFunc = function() {
            return botui.action.button({
                addMessage: true,
                action: [{
                    text: 'Reclamations',
                    value: 'Reclamations'
                }]
            })
        }

        var methodSelectFunc = function () {
            return botui.action.button({
                addMessage: true,
                // so we could the address in message instead if 'Existing Address'
                action: [
                    {
                  text: 'search',
                    value: 'MySQL'
                }]
            })
        }

        function displayMessage(result) {
            // Update the message by using the index of loading icon
            botui.message.update(msgIndex, {
                content: 'Result for ' + methodName + ': <br><br>' + result
            })
        }

        function displayHistory(result) {
            botui.message.bot({
                content: 'View your history' + ': <br><br>' + result
            })
            init();
        }

        function plotChart() {
            botui.message.bot({
                content: "Here are performance charts."
            }).then(function(res) {
                return botui.message.bot({
                    delay: 1000,
                    content: '<img src="image1?filetype=' + fileType + '&searchContent=' + searchContent + '">'
                }).then(function(res) {
                    return botui.message.bot({
                        delay: 1000,
                        content: '<img src="image2?filetype=' + fileType + '">'
                    })
                }).then(init)
            })
        }

        function showCharts() {
            // botui.message.bot({
            //     content: 'Ok, I will show you performance charts.'
            // }).then(function(res) {
            //     return botui.message.bot({
            //         delay: 1000,
            //         content: '<img src=image?type=SA/LightSensor>'
            //     })
            // }).then(function(res) {
            //     return botui.message.bot({
            //         delay: 1000,
            //         content: '<img src=image?type=ActivFit>'
            //     })
            // }).then(function(res) {
            //     botui.message.bot({
            //         delay: 1000,
            //         content: '<img src=image?type=HeartRate>'
            //     });
            //     end();
            // });
            console.log("xxx");
        }

        function end() {
            botui.message.bot({
                content: "Thanks! Goodbye!"
            })
        }

    }
)();
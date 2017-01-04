angular.module('vtl', ['ui.codemirror', 'angular.filter'])
    .controller('ExecutionController', ['$scope', '$http', '$q', function ($scope, $http, $q) {
        "use strict";
        $scope.expression = "a := get(\"1104\")";

        $scope.editorOptions = {
            lineWrapping: true,
            lineNumbers: true,
            mode: "vtl",
            gutters: ["CodeMirror-lint-markers"],
            lint: {
                async: true
            }
        };

        $scope.options = {
            limitTuple: 5
        };

        $scope.variables = [];

        $scope.datasets = {};

        $scope.fetchData = function (dataset) {
            var data = $http.get("/dataset/" + dataset + "/data");
            data.then(function (responce) {
                $scope.datasets[dataset]["data"] = responce.data;
            });
        };

        $scope.remove = function (dataset) {
            var data = $http.delete("/dataset/" + dataset);
            data.then(function (responce) {
                delete $scope.datasets[dataset];
            });
        };

        $scope.execute = function () {
            // Simple GET request example:
            $http({
                data: $scope.expression,
                method: 'POST',
                url: '/execute'
            }).then(function successCallback(response) {

                var datasets = response.data;
                var promises = {};

                $scope.variables = datasets;

                for (var i in datasets) {
                    var dataset = datasets[i];
                    var promise = $http.get("/dataset/" + dataset + "/structure");
                    promises[dataset] = promise.then(function (response) {
                        return {variables: response.data.dataStructure};
                    });
                }

                $q.all(promises).then(function (result) {
                    $scope.datasets = result;
                })

            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
        }
    }]);

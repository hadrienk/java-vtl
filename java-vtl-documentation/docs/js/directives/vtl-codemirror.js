/*global define*/
'use strict';

define(['angular', 'ui.codemirror'], function (angular) {
    var moduleName = 'vtl.code';
    angular.module(moduleName, ['ui.codemirror'])
        .controller('VtlCodeController', ['$scope', '$http', function ($scope, $http) {
            $scope.options = {
                lineWrapping: true,
                lineNumbers: true,
                indentUnit: 4,
                tabSize: 4,
                mode: 'vtl',
                gutters: ['CodeMirror-lint-markers']
            };

            $scope.error = null;
            $scope.loading = false;

            $scope.execute = function () {

                $scope.loading = true;
                $scope.error = null;

                var execution = {
                    datasets: $scope.input,
                    expression: $scope.expression
                };

                // Simple GET request example:
                $http({
                    data: execution,
                    method: 'POST',
                    url: 'http://localhost:8080/execute2'
                }).then(function successCallback(response) {
                    $scope.loading = false;

                    $scope.output = response.data.datasets;
                }, function failureCallback(response) {
                    $scope.loading = false;
                    $scope.error = response.data;
                });
            }

        }])
        .directive('vtlCode', function () {
            return {
                scope: {
                    expression: '=',
                    input: "=",
                    output: "="
                },
                restrict: 'E',
                transclude: true,
                replace: true,
                controller: 'VtlCodeController',
                templateUrl: '../../js/directives/vtl-codemirror.html'
            };
        });
    return moduleName;
});

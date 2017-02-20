/*global define*/
'use strict';

define(['angular',], function (angular) {
    var moduleName = 'vtl.data';
    angular.module(moduleName, [])
        .directive('vtlData', function () {
            return {
                restrict: 'E',
                scope: {
                    datasets: '=',
                },
                replace: true,
                templateUrl: '../../js/directives/vtl-data.html'
            };
        });
    return moduleName;
});

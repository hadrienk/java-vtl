/*global define*/
'use strict';

define(['angular'], function (angular) {
    var moduleName = 'vtl.dataset';
    angular.module(moduleName, [])
        .directive('vtlDataset', function () {
            return {
                restrict: 'E',
                scope: {
                    dataset: '=',
                },
                transclude: true,
                replace: true,
                templateUrl: '../../js/directives/vtl-dataset.html'
            };
        });
    return moduleName;
});

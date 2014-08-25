<%--
  Created by IntelliJ IDEA.
  User: Elyor
  Date: 8/17/2014
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<link href="/resources/js/agularjs/ng-table/ng-table.min.css"/>
<link href="/resources/js/select2/select2-bootstrap.css"/>

<script src="/resources/js/select2/select2.min.js"></script>
<script src="/resources/js/agularjs/angular.min.js"></script>
<script src="/resources/js/agularjs/angular-resource.min.js"></script>
<script src="/resources/js/agularjs/ng-table/ng-table.min.js"></script>
<script src="/resources/js/agularjs/ng-table/ng-table-export.src.js"></script>
<script>

    var restUrl = '/noteServlet';
    var tagUrl = '/tagServlet';
    var app = angular.module("noteApp", ['ngResource', 'ngTable', 'ngTableExport']);

    app.controller("noteController", ['$http', '$scope', '$filter', 'ngTableParams', function ($http, $scope, $filter, ngTableParams) {

        //reload list
        $scope.getNotes = function () {
            $http({
                url: restUrl,
                method: 'GET',
                params: {
                },
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                    .success(function (data, status, headers, config) {

                        $scope.notes = data.notes;
                        $scope.tableParams = new ngTableParams({
                            page: 1,// show first page
                            count: 10, // count per page
                            filter: {
                                // initial filter
                            },
                            sorting: {
                                // initial sorting
                            }
                        }, {
                            total: $scope.notes.length, // length of data
                            getData: function ($defer, params) {

                                // use build-in angular filter
                                var filteredData = params.filter() ?
                                        $filter('filter')($scope.notes, params.filter()) :
                                        $scope.notes;
                                var orderedData = params.sorting() ?
                                        $filter('orderBy')(filteredData, params.orderBy()) :
                                        $scope.notes;

                                // set total for recalc pagination
                                params.total(orderedData.length);
                                $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                            }
                        });

                        console.log(data);
                    })
                    .error(function (data, status, headers, config) {
                        console.error(data);
                    });
        };

        //create item
        $scope.addNote = function (note) {
            $http({
                url: restUrl,
                method: 'POST',
                params: {
                    'title': note.title,
                    'content': note.content,
                    'tags': note.tags
                },
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                    .success(function (data, status, headers, config) {
                        if (data.status == "200") {
                            note = data.note;
                            $scope.notes.push(note);

                            $('#addNoteModal').modal('hide');
                            $('#addNoteModal').find("form").eq(0).trigger("reset");
                        } else {
                            alert("Inserting error")
                        }
                    })
                    .error(function (data, status, headers, config) {
                        alert(data);
                    });
        };

        //tags
        $scope.getTags = function () {
            $http({
                url: tagUrl,
                method: 'POST',
                params: {
                },
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                    .success(function (data, status, headers, config) {

                        var items = jQuery.map(data.tags, function (n, i) {
                            return '"' + n + '"';
                        }).join(",");
 
                        $(".tags").select2({
                            tags: [items],
                            maximumSelectionSize: 250,
                            allowClear: true,
                            tokenSeparators: [" "],
                            multiple: true
                        });

                        console.log(data);
                    })
                    .error(function (data, status, headers, config) {
                        console.error(data);
                    });
        };

        //edit item
        $scope.editNote = function (note) {
            $http({
                url: restUrl,
                method: 'PUT',
                params: {
                    'id': note.id,
                    'title': note.title,
                    'content': note.content,
                    'tags': note.tags
                },
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                    .success(function (data, status, headers, config) {
                        if (data.status == "200") {
                            $scope.notes[$scope.notes.indexOf(note)] = data.note;
                            $('#editNoteModal_' + note.id).modal('hide');
                            $('#editNoteModal_' + note.id).find("form").eq(0).trigger("reset");
                        } else {
                            alert("Updating error");
                        }
                    })
                    .error(function (data, status, headers, config) {
                        alert(data);
                    });
        };

        //delete item
        $scope.deleteNote = function (note) {

            $http({
                url: restUrl,
                method: 'DELETE',
                params: {
                    'id': note.id
                },
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                    .success(function (data, status, headers, config) {
                        if (data.status == "200") {
                            $scope.notes.splice($scope.notes.indexOf(note), 1);
                            $('#deleteNoteModal_' + note.id).modal('hide');
                        } else {
                            alert("Deleting error");
                        }
                    })
                    .error(function (data, status, headers, config) {
                        alert(data);
                    });
        };

        $scope.init = function () {

            //load list
            $scope.getNotes();

            //load tags
            $scope.getTags();

        };

        $scope.init();

    }]);

</script>
<div class="page-header">
    <h2>
        <spa class="glyphicon glyphicon-tasks"></spa>
        List of notes
    </h2>
</div>
<div class="panel panel-default" ng-app="noteApp" ng-controller="noteController">
<!-- Default panel contents -->
<div class="panel-heading">
    <p>

        <a class="btn btn-default" data-toggle="modal" data-target="#addNoteModal">Create new note</a>

        <a class="btn btn-primary" ng-mousedown="csv.generate()" ng-href="{{ csv.link() }}" download="notes.csv">
            Export to CSV</a>

    </p>

    <div class="modal" id="addNoteModal" tabindex="-1" role="dialog"
         aria-labelledby="addNoteModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">Create new note</h4>
                </div>
                <div class="modal-body">
                    <form class="form-signup" ng-class="{danger: note}">

                        <div class="form-group">
                            <label class="control-label">Title:</label>
                            <input type="text" ng-model="note.title" class="form-control" required="required"/>
                        </div>

                        <div class="form-group">
                            <label class="control-label">Content:</label>
                            <textarea ng-model="note.content" class="form-control">
                            </textarea>
                        </div>

                        <div class="form-group">
                            <label class="control-label">Tags:</label>
                            <input type="text" ng-model="note.tags" class="form-control tags"/>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close
                    </button>
                    <button type="button" class="btn btn-primary" ng-hide="note.id" ng-click="addNote(note)">
                        Create
                    </button>
                </div>
            </div>
        </div>
    </div>

</div>
<div class="panel-body">

    <a class="btn btn-link" ng-click="getNotes()">
        <span class="glyphicon glyphicon-refresh"></span>
        Reload list</a>

    <!-- Table -->
    <table class="table table-table ng-table-responsive" show-filter="true" export-csv="csv" ng-table="tableParams">

        <tr ng-repeat="note in notes">
            <td data-title="'#'" sortable="'id'">{{note.id}}</td>
            <td data-title="'Title'" sortable="'title'" filter="{ 'title': 'text' }">
                {{note.title}}
            </td>
            <td data-title="'Create Date'">{{note.create_date}}</td>
            <td data-title="'Modified Date'">{{note.modified_date}}</td>
            <td data-title="">
                <a class="btn btn-success" data-toggle="modal" data-target="#editNoteModal_{{note.id}}"><span
                        class="glyphicon glyphicon-edit"></span></a>
                <a class="btn btn-danger" data-toggle="modal" data-target="#deleteNoteModal_{{note.id}}">
                    <span class="glyphicon glyphicon-trash"></span> </a>
                <a class="btn  btn-info" data-toggle="modal" data-target="#viewNoteModal_{{note.id}}">
                    <span class="glyphicon glyphicon-cog"></span>
                </a>

                <!--
                View
                -->
                <div class="modal" id="viewNoteModal_{{note.id}}" tabindex="-1" role="dialog"
                     aria-labelledby="viewNoteModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span
                                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title">View note N-{{note.id}}</h4>
                            </div>
                            <div class="modal-body">
                                <table class="table table-bordered">
                                    <tr>
                                        <th>Title</th>
                                        <td>{{note.title}}</td>
                                    </tr>
                                    <tr>
                                        <th>Content</th>
                                        <td>{{note.content}}</td>
                                    </tr>
                                    <tr>
                                        <th>Create Date</th>
                                        <td>{{note.create_date}}</td>
                                    </tr>
                                    <tr>
                                        <th>Modified Date</th>
                                        <td>{{note.modified_date}}</td>
                                    </tr>
                                    <tr>
                                        <th>Tags:</th>
                                        <td>{{note.tags}}</td>
                                    </tr>
                                    <tr>
                                        <th>Versions</th>
                                        <td>{{note.version_count}}</td>
                                    </tr>
                                    <tr>
                                        <th>Version Id</th>
                                        <td>{{note.version_id}}</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close
                                </button>
                                <button type="button" class="btn btn-warning" ng-click="historyNote(note)">
                                    History
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <!--
                Delete
                -->
                <div class="modal" id="deleteNoteModal_{{note.id}}" tabindex="-1" role="dialog"
                     aria-labelledby="deleteNoteModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span
                                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title">Delete note N-{{note.id}}</h4>
                            </div>
                            <div class="modal-body">
                                <h4>
                                    You are sure to delete this item?
                                </h4>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close
                                </button>
                                <button type="button" class="btn btn-danger" ng-click="deleteNote(note)">OK
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <!--
                Edit
                -->
                <div class="modal" id="editNoteModal_{{note.id}}" tabindex="-1" role="dialog"
                     aria-labelledby="editNoteModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span
                                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title">Edit note N-{{note.id}}</h4>
                            </div>
                            <div class="modal-body">
                                <form class="form-signup" ng-class="{danger: note}">

                                    <div class="form-group">
                                        <label class="control-label">Title:</label>
                                        <input type="text" ng-model="note.title" value="{{note.title}}"
                                               class="form-control" required="required"/>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label">Content:</label>
                                        <textarea ng-model="note.content" class="form-control">
                                            {{note.content}}
                                        </textarea>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label">Tags:</label>
                                        <input type="text" ng-model="note.tags" value="{{note.tags}}"
                                               class="form-control tags"/>
                                    </div>

                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close
                                </button>
                                <button type="button" class="btn btn-success" ng-click="editNote(note)">
                                    Save
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

            </td>
        </tr>
    </table>
</div>

</div>
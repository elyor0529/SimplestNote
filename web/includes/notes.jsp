<%--
  Created by IntelliJ IDEA.
  User: Elyor
  Date: 8/17/2014
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<script src="../resources/js/dataTables.bootstrap.js"></script>
<script src="../resources/css/dataTables.bootstrap.css"></script>
<script src="../resources/js/jquery.dataTables.min.js"></script>
<script src="../resources/js/dataTables.bootstrap.js"></script>
<script>
    $(function () {

        $('#note-list').dataTable({
            "processing": true,
            "serverSide": true,
            "searching": false,
            "ordering": false,
            "lengthMenu": [
                [10, 25, 50, 100, -1],
                [10, 25, 50, 100, "All"]
            ],
            "ajax": {
                "url": "/noteListServlet",
                "type": "POST"
            },
            "columns": [
                { "data": "id" },
                { "data": "title" },
                { "data": "create_date" },
                { "data": "modified_date" },
                { "data": null }
            ],
            "pagingType": "full_numbers",
            "scrollY": "600px",
            "scrollX": "100%",
            "scrollCollapse": true
        });

        $('#noteDeleteModal').modal({
            backdrop: true,
            keyboard: true,
            show: false
        });

        $('a[data-action="delete"]').click(function () {
            var self = $(this);
            var url = self.data("href");

            if ($.trim(url)) {
                $("#noteDeleteForm").attr("action", url);

                return true;
            }

            return false;
        });
    });
</script>
<div class="page-header">
    <h2>List of notes</h2>
</div>
<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading">
        <p>
            <a data-href="/noteCreate" data-action="create" class="btn btn-default">Create new note</a>
        </p>
    </div>
    <div class="panel-body">
        <!-- Table -->
        <table id="note-list" class="table table-bordered" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th width="10%">#</th>
                <th width="40%">Title</th>
                <th width="10%">Created Date</th>
                <th width="10%">Modified Date</th>
                <th width="30%">
                </th>
            </tr>
            </thead>

        </table>
    </div>

</div>


<div class="modal" id="noteDeleteModal" tabindex="-1" role="dialog"
     aria-labelledby="noteDeleteModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                <h4>
                    You are sure to delete this item?
                </h4>
            </div>
            <div class="modal-footer">
                <form action="about:blank" id="noteDeleteForm" method="post">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close
                    </button>
                    <button type="submit" class="btn btn-danger">OK</button>
                </form>
            </div>
        </div>
    </div>
</div>
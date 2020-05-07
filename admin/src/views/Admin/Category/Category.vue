<template>
    <div>
        <!-- Modal -->
        <div class="modal fade" id="saveModal" tabindex="-1" role="dialog" aria-labelledby="saveModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="saveModalLabel">编辑分类</h4>
                    </div>
                    <div class="modal-body">
                        <form>
                                        <div class="form-group">
                                            <label for="parent">父id</label>
                                            <input type="text" v-model="categoryToEdit.parent" class="form-control" id="parent"
                                                   placeholder="父id">
                                        </div>
                                        <div class="form-group">
                                            <label for="name">名称</label>
                                            <input type="text" v-model="categoryToEdit.name" class="form-control" id="name"
                                                   placeholder="名称">
                                        </div>
                                        <div class="form-group">
                                            <label for="sort">顺序</label>
                                            <input type="text" v-model="categoryToEdit.sort" class="form-control" id="sort"
                                                   placeholder="顺序">
                                        </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" @click="saveOneCategory">保存</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <P>
                    <button class="btn btn-white btn-default btn-round" @click="showEditCategoryDialog(null)">
                        <i class="ace-icon fa fa-save red2"></i>
                        新增
                    </button>
                    &nbsp;
                    <button class="btn btn-white btn-default btn-round"
                            @click="getCategoryList(1,$refs.pagination.size)">
                        <i class="ace-icon fa fa-refresh red2"></i>
                        刷新
                    </button>
                </P>
                <table id="categoryTable" class="table  table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>父id</th>
                        <th>名称</th>
                        <th>顺序</th>

                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="category in tableData.rows" :key="category.id">
                        <td>{{category.id}}</td>
                        <td>{{category.parent}}</td>
                        <td>{{category.name}}</td>
                        <td>{{category.sort}}</td>
                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <button class="btn btn-xs btn-info" @click="showEditCategoryDialog(category)">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    编辑
                                </button>

                                <button class="btn btn-xs btn-danger" @click="delOneCategory(category.id)">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                    删除
                                </button>
                            </div>

                            <div class="hidden-md hidden-lg">
                                <div class="inline pos-rel">
                                    <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown"
                                            data-position="auto">
                                        <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                    </button>

                                    <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                        <li>
                                            <a href="javascript:void(0);" class="tooltip-success" data-rel="tooltip"
                                               title="Edit" @click="showEditCategoryDialog(category)">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete"
                                               @click="delOneCategory(category.id)">
																			<span class="red">
																				<i class="ace-icon fa fa-trash-o bigger-120"></i>
																			</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <pagination :item-count="5" :total="tableData.total" :size="currentPageConfig.tableSize"
                            :items="[1,5,10,30,50,100]"
                            @list="getCategoryList" @selectChanged="paginationSelectChanged" ref="pagination"/>
            </div><!-- /.span -->
        </div><!-- /.row -->
    </div>
</template>

<script>
    import {getCategoryList, addOneCategory, editOneCategory, delOneCategory} from "api/admin/category";
    import Pagination from "components/Pagination/Pagination";
    import {MessageBox, Swal} from "common/utils/SweetAlert2";
    import MaskLoading from "common/utils/LoadingMask";
    import Validator from "common/utils/validator";

    export default {
        name: "Index",
        components: {Pagination},
        data() {
            return {
                tableData: [],
                categoryToEdit: {
                    parent: null,
                    name: null,
                    sort: null,
                },
                currentPageConfig: {
                    tableSize: 5,
                    showLoading: false
                },
            }
        },
        methods: {
            getCategoryList(page, size) {
                let _this = this;
                getCategoryList(page, size).then(data => {
                    _this.tableData = data.data
                    //由于数据的更新，等dom更新以后，$nextTick里的操作会被执行
                    _this.$nextTick(() => _this.$refs.pagination.render(page))
                }).catch(reason => {
                    new MessageBox({
                        message: reason.msg,
                        icon: 'error'
                    }).toast()
                })
            },
            paginationSelectChanged(currentPageSize) {
                let _this = this;
                _this.currentPageConfig.tableSize = currentPageSize;
                _this.getCategoryList(1, this.currentPageConfig.tableSize)
            },
            //如果传入了category，说明是编辑，否则是新增
            showEditCategoryDialog(category) {
                let _this = this
                if (category) {
                    //因为直接用category会触发界面的修改，所以复制一份对象，用复制的对象去修改
                    _this.categoryToEdit = Object.assign({}, category)
                } else {

                    _this.categoryToEdit = {
                    parent: null,
                    name: null,
                    sort: null,
                    }
                }
                $('#saveModal').modal('show')
            },
            saveOneCategory() {
                let _this = this;

                // 保存校验
                if (1 != 1
                    || !Validator.require(_this.categoryToEdit.parent, "父id")
                    || !Validator.require(_this.categoryToEdit.name, "名称")
                    || !Validator.length(_this.categoryToEdit.name, "名称", 1, 50)
                ) {
                    return;
                }

                addOneCategory(_this.categoryToEdit)
                    .then(success => {
                        if (success) {
                            _this.getCategoryList(1, _this.currentPageConfig.tableSize)
                            $('#saveModal').modal('hide')
                        }
                    }).catch(reason => {
                    new MessageBox({
                        message: reason.msg,
                        icon: 'error'
                    }).toast()
                })
            },
            editOneCategory() {
                let _this = this;

                // 保存校验
                if (1 != 1
                    || !Validator.require(_this.categoryToEdit.parent, "父id")
                    || !Validator.require(_this.categoryToEdit.name, "名称")
                    || !Validator.length(_this.categoryToEdit.name, "名称", 1, 50)
                ) {
                    return;
                }

                editOneCategory(_this.categoryToEdit)
                    .then(success => {
                        if (success) {
                            _this.getCategoryList(1, _this.currentPageConfig.tableSize)
                            $('#saveModal').modal('hide')
                        }
                    }).catch(reason => {
                    new MessageBox({
                        message: reason.msg,
                        icon: 'error'
                    }).toast()
                })
            },
            delOneCategory(id) {
                let _this = this;
                new MessageBox({
                    message: '您确认要删除分类吗？',
                    callback: function (result) {
                        MaskLoading.start()
                        if (result.value) {
                            delOneCategory(id).then(success => {
                                _this.getCategoryList(1, _this.currentPageConfig.tableSize)

                                MaskLoading.complete()
                                new MessageBox({
                                    message: '删除成功！',
                                    icon: 'success'
                                }).toast()
                            })
                            // For more information about handling dismissals please visit
                            // https://sweetalert2.github.io/#handling-dismissals
                        } else if (result.dismiss === Swal.DismissReason.cancel) {
                            MaskLoading.complete()
                            new MessageBox({
                                message: '删除已取消！您的数据很安全。',
                                icon: 'info'
                            }).alert()
                        }
                    }
                }).comfirm()
            }
        },
        created() {
            this.getCategoryList(1, this.currentPageConfig.tableSize)
        },
        mounted() {
        },
    }
</script>

<style lang="stylus" scoped>
</style>


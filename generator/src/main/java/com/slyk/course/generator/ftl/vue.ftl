<template>
    <div>
        <!-- Modal -->
        <div class="modal fade" id="saveModal" tabindex="-1" role="dialog" aria-labelledby="saveModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="saveModalLabel">编辑${tableNameCn}</h4>
                    </div>
                    <div class="modal-body">
                        <form>
                            <#list fieldList as field>
                                <#if field.name!="id" && field.nameHump!="createdAt" && field.nameHump!="updatedAt">
                                    <#if field.enums>

                                        <div class="form-group">
                                            <label for="${field.nameHump}">${field.nameCn}</label>
                                            <select v-model="${domain}ToEdit.${field.nameHump}" class="form-control" id="${field.nameHump}">
                                                <option v-for="o in ${field.enumsConst}" :value="o.key" :key="o.value">{{o.value}}</option>
                                            </select>
                                        </div>

                                    <#else>
                                        <div class="form-group">
                                            <label for="${field.nameHump}">${field.nameCn}</label>
                                            <input type="text" v-model="${domain}ToEdit.${field.nameHump}" class="form-control" id="${field.nameHump}"
                                                   placeholder="${field.nameCn}">
                                        </div>
                                    </#if>
                                </#if>
                            </#list>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" @click="saveOne${Domain}">保存</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <P>
                    <button class="btn btn-white btn-default btn-round" @click="showEdit${Domain}Dialog(null)">
                        <i class="ace-icon fa fa-save red2"></i>
                        新增
                    </button>
                    &nbsp;
                    <button class="btn btn-white btn-default btn-round"
                            @click="get${Domain}List(1,$refs.pagination.size)">
                        <i class="ace-icon fa fa-refresh red2"></i>
                        刷新
                    </button>
                </P>
                <table id="${domain}Table" class="table  table-bordered table-hover">
                    <thead>
                    <tr>
                        <#list fieldList as field>
                            <#if field.nameHump!="createdAt" && field.nameHump!="updatedAt">
                        <th>${field.nameCn}</th>
                            </#if>
                        </#list>

                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="${domain} in tableData.rows" :key="${domain}.id">
                        <#list fieldList as field>
                            <#if field.nameHump!="createdAt" && field.nameHump!="updatedAt">
                                <#if field.enums>
                        <td>{{${field.enumsConst} | optionKV(${domain}.${field.nameHump})}}</td>
                                <#else>
                        <td>{{${domain}.${field.nameHump}}}</td>
                                </#if>
                            </#if>
                        </#list>
                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <button class="btn btn-xs btn-info" @click="showEdit${Domain}Dialog(${domain})">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    编辑
                                </button>

                                <button class="btn btn-xs btn-danger" @click="delOne${Domain}(${domain}.id)">
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
                                               title="Edit" @click="showEdit${Domain}Dialog(${domain})">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete"
                                               @click="delOne${Domain}(${domain}.id)">
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
                            @list="get${Domain}List" @selectChanged="paginationSelectChanged" ref="pagination"/>
            </div><!-- /.span -->
        </div><!-- /.row -->
    </div>
</template>

<script>
    import {get${Domain}List, addOne${Domain}, editOne${Domain}, delOne${Domain}} from "api/admin/${domain}";
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
                ${domain}ToEdit: {
                    <#list fieldList as field>
                        <#if field.name!="id" && field.nameHump!="createdAt" && field.nameHump!="updatedAt">
                    ${field.nameHump}: null,
                        </#if>
                    </#list>
                },
                currentPageConfig: {
                    tableSize: 5,
                    showLoading: false
                },
            <#list fieldList as field>
                <#if field.enums>
                ${field.enumsConst}: ${field.enumsConst},
                </#if>
            </#list>
            }
        },
        methods: {
            get${Domain}List(page, size) {
                let _this = this;
                get${Domain}List(page, size).then(data => {
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
                _this.get${Domain}List(1, this.currentPageConfig.tableSize)
            },
            //如果传入了${domain}，说明是编辑，否则是新增
            showEdit${Domain}Dialog(${domain}) {
                let _this = this
                if (${domain}) {
                    //因为直接用${domain}会触发界面的修改，所以复制一份对象，用复制的对象去修改
                    _this.${domain}ToEdit = Object.assign({}, ${domain})
                } else {

                    _this.${domain}ToEdit = {
                    <#list fieldList as field>
                    <#if field.name!="id" && field.nameHump!="createdAt" && field.nameHump!="updatedAt">
                    ${field.nameHump}: null,
                    </#if>
                    </#list>
                    }
                }
                $('#saveModal').modal('show')
            },
            saveOne${Domain}() {
                let _this = this;

                // 保存校验
                if (1 != 1
                    <#list fieldList as field>
                    <#if field.name!="id" && field.nameHump!="createdAt" && field.nameHump!="updatedAt" && field.nameHump!="sort">
                    <#if !field.nullAble>
                    || !Validator.require(_this.${domain}ToEdit.${field.nameHump}, "${field.nameCn}")
                    </#if>
                    <#if (field.length > 0)>
                    || !Validator.length(_this.${domain}ToEdit.${field.nameHump}, "${field.nameCn}", 1, ${field.length?c})
                    </#if>
                    </#if>
                    </#list>
                ) {
                    return;
                }

                addOne${Domain}(_this.${domain}ToEdit)
                    .then(success => {
                        if (success) {
                            _this.get${Domain}List(1, _this.currentPageConfig.tableSize)
                            $('#saveModal').modal('hide')
                        }
                    }).catch(reason => {
                    new MessageBox({
                        message: reason.msg,
                        icon: 'error'
                    }).toast()
                })
            },
            editOne${Domain}() {
                let _this = this;

                // 保存校验
                if (1 != 1
                    <#list fieldList as field>
                    <#if field.name!="id" && field.nameHump!="createdAt" && field.nameHump!="updatedAt" && field.nameHump!="sort">
                    <#if !field.nullAble>
                    || !Validator.require(_this.${domain}ToEdit.${field.nameHump}, "${field.nameCn}")
                    </#if>
                    <#if (field.length > 0)>
                    || !Validator.length(_this.${domain}ToEdit.${field.nameHump}, "${field.nameCn}", 1, ${field.length?c})
                    </#if>
                    </#if>
                    </#list>
                ) {
                    return;
                }

                editOne${Domain}(_this.${domain}ToEdit)
                    .then(success => {
                        if (success) {
                            _this.get${Domain}List(1, _this.currentPageConfig.tableSize)
                            $('#saveModal').modal('hide')
                        }
                    }).catch(reason => {
                    new MessageBox({
                        message: reason.msg,
                        icon: 'error'
                    }).toast()
                })
            },
            delOne${Domain}(id) {
                let _this = this;
                new MessageBox({
                    message: '您确认要删除${tableNameCn}吗？',
                    callback: function (result) {
                        MaskLoading.start()
                        if (result.value) {
                            delOne${Domain}(id).then(success => {
                                _this.get${Domain}List(1, _this.currentPageConfig.tableSize)

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
            this.get${Domain}List(1, this.currentPageConfig.tableSize)
        },
        mounted() {
        },
    }
</script>

<style lang="stylus" scoped>
</style>


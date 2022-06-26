// 2度押し判定フラグ
var isSubmit = false;

$(function(){
    // メンバー追加処理
    $('#addAccounts').on('click', function(){
        // ボタンを2度押ししている場合
        if(isSubmit){
            alert("処理中です");
            return false;
        } else {
            isSubmit = true;
        }
        // 画面に表示されている全アカウントを取得
        var accountList = document.getElementsByClassName("chkAccounts");
        var selectedAccountList = [];
        // チェックされているアカウントのみリストに格納
        for(var i = 0; i < accountAllList.length; i++){
            if(accountAllList[i].cheked){
                selectedAccountList.push(accountAllList[i].value);
            }
        }
        // アカウントが選択されていない場合処理を抜ける
        if(selectedAccountList.length <= 0){
            isSubmit = false;
            return;
        }
        var groupName = '';
        // グループ名の取得
        if (document.getElementById('txtGroupName') != null){
            groupName = document.getElementById('txtGroupName').value;
        }
        // パラメータの設定
        var data = {
            'selectedAccountList' : selectedAccountList
            , 'groupName' : groupName
        }

        $.ajax({
            contentType: 'application/json'
            , url: '/group/member/add'
            , type: 'POST'
            , dataType: 'json'
            , data: JSON.stringify(data)
        // レスポンスが正常に返却された場合
        }).done(function(flag){
            // エラーが発生した場合
            if (flag.error){
                location.href = '/exception'
            // ログインしていない場合
            } else if(flag.login){
                location.href = '/login'
            } else {
                location.href = '/group/regist'
                isSubmit = false;
            }
        // アクセスエラーが発生した場合
        }).fail(function(){
            location.href = '/exception'
        });
    });
});

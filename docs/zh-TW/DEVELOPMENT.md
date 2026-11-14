# Aerial 繁體中文版開發紀錄

## 專案目標
以原始 Aerial 專案為基礎，新增台灣繁體中文介面（zh-TW），保留原有功能與原始語系，不直接覆蓋簡體中文資源。

## 開發原則
- 所有改動只提交到 `feature/zh-tw-localization`，不直接修改 `main`。
- 新增 Android 資源目錄 `values-zh-rTW`，不覆蓋 `values-zh`。
- 手機掃碼管理頁需同步新增繁體中文語系。
- 依原專案規範維護資源 key、格式占位符與字串一致性。
- 不提交 `local/`、簽章金鑰、電視 IP、個人裝置資料。
- 未經真機測試不宣稱遙控器操作或設備相容性已驗收。
- 不任意更改 applicationId、既有資料路徑或簽章設定。

## 基準資料
- 上游：`pengchujin/aerial-tv`
- 工作儲存庫：`chistyl/aerial-tv`
- 基底分支：`main`
- 工作分支：`feature/zh-tw-localization`
- 基準版本：Aerial 1.96 / versionCode 137
- 下次正式版 versionCode：至少 140（依上游 HANDOFF 交接紀錄）
- Lite versionCode：至少 32（若本次有修改 Lite）

## 目前盤點
- Android UI 簡體中文：`app/src/main/res/values-zh/strings.xml`
- Android UI 英文基準：`app/src/main/res/values/strings.xml`
- 其他原始語系：ru、uk、kk
- 手機管理頁：`app/src/main/assets/web/index.html`，使用 KEYS / TEXTS 語系表
- 模組：`app`（standard / guard）、`lite`（Android 4.x）
- applicationId：`app.orchard.tv`
- minSdk：21；targetSdk：34；compileSdk：36
- Release 簽章依賴本機 `local/signing.properties`，不得假設可用原作者簽章

## 里程碑
1. [x] 建立繁中專用分支
2. [x] 建立繁中開發紀錄
3. [ ] 建立並填入 Android zh-TW 字串資源
4. [ ] 建立手機管理頁 zh-TW 語系
5. [ ] 全面檢查對話框、通知、無障礙描述、錯誤提示
6. [ ] 加入語系完整性檢查與 GitHub Actions 建置
7. [ ] 建置 standard / guard APK
8. [ ] 實機遙控器與功能驗收
9. [ ] 發布 APK、原始碼 ZIP 與 GitHub Release

## APK 安裝與簽章注意
原 applicationId 保留為 `app.orchard.tv`。若無法取得原作者相同的 Release keystore，使用新簽章的 APK 不能覆蓋更新原作者已安裝版本；需先用測試設備驗證獨立安裝／簽章方案，且不得清除原有資料。

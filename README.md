<h1>Api de Controle Financeiro</h1>

<p>Este é um projeto back-end desenvolvido com Spring Boot para controle financeiro. A API permite que os usuários gerenciem seus centros de custos e títulos financeiros (a pagar e a receber). A plataforma também disponibiliza um dashboard para análise do fluxo de caixa com base em um período inicial e final.</p>

<h2>🛠 Tecnologias Utilizadas</h2>
<ul>
    <li>Spring Boot</li>
    <li>Spring Security</li>
    <li>JWT (JSON Web Token)</li>
    <li>Lombok</li>
    <li>ModelMapper</li>
    <li>PostgreSQL</li>
</ul>

<h2>Como executar  projeto</h2>

<h3>Requisitos</h3>
<ul>
    <li>Java 21</li>
    <li>PostgreSQL</li>
</ul>

<h2>🚀 Endpoints</h2>

<h3>Usuários (<code>/usuarios</code>)</h3>

<ul>
    <li><code>GET /usuarios</code> - Lista todos os usuários</li>
    <li><code>GET /usuarios/{id}</code> - Busca um usuário pelo ID</li>
    <li><code>POST /usuarios</code> - Cria um novo usuário</li>
    <li><code>PUT /usuarios</code> - Atualiza os dados do usuário autenticado</li>
    <li><code>DELETE /usuarios</code> - Deixa a conta do usuario inativado</li>
</ul>

<h3>Observações</h3>
<ul>
    <Li>Caso não for achado or id do usuario, ira retornar um erro de não achado usuario.</Li>
    <li>O usuario possui um sistema de validação de email e senha.</li>
    <li>Não se pode criar conta com um email ja existente.</li>
</ul>

<h3>Títulos (<code>/titulos</code>)</h3>
<ul>
    <li><code>GET /titulos</code> - Lista todos os títulos</li>
    <li><code>GET /titulos/{id}</code> - Busca um título pelo ID</li>
    <li><code>POST /titulos</code> - Cria um novo título</li>
    <li><code>PUT /titulos/{id}</code> - Atualiza um título existente</li>
    <li><code>DELETE /titulos/{id}</code> - Remove um título</li>
</ul>

<h3>Observações</h3>
<ul>
    <li>Para criação de titulos e centro de custo necessita do usuario autenticado</li>
    <li>O titulo possui um sistema de validação.</li>
</ul>

<h3>Centros de Custo (<code>/centrodecusto</code>)</h3>
<ul>
    <li><code>GET /centrodecusto</code> - Lista todos os centros de custo</li>
    <li><code>POST /centrodecusto</code> - Cria um novo centro de custo</li>
    <li><code>GET /centrodecusto/{id}</code> - Busca um centro de custo pelo ID</li>
    <li><code>PUT /centrodecusto/{id}</code> - Atualiza um centro de custo</li>
    <li><code>DELETE /centrodecusto/{id}</code> - Remove um centro de custo</li>
</ul>

<h3>Dashboard (<code>/dashboard</code>)</h3>
<ul>
    <li><code>GET /dashboard?periodoInicial=yyyy-MM-dd HH:mm:ss&periodoFinal=yyyy-MM-dd HH:mm:ss</code> - Obtém fluxo de caixa no período informado</li>
</ul>

<h2>📌 Autenticação</h2>
<p>Esta API utiliza <strong>JWT</strong> para autenticação. O usuário deve incluir o token no cabeçalho das requisições autenticadas, no formato:</p>
<pre><code>Authorization: Bearer {token}</code></pre>

<h2>🗂 Banco de Dados</h2>
<p>O projeto utiliza <strong>PostgreSQL</strong>. Certifique-se de configurar corretamente as credenciais no <code>application.properties</code>.</p>

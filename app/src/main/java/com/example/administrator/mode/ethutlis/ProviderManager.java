package com.example.administrator.mode.ethutlis;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.ipc.UnixIpcService;
import org.web3j.protocol.ipc.WindowsIpcService;

import java.util.*;

public class ProviderManager {

    private Map<String, Provider> _nodeMapping = new HashMap<>();

    private int nextBalancingNodeIndex = 0;

    private ProviderManager() { }

    private static ProviderManager _shareInstance = null;
    public static synchronized ProviderManager DefaultManager() {

        try {

            if ( ProviderManager._shareInstance == null ) {

                ProviderManager._shareInstance = new ProviderManager();

                ResourceBundle resource = ResourceBundle.getBundle("local");

                String configType = resource.getString("provider.connection.type");
                String configParam = resource.getString("provider.connection.param");

                Provider newNode = null;

                switch (configType) {

                    case "RPC" : {

                        newNode = new Provider("DefaultProvider", Provider.ConnectType.RPC, configParam );

                    }
                    break;

                    case "WindowsIPC" : {

                        newNode = new Provider("DefaultProvider", Provider.ConnectType.Windows_IPC, configParam);

                    }
                    break;

                    case "UinxIPC" : {
                        newNode = new Provider("DefaultProvider", Provider.ConnectType.Uinx_IPC, configParam);
                    }
                }

                _shareInstance.AddConnectionNode(newNode);
            }

            return ProviderManager._shareInstance;
        }
        catch (ServiceExpection se)
        {

        }
        finally {

            return ProviderManager._shareInstance;
        }
    }


    public void AddConnectionNode( Provider node ) throws ServiceExpection {

        if ( _nodeMapping.containsKey(node.getIdentifier() ) )
        {
            throw new ServiceExpection(-101, "Provider identifier already exist.");
        }

        Web3j web3jInstance = null;

        /// 尝试连接
        switch ( node.getType() )
        {
            case RPC:
            {
                node.setService( new HttpService(node.getParam()) );
            }
            break;

            case Uinx_IPC:
            {
                node.setService( new UnixIpcService(node.getParam()) );
            }
            break;

            case Windows_IPC:
            {
                node.setService( new WindowsIpcService(node.getParam()) );
            }
            break;
        }

        web3jInstance = Web3j.build(node.getService());

        if ( web3jInstance != null )
        {
            node.setWeb3j(web3jInstance);
            node.setState(Provider.ConnectState.Connectioned);
            _nodeMapping.put(node.getIdentifier(), node);

            return ;
        }

        throw new ServiceExpection(-102, "Can't connection to provider.");
    }

    public void DisconnectNode(Provider node) throws ServiceExpection {

        if ( !_nodeMapping.containsKey(node.getIdentifier() ) )
        {
            throw new ServiceExpection(-103, "Can't found provider identifier : " + node.getIdentifier() + "." );
        }

        if ( _nodeMapping.get(node.getIdentifier()).getState() == Provider.ConnectState.Connectioned )
        {
            _nodeMapping.get(node.getIdentifier()).getWeb3j().shutdown();

            return ;
        }
        else
        {
            throw new ServiceExpection(-104, "Provider node are not connected." + node.getIdentifier());
        }

    }

    public void DisconnectNode(String identifier) throws ServiceExpection {

        if ( !_nodeMapping.containsKey(identifier) )
        {
            throw new ServiceExpection(-103, "Can't found provider identifier : " + identifier + "." );
        }

        DisconnectNode( _nodeMapping.get(identifier) );
    }

    public void RemoveNode(Provider node) throws ServiceExpection {

        DisconnectNode(node);

        _nodeMapping.remove(node.getIdentifier());

        return ;
    }

    public void RemoveNode(String identifier) throws ServiceExpection {

        DisconnectNode(identifier);

        _nodeMapping.remove(identifier);
    }

    public List<Provider> GetNodesList() {
        return new ArrayList<Provider>(_nodeMapping.values());
    }

    public Provider GetNode( String identifier ) throws ServiceExpection {
        if ( !_nodeMapping.containsKey(identifier) )
        {
            throw new ServiceExpection(-103, "Can't found provider identifier : " + identifier + "." );
        }

        return _nodeMapping.get(identifier);
    }

    public Provider GetBalancingNode() {

        try {

            List<Provider> nodeList = GetNodesList();

            for ( int i = nextBalancingNodeIndex; i < _nodeMapping.size(); i++ )
            {
                if ( nodeList.get(i).getState() == Provider.ConnectState.Connectioned )
                {
                    return nodeList.get(i);
                }
            }

            return null;
        }
        finally {

            if ( nextBalancingNodeIndex + 1 >= _nodeMapping.size() )
            {
                nextBalancingNodeIndex = 0;
            }
            else
            {
                nextBalancingNodeIndex ++;
            }
        }
    }
}
